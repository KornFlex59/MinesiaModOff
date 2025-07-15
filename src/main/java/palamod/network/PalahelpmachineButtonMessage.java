
package palamod.network;

import palamod.world.inventory.PalahelpmachineMenu;

import palamod.procedures.ConnectpalamachinepalahelpProcedure;
import palamod.procedures.ConnectpalahelpcrusherProcedure;
import palamod.procedures.ConnectnotyetguiProcedure;
import palamod.procedures.Connectgrinderpag1Procedure;
import palamod.procedures.ConnectforgepalahelpProcedure;
import palamod.procedures.CloseguiProcedure;
import palamod.procedures.BlockbackProcedure;
import palamod.procedures.BackpalahelpProcedure;

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
public record PalahelpmachineButtonMessage(int buttonID, int x, int y, int z) implements CustomPacketPayload {

	public static final Type<PalahelpmachineButtonMessage> TYPE = new Type<>(ResourceLocation.fromNamespaceAndPath(PalamodMod.MODID, "palahelpmachine_buttons"));
	public static final StreamCodec<RegistryFriendlyByteBuf, PalahelpmachineButtonMessage> STREAM_CODEC = StreamCodec.of((RegistryFriendlyByteBuf buffer, PalahelpmachineButtonMessage message) -> {
		buffer.writeInt(message.buttonID);
		buffer.writeInt(message.x);
		buffer.writeInt(message.y);
		buffer.writeInt(message.z);
	}, (RegistryFriendlyByteBuf buffer) -> new PalahelpmachineButtonMessage(buffer.readInt(), buffer.readInt(), buffer.readInt(), buffer.readInt()));
	@Override
	public Type<PalahelpmachineButtonMessage> type() {
		return TYPE;
	}

	public static void handleData(final PalahelpmachineButtonMessage message, final IPayloadContext context) {
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
		HashMap guistate = PalahelpmachineMenu.guistate;
		// security measure to prevent arbitrary chunk generation
		if (!world.hasChunkAt(new BlockPos(x, y, z)))
			return;
		if (buttonID == 0) {

			Connectgrinderpag1Procedure.execute(world, x, y, z, entity);
		}
		if (buttonID == 1) {

			ConnectpalamachinepalahelpProcedure.execute(world, x, y, z, entity);
		}
		if (buttonID == 2) {

			ConnectpalahelpcrusherProcedure.execute(world, x, y, z, entity);
		}
		if (buttonID == 3) {

			ConnectforgepalahelpProcedure.execute(world, x, y, z, entity);
		}
		if (buttonID == 4) {

			ConnectnotyetguiProcedure.execute(world, x, y, z, entity);
		}
		if (buttonID == 5) {

			ConnectnotyetguiProcedure.execute(world, x, y, z, entity);
		}
		if (buttonID == 6) {

			ConnectnotyetguiProcedure.execute(world, x, y, z, entity);
		}
		if (buttonID == 7) {

			BlockbackProcedure.execute(world, x, y, z, entity);
		}
		if (buttonID == 8) {

			BackpalahelpProcedure.execute(world, x, y, z, entity);
		}
		if (buttonID == 9) {

			CloseguiProcedure.execute(entity);
		}
	}

	@SubscribeEvent
	public static void registerMessage(FMLCommonSetupEvent event) {
		PalamodMod.addNetworkMessage(PalahelpmachineButtonMessage.TYPE, PalahelpmachineButtonMessage.STREAM_CODEC, PalahelpmachineButtonMessage::handleData);
	}
}
