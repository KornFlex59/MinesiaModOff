
package palamod.network;

import palamod.world.inventory.AdminshopblockblackstoneMenu;

import palamod.procedures.CloseguiProcedure;
import palamod.procedures.AdminshopblockconnectProcedure;
import palamod.procedures.Adminshop_openProcedure;
import palamod.procedures.AdhblksellblackstoneProcedure;
import palamod.procedures.AdhblkbuyblackstoneProcedure;

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
public record AdminshopblockblackstoneButtonMessage(int buttonID, int x, int y, int z) implements CustomPacketPayload {

	public static final Type<AdminshopblockblackstoneButtonMessage> TYPE = new Type<>(ResourceLocation.fromNamespaceAndPath(PalamodMod.MODID, "adminshopblockblackstone_buttons"));
	public static final StreamCodec<RegistryFriendlyByteBuf, AdminshopblockblackstoneButtonMessage> STREAM_CODEC = StreamCodec.of((RegistryFriendlyByteBuf buffer, AdminshopblockblackstoneButtonMessage message) -> {
		buffer.writeInt(message.buttonID);
		buffer.writeInt(message.x);
		buffer.writeInt(message.y);
		buffer.writeInt(message.z);
	}, (RegistryFriendlyByteBuf buffer) -> new AdminshopblockblackstoneButtonMessage(buffer.readInt(), buffer.readInt(), buffer.readInt(), buffer.readInt()));
	@Override
	public Type<AdminshopblockblackstoneButtonMessage> type() {
		return TYPE;
	}

	public static void handleData(final AdminshopblockblackstoneButtonMessage message, final IPayloadContext context) {
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
		HashMap guistate = AdminshopblockblackstoneMenu.guistate;
		// security measure to prevent arbitrary chunk generation
		if (!world.hasChunkAt(new BlockPos(x, y, z)))
			return;
		if (buttonID == 0) {

			AdhblkbuyblackstoneProcedure.execute(world, entity, guistate);
		}
		if (buttonID == 1) {

			AdhblksellblackstoneProcedure.execute(world, entity, guistate);
		}
		if (buttonID == 2) {

			CloseguiProcedure.execute(entity);
		}
		if (buttonID == 3) {

			AdminshopblockconnectProcedure.execute(world, x, y, z, entity);
		}
		if (buttonID == 4) {

			Adminshop_openProcedure.execute(world, x, y, z, entity);
		}
	}

	@SubscribeEvent
	public static void registerMessage(FMLCommonSetupEvent event) {
		PalamodMod.addNetworkMessage(AdminshopblockblackstoneButtonMessage.TYPE, AdminshopblockblackstoneButtonMessage.STREAM_CODEC, AdminshopblockblackstoneButtonMessage::handleData);
	}
}
