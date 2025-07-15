
package palamod.network;

import palamod.world.inventory.Adminshopmobs2Menu;

import palamod.procedures.ConnectadminshopmobsProcedure;
import palamod.procedures.ConnectAdminshopmobs2steakProcedure;
import palamod.procedures.ConnectAdminshopmobs2leatherProcedure;
import palamod.procedures.ConnectAdminshopmobs2inksacProcedure;
import palamod.procedures.ConnectAdminshopmobs2honeycombProcedure;
import palamod.procedures.ConnectAdminshopmobs2gunpowderProcedure;
import palamod.procedures.ConnectAdminshopmobs2glowinksacProcedure;
import palamod.procedures.ConnectAdminshopmobs2featherProcedure;
import palamod.procedures.ConnectAdminshopmobs2blazeProcedure;
import palamod.procedures.Adminshop_openProcedure;

import palamod.PalamodMod;

import net.neoforged.neoforge.network.handling.IPayloadContext;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.network.protocol.PacketFlow;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.chat.Component;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.core.BlockPos;

import java.util.HashMap;

@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD)
public record Adminshopmobs2ButtonMessage(int buttonID, int x, int y, int z) implements CustomPacketPayload {

	public static final Type<Adminshopmobs2ButtonMessage> TYPE = new Type<>(ResourceLocation.fromNamespaceAndPath(PalamodMod.MODID, "adminshopmobs_2_buttons"));
	public static final StreamCodec<RegistryFriendlyByteBuf, Adminshopmobs2ButtonMessage> STREAM_CODEC = StreamCodec.of((RegistryFriendlyByteBuf buffer, Adminshopmobs2ButtonMessage message) -> {
		buffer.writeInt(message.buttonID);
		buffer.writeInt(message.x);
		buffer.writeInt(message.y);
		buffer.writeInt(message.z);
	}, (RegistryFriendlyByteBuf buffer) -> new Adminshopmobs2ButtonMessage(buffer.readInt(), buffer.readInt(), buffer.readInt(), buffer.readInt()));
	@Override
	public Type<Adminshopmobs2ButtonMessage> type() {
		return TYPE;
	}

	public static void handleData(final Adminshopmobs2ButtonMessage message, final IPayloadContext context) {
		if (context.flow() == PacketFlow.SERVERBOUND) {
			context.enqueueWork(() -> {
				Player entity = context.player();
				int buttonID = message.buttonID;
				int x = message.x;
				int y = message.y;
				int z = message.z;
				handleButtonAction(entity, buttonID, x, y, z);
			}).exceptionally(e -> {
				context.connection().disconnect(Component.literal(e.getMessage()));
				return null;
			});
		}
	}

	public static void handleButtonAction(Player entity, int buttonID, int x, int y, int z) {
		Level world = entity.level();
		HashMap guistate = Adminshopmobs2Menu.guistate;
		// security measure to prevent arbitrary chunk generation
		if (!world.hasChunkAt(new BlockPos(x, y, z)))
			return;
		if (buttonID == 0) {

			ConnectAdminshopmobs2honeycombProcedure.execute(world, x, y, z, entity);
		}
		if (buttonID == 1) {

			ConnectAdminshopmobs2leatherProcedure.execute(world, x, y, z, entity);
		}
		if (buttonID == 2) {

			ConnectAdminshopmobs2featherProcedure.execute(world, x, y, z, entity);
		}
		if (buttonID == 3) {

			ConnectAdminshopmobs2inksacProcedure.execute(world, x, y, z, entity);
		}
		if (buttonID == 4) {

			ConnectAdminshopmobs2glowinksacProcedure.execute(world, x, y, z, entity);
		}
		if (buttonID == 5) {

			ConnectAdminshopmobs2blazeProcedure.execute(world, x, y, z, entity);
		}
		if (buttonID == 6) {

			ConnectAdminshopmobs2gunpowderProcedure.execute(world, x, y, z, entity);
		}
		if (buttonID == 7) {

			ConnectAdminshopmobs2steakProcedure.execute(world, x, y, z, entity);
		}
		if (buttonID == 9) {

			Adminshop_openProcedure.execute(world, x, y, z, entity);
		}
		if (buttonID == 10) {

			ConnectadminshopmobsProcedure.execute(world, x, y, z, entity);
		}
	}

	@SubscribeEvent
	public static void registerMessage(FMLCommonSetupEvent event) {
		PalamodMod.addNetworkMessage(Adminshopmobs2ButtonMessage.TYPE, Adminshopmobs2ButtonMessage.STREAM_CODEC, Adminshopmobs2ButtonMessage::handleData);
	}
}
