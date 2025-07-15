
package palamod.network;

import palamod.world.inventory.AdminshoputilitiesMenu;

import palamod.procedures.ConnectadminshoputilitiesredstoneProcedure;
import palamod.procedures.ConnectadminshoputilitiesemeraldProcedure;
import palamod.procedures.ConnectadminshoputilitiesdiampicProcedure;
import palamod.procedures.ConnectadminshopmobsenderpearlProcedure;
import palamod.procedures.ConnectadminshopmobsboneProcedure;
import palamod.procedures.ConnectadhorecoalProcedure;
import palamod.procedures.ConnectadhblkstoneProcedure;
import palamod.procedures.ConnectadhblkoakwoodProcedure;
import palamod.procedures.ConnectadhblknetherrackProcedure;
import palamod.procedures.ConnectAdminshopmobs2steakProcedure;
import palamod.procedures.CloseguiProcedure;
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
public record AdminshoputilitiesButtonMessage(int buttonID, int x, int y, int z) implements CustomPacketPayload {

	public static final Type<AdminshoputilitiesButtonMessage> TYPE = new Type<>(ResourceLocation.fromNamespaceAndPath(PalamodMod.MODID, "adminshoputilities_buttons"));
	public static final StreamCodec<RegistryFriendlyByteBuf, AdminshoputilitiesButtonMessage> STREAM_CODEC = StreamCodec.of((RegistryFriendlyByteBuf buffer, AdminshoputilitiesButtonMessage message) -> {
		buffer.writeInt(message.buttonID);
		buffer.writeInt(message.x);
		buffer.writeInt(message.y);
		buffer.writeInt(message.z);
	}, (RegistryFriendlyByteBuf buffer) -> new AdminshoputilitiesButtonMessage(buffer.readInt(), buffer.readInt(), buffer.readInt(), buffer.readInt()));
	@Override
	public Type<AdminshoputilitiesButtonMessage> type() {
		return TYPE;
	}

	public static void handleData(final AdminshoputilitiesButtonMessage message, final IPayloadContext context) {
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
		HashMap guistate = AdminshoputilitiesMenu.guistate;
		// security measure to prevent arbitrary chunk generation
		if (!world.hasChunkAt(new BlockPos(x, y, z)))
			return;
		if (buttonID == 0) {

			ConnectadminshoputilitiesdiampicProcedure.execute(world, x, y, z, entity);
		}
		if (buttonID == 1) {

			ConnectadhblknetherrackProcedure.execute(world, x, y, z, entity);
		}
		if (buttonID == 2) {

			ConnectadminshoputilitiesredstoneProcedure.execute(world, x, y, z, entity);
		}
		if (buttonID == 3) {

			ConnectadhorecoalProcedure.execute(world, x, y, z, entity);
		}
		if (buttonID == 4) {

			ConnectadhblkoakwoodProcedure.execute(world, x, y, z, entity);
		}
		if (buttonID == 5) {

			ConnectadminshopmobsboneProcedure.execute(world, x, y, z, entity);
		}
		if (buttonID == 6) {

			ConnectadminshopmobsenderpearlProcedure.execute(world, x, y, z, entity);
		}
		if (buttonID == 7) {

			ConnectAdminshopmobs2steakProcedure.execute(world, x, y, z, entity);
		}
		if (buttonID == 8) {

			ConnectadhblkstoneProcedure.execute(world, x, y, z, entity);
		}
		if (buttonID == 9) {

			ConnectadminshoputilitiesemeraldProcedure.execute(world, x, y, z, entity);
		}
		if (buttonID == 10) {

			Adminshop_openProcedure.execute(world, x, y, z, entity);
		}
		if (buttonID == 11) {

			CloseguiProcedure.execute(entity);
		}
	}

	@SubscribeEvent
	public static void registerMessage(FMLCommonSetupEvent event) {
		PalamodMod.addNetworkMessage(AdminshoputilitiesButtonMessage.TYPE, AdminshoputilitiesButtonMessage.STREAM_CODEC, AdminshoputilitiesButtonMessage::handleData);
	}
}
