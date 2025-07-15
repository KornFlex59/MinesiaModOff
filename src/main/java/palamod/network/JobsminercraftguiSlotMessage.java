
package palamod.network;

import palamod.world.inventory.JobsminercraftguiMenu;

import palamod.procedures.GuijobsminercheckcraftProcedure;
import palamod.procedures.CraftdoneminerProcedure;

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
public record JobsminercraftguiSlotMessage(int slotID, int x, int y, int z, int changeType, int meta) implements CustomPacketPayload {

	public static final Type<JobsminercraftguiSlotMessage> TYPE = new Type<>(ResourceLocation.fromNamespaceAndPath(PalamodMod.MODID, "jobsminercraftgui_slots"));
	public static final StreamCodec<RegistryFriendlyByteBuf, JobsminercraftguiSlotMessage> STREAM_CODEC = StreamCodec.of((RegistryFriendlyByteBuf buffer, JobsminercraftguiSlotMessage message) -> {
		buffer.writeInt(message.slotID);
		buffer.writeInt(message.x);
		buffer.writeInt(message.y);
		buffer.writeInt(message.z);
		buffer.writeInt(message.changeType);
		buffer.writeInt(message.meta);
	}, (RegistryFriendlyByteBuf buffer) -> new JobsminercraftguiSlotMessage(buffer.readInt(), buffer.readInt(), buffer.readInt(), buffer.readInt(), buffer.readInt(), buffer.readInt()));
	@Override
	public Type<JobsminercraftguiSlotMessage> type() {
		return TYPE;
	}

	public static void handleData(final JobsminercraftguiSlotMessage message, final IPayloadContext context) {
		if (context.flow() == PacketFlow.SERVERBOUND) {
			context.enqueueWork(() -> {
				Player entity = context.player();
				int slotID = message.slotID;
				int changeType = message.changeType;
				int meta = message.meta;
				int x = message.x;
				int y = message.y;
				int z = message.z;
				handleSlotAction(entity, slotID, changeType, meta, x, y, z);
			}).exceptionally(e -> {
				context.connection().disconnect(Component.literal(e.getMessage()));
				return null;
			});
		}
	}

	public static void handleSlotAction(Player entity, int slot, int changeType, int meta, int x, int y, int z) {
		Level world = entity.level();
		HashMap guistate = JobsminercraftguiMenu.guistate;
		// security measure to prevent arbitrary chunk generation
		if (!world.hasChunkAt(new BlockPos(x, y, z)))
			return;
		if (slot == 0 && changeType == 0) {

			GuijobsminercheckcraftProcedure.execute(world, entity);
		}
		if (slot == 0 && changeType == 1) {

			GuijobsminercheckcraftProcedure.execute(world, entity);
		}
		if (slot == 1 && changeType == 0) {

			GuijobsminercheckcraftProcedure.execute(world, entity);
		}
		if (slot == 1 && changeType == 1) {

			GuijobsminercheckcraftProcedure.execute(world, entity);
		}
		if (slot == 2 && changeType == 0) {

			GuijobsminercheckcraftProcedure.execute(world, entity);
		}
		if (slot == 3 && changeType == 0) {

			GuijobsminercheckcraftProcedure.execute(world, entity);
		}
		if (slot == 3 && changeType == 1) {

			GuijobsminercheckcraftProcedure.execute(world, entity);
		}
		if (slot == 4 && changeType == 0) {

			GuijobsminercheckcraftProcedure.execute(world, entity);
		}
		if (slot == 4 && changeType == 1) {

			GuijobsminercheckcraftProcedure.execute(world, entity);
		}
		if (slot == 5 && changeType == 0) {

			GuijobsminercheckcraftProcedure.execute(world, entity);
		}
		if (slot == 5 && changeType == 1) {

			GuijobsminercheckcraftProcedure.execute(world, entity);
		}
		if (slot == 6 && changeType == 0) {

			GuijobsminercheckcraftProcedure.execute(world, entity);
		}
		if (slot == 6 && changeType == 1) {

			GuijobsminercheckcraftProcedure.execute(world, entity);
		}
		if (slot == 7 && changeType == 0) {

			GuijobsminercheckcraftProcedure.execute(world, entity);
		}
		if (slot == 8 && changeType == 0) {

			GuijobsminercheckcraftProcedure.execute(world, entity);
		}
		if (slot == 8 && changeType == 1) {

			GuijobsminercheckcraftProcedure.execute(world, entity);
		}
		if (slot == 9 && changeType == 1) {

			CraftdoneminerProcedure.execute(world, entity);
		}
	}

	@SubscribeEvent
	public static void registerMessage(FMLCommonSetupEvent event) {
		PalamodMod.addNetworkMessage(JobsminercraftguiSlotMessage.TYPE, JobsminercraftguiSlotMessage.STREAM_CODEC, JobsminercraftguiSlotMessage::handleData);
	}
}
