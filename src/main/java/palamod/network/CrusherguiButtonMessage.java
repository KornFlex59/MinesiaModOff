
package palamod.network;

import palamod.world.inventory.CrusherguiMenu;

import palamod.procedures.Crusherluncherv2titaneProcedure;
import palamod.procedures.Crusherluncherv2paladiumProcedure;
import palamod.procedures.Crusherluncherv2endiumProcedure;
import palamod.procedures.Crusherluncherv2amethystProcedure;

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
public record CrusherguiButtonMessage(int buttonID, int x, int y, int z) implements CustomPacketPayload {

	public static final Type<CrusherguiButtonMessage> TYPE = new Type<>(ResourceLocation.fromNamespaceAndPath(PalamodMod.MODID, "crushergui_buttons"));
	public static final StreamCodec<RegistryFriendlyByteBuf, CrusherguiButtonMessage> STREAM_CODEC = StreamCodec.of((RegistryFriendlyByteBuf buffer, CrusherguiButtonMessage message) -> {
		buffer.writeInt(message.buttonID);
		buffer.writeInt(message.x);
		buffer.writeInt(message.y);
		buffer.writeInt(message.z);
	}, (RegistryFriendlyByteBuf buffer) -> new CrusherguiButtonMessage(buffer.readInt(), buffer.readInt(), buffer.readInt(), buffer.readInt()));
	@Override
	public Type<CrusherguiButtonMessage> type() {
		return TYPE;
	}

	public static void handleData(final CrusherguiButtonMessage message, final IPayloadContext context) {
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
		HashMap guistate = CrusherguiMenu.guistate;
		// security measure to prevent arbitrary chunk generation
		if (!world.hasChunkAt(new BlockPos(x, y, z)))
			return;
		if (buttonID == 0) {

			Crusherluncherv2titaneProcedure.execute(world, x, y, z);
		}
		if (buttonID == 1) {

			Crusherluncherv2endiumProcedure.execute(world, x, y, z);
		}
		if (buttonID == 2) {

			Crusherluncherv2amethystProcedure.execute(world, x, y, z);
		}
		if (buttonID == 3) {

			Crusherluncherv2paladiumProcedure.execute(world, x, y, z);
		}
	}

	@SubscribeEvent
	public static void registerMessage(FMLCommonSetupEvent event) {
		PalamodMod.addNetworkMessage(CrusherguiButtonMessage.TYPE, CrusherguiButtonMessage.STREAM_CODEC, CrusherguiButtonMessage::handleData);
	}
}
