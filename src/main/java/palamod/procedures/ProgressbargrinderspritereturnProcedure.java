package palamod.procedures;

import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.core.BlockPos;

import java.io.File;

public class ProgressbargrinderspritereturnProcedure {
	public static double execute(LevelAccessor world, double x, double y, double z) {
		com.google.gson.JsonObject main = new com.google.gson.JsonObject();
		File file = new File("");
		File jobs = new File("");
		double nlm = 0;
		double xp = 0;
		return Math.round((new Object() {
			public double getValue(LevelAccessor world, BlockPos pos, String tag) {
				BlockEntity blockEntity = world.getBlockEntity(pos);
				if (blockEntity != null)
					return blockEntity.getPersistentData().getDouble(tag);
				return -1;
			}
		}.getValue(world, BlockPos.containing(x, y, z), "gnum")) / 2);
	}
}
