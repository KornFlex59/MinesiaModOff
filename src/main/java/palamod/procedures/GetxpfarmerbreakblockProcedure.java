package palamod.procedures;

import palamod.init.PalamodModBlocks;

import net.neoforged.neoforge.server.ServerLifecycleHooks;
import net.neoforged.fml.loading.FMLPaths;

import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.Entity;
import net.minecraft.core.BlockPos;
import net.minecraft.client.Minecraft;

import java.io.IOException;
import java.io.FileReader;
import java.io.File;
import java.io.BufferedReader;

public class GetxpfarmerbreakblockProcedure {
	public static double execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return 0;
		double output = 0;
		double lvl = 0;
		File jobs = new File("");
		com.google.gson.JsonObject main = new com.google.gson.JsonObject();
		jobs = new File((FMLPaths.GAMEDIR.get().toString() + "\\saves\\" + (world.isClientSide() ? Minecraft.getInstance().getSingleplayerServer().getWorldData().getLevelName() : ServerLifecycleHooks.getCurrentServer().getWorldData().getLevelName())
				+ "\\jobs\\" + entity.getUUID().toString()), File.separator + "jobs.json");
		{
			try {
				BufferedReader bufferedReader = new BufferedReader(new FileReader(jobs));
				StringBuilder jsonstringbuilder = new StringBuilder();
				String line;
				while ((line = bufferedReader.readLine()) != null) {
					jsonstringbuilder.append(line);
				}
				bufferedReader.close();
				main = new com.google.gson.Gson().fromJson(jsonstringbuilder.toString(), com.google.gson.JsonObject.class);
				lvl = main.get("lvl_farmer").getAsDouble();
				if (7 == ((world.getBlockState(BlockPos.containing(x, y, z))).getBlock().getStateDefinition().getProperty("blockstate") instanceof IntegerProperty _getip6 ? (world.getBlockState(BlockPos.containing(x, y, z))).getValue(_getip6) : -1)
						&& (world.getBlockState(BlockPos.containing(x, y, z))).getBlock() == Blocks.WHEAT) {
					output = 2;
				} else if (7 == ((world.getBlockState(BlockPos.containing(x, y, z))).getBlock().getStateDefinition().getProperty("blockstate") instanceof IntegerProperty _getip10
						? (world.getBlockState(BlockPos.containing(x, y, z))).getValue(_getip10)
						: -1) && (world.getBlockState(BlockPos.containing(x, y, z))).getBlock() == Blocks.POTATOES && 5 <= lvl) {
					output = 3;
				} else if (7 == ((world.getBlockState(BlockPos.containing(x, y, z))).getBlock().getStateDefinition().getProperty("blockstate") instanceof IntegerProperty _getip14
						? (world.getBlockState(BlockPos.containing(x, y, z))).getValue(_getip14)
						: -1) && (world.getBlockState(BlockPos.containing(x, y, z))).getBlock() == Blocks.CARROTS && 10 <= lvl) {
					output = 2.5;
				} else if ((world.getBlockState(BlockPos.containing(x, y, z))).getBlock() == Blocks.MELON && 15 <= lvl) {
					output = 4;
				} else if ((world.getBlockState(BlockPos.containing(x, y, z))).getBlock() == Blocks.CARVED_PUMPKIN && 30 <= lvl) {
					output = 5;
				} else if ((world.getBlockState(BlockPos.containing(x, y, z))).getBlock() == PalamodModBlocks.EGGPLANT_3.get() && 40 <= lvl) {
					output = 10;
				} else if ((world.getBlockState(BlockPos.containing(x, y, z))).getBlock() == PalamodModBlocks.ORANGEBLUE_4.get() && 100 <= lvl) {
					output = 2215;
				} else if ((world.getBlockState(BlockPos.containing(x, y, z))).getBlock() == PalamodModBlocks.CHERVIL_3.get() && 60 <= lvl) {
					output = 20;
				} else if ((world.getBlockState(BlockPos.containing(x, y, z))).getBlock() == PalamodModBlocks.KIWANO_4.get() && 80 <= lvl) {
					output = 200;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return output;
	}
}
