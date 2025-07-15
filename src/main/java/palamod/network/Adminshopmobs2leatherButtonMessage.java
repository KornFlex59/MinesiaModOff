
package palamod.network;

import palamod.world.inventory.Adminshopmobs2leatherMenu;

import palamod.procedures.Connectadminshopmobs2Procedure;
import palamod.procedures.CloseguiProcedure;
import palamod.procedures.Adminshop_openProcedure;
import palamod.procedures.Adhmobs2sellleatherProcedure;
import palamod.procedures.Adhmobs2buyleatherProcedure;

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
public record Adminshopmobs2leatherButtonMessage(int buttonID, int x, int y, int z) implements CustomPacketPayload {

	public static final Type<Adminshopmobs2leatherButtonMessage> TYPE = new Type<>(ResourceLocation.fromNamespaceAndPath(PalamodMod.MODID, "adminshopmobs_2leather_buttons"));
	public static final StreamCodec<RegistryFriendlyByteBuf, Adminshopmobs2leatherButtonMessage> STREAM_CODEC = StreamCodec.of((RegistryFriendlyByteBuf buffer, Adminshopmobs2leatherButtonMessage message) -> {
		buffer.writeInt(message.buttonID);
		buffer.writeInt(message.x);
		buffer.writeInt(message.y);
		buffer.writeInt(message.z);
	}, (RegistryFriendlyByteBuf buffer) -> new Adminshopmobs2leatherButtonMessage(buffer.readInt(), buffer.readInt(), buffer.readInt(), buffer.readInt()));
	@Override
	public Type<Adminshopmobs2leatherButtonMessage> type() {
		return TYPE;
	}

	public static void handleData(final Adminshopmobs2leatherButtonMessage message, final IPayloadContext context) {
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
		HashMap guistate = Adminshopmobs2leatherMenu.guistate;
		// security measure to prevent arbitrary chunk generation
		if (!world.hasChunkAt(new BlockPos(x, y, z)))
			return;
		if (buttonID == 0) {

			Adhmobs2buyleatherProcedure.execute(world, entity, guistate);
		}
		if (buttonID == 1) {

			Adhmobs2sellleatherProcedure.execute(world, entity, guistate);
		}
		if (buttonID == 2) {

			CloseguiProcedure.execute(entity);
		}
		if (buttonID == 3) {

			Connectadminshopmobs2Procedure.execute(world, x, y, z, entity);
		}
		if (buttonID == 4) {

			Adminshop_openProcedure.execute(world, x, y, z, entity);
		}
	}

	@SubscribeEvent
	public static void registerMessage(FMLCommonSetupEvent event) {
		PalamodMod.addNetworkMessage(Adminshopmobs2leatherButtonMessage.TYPE, Adminshopmobs2leatherButtonMessage.STREAM_CODEC, Adminshopmobs2leatherButtonMessage::handleData);
	}
}
