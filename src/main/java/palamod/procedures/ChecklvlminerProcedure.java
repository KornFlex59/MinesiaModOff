package palamod.procedures;

import palamod.init.PalamodModItems;

import net.neoforged.neoforge.server.ServerLifecycleHooks;
import net.neoforged.neoforge.items.ItemHandlerHelper;
import net.neoforged.fml.loading.FMLPaths;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.GameType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.network.chat.Component;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.CommandSource;
import net.minecraft.client.Minecraft;

import java.io.IOException;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.File;
import java.io.BufferedReader;

public class ChecklvlminerProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		double money_add = 0;
		boolean money_getadd = false;
		com.google.gson.JsonObject main_jobs = new com.google.gson.JsonObject();
		com.google.gson.JsonObject main_money = new com.google.gson.JsonObject();
		File jobs = new File("");
		File money = new File("");
		File cache = new File("");
		File lu_jobs = new File("");
		jobs = new File((FMLPaths.GAMEDIR.get().toString() + "\\saves\\" + (world.isClientSide() ? Minecraft.getInstance().getSingleplayerServer().getWorldData().getLevelName() : ServerLifecycleHooks.getCurrentServer().getWorldData().getLevelName())
				+ "\\jobs\\" + entity.getUUID().toString()), File.separator + "jobs.json");
		money = new File((FMLPaths.GAMEDIR.get().toString() + "/serverconfig/palamod/money/"), File.separator + (entity.getUUID().toString() + ".json"));
		if (jobs.exists() && !(new Object() {
			public boolean checkGamemode(Entity _ent) {
				if (_ent instanceof ServerPlayer _serverPlayer) {
					return _serverPlayer.gameMode.getGameModeForPlayer() == GameType.CREATIVE;
				} else if (_ent.level().isClientSide() && _ent instanceof Player _player) {
					return Minecraft.getInstance().getConnection().getPlayerInfo(_player.getGameProfile().getId()) != null && Minecraft.getInstance().getConnection().getPlayerInfo(_player.getGameProfile().getId()).getGameMode() == GameType.CREATIVE;
				}
				return false;
			}
		}.checkGamemode(entity)) && money.exists()) {
			{
				try {
					BufferedReader bufferedReader = new BufferedReader(new FileReader(jobs));
					StringBuilder jsonstringbuilder = new StringBuilder();
					String line;
					while ((line = bufferedReader.readLine()) != null) {
						jsonstringbuilder.append(line);
					}
					bufferedReader.close();
					main_jobs = new com.google.gson.Gson().fromJson(jsonstringbuilder.toString(), com.google.gson.JsonObject.class);
					if (main_jobs.get("next_level_miner").getAsDouble() <= main_jobs.get("xp_miner").getAsDouble()) {
						main_jobs.addProperty("lvl_miner", (1 + main_jobs.get("lvl_miner").getAsDouble()));
						main_jobs.addProperty("xp_miner", (main_jobs.get("xp_miner").getAsDouble() - main_jobs.get("next_level_miner").getAsDouble()));
						main_jobs.addProperty("next_level_miner", GetnextlevelxpProcedure.execute(world, entity));
						if (entity instanceof Player _player) {
							ItemStack _setstack = new ItemStack(PalamodModItems.PALADIUM_INGOT.get()).copy();
							_setstack.setCount((int) (1 + Math.floor(main_jobs.get("lvl_miner").getAsDouble() / 2)));
							ItemHandlerHelper.giveItemToPlayer(_player, _setstack);
						}
						if (entity instanceof Player _player) {
							ItemStack _setstack = new ItemStack(PalamodModItems.TRIXIUM.get()).copy();
							_setstack.setCount((int) main_jobs.get("lvl_miner").getAsDouble());
							ItemHandlerHelper.giveItemToPlayer(_player, _setstack);
						}
						if (world instanceof ServerLevel _level)
							_level.getServer().getCommands().performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, new Vec3(x, y, z), Vec2.ZERO, _level, 4, "", Component.literal(""), _level.getServer(), null).withSuppressedOutput(),
									("tellraw @p [\"\",{\"text\":\"[ Palamod ] :\",\"color\":\"dark_red\"},{\"text\":\" " + "" + Component.translatable("palamod.procedure.jobswinlvl_miner1").getString() + " \\n "
											+ Component.translatable("palamod.procedure.jobswinlvl_miner2").getString() + " " + Math.round(main_jobs.get("lvl_miner").getAsDouble()) + ","
											+ Component.translatable("palamod.procedure.jobswinlvl_miner3").getString() + " " + Math.round(1000) + "$\",\"color\":\"gold\"}]"));
						if (world instanceof ServerLevel _level)
							_level.getServer().getCommands().performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, new Vec3(x, y, z), Vec2.ZERO, _level, 4, "", Component.literal(""), _level.getServer(), null).withSuppressedOutput(),
									"title @p times 20 140 40");
						if (world instanceof ServerLevel _level)
							_level.getServer().getCommands().performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, new Vec3(x, y, z), Vec2.ZERO, _level, 4, "", Component.literal(""), _level.getServer(), null).withSuppressedOutput(),
									("title @p subtitle [\"\",{\"text\":\"You Gain 1 \",\"color\":\"gold\"},{\"text\":\"miner \",\"color\":\"dark_blue\"},{\"text\":\"level, you are at level \",\"color\":\"gold\"},{\"text\":\"" + ""
											+ Math.round(main_jobs.get("lvl_miner").getAsDouble()) + "\",\"color\":\"dark_red\"},{\"text\":\".\",\"color\":\"gold\"}]"));
						if (world instanceof ServerLevel _level)
							_level.getServer().getCommands().performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, new Vec3(x, y, z), Vec2.ZERO, _level, 4, "", Component.literal(""), _level.getServer(), null).withSuppressedOutput(),
									"title @p title {\"text\":\"Congratutlation\",\"color\":\"dark_green\"}");
						money_getadd = true;
						money_add = 2 * (main_jobs.get("lvl_miner").getAsDouble() + 1);
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			{
				com.google.gson.Gson mainGSONBuilderVariable = new com.google.gson.GsonBuilder().setPrettyPrinting().create();
				try {
					FileWriter fileWriter = new FileWriter(jobs);
					fileWriter.write(mainGSONBuilderVariable.toJson(main_jobs));
					fileWriter.close();
				} catch (IOException exception) {
					exception.printStackTrace();
				}
			}
		}
		if (money.exists()) {
			{
				try {
					BufferedReader bufferedReader = new BufferedReader(new FileReader(money));
					StringBuilder jsonstringbuilder = new StringBuilder();
					String line;
					while ((line = bufferedReader.readLine()) != null) {
						jsonstringbuilder.append(line);
					}
					bufferedReader.close();
					main_money = new com.google.gson.Gson().fromJson(jsonstringbuilder.toString(), com.google.gson.JsonObject.class);
					if (money_getadd) {
						main_money.addProperty("money", (main_money.get("money").getAsDouble() + money_add));
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			{
				com.google.gson.Gson mainGSONBuilderVariable = new com.google.gson.GsonBuilder().setPrettyPrinting().create();
				try {
					FileWriter fileWriter = new FileWriter(money);
					fileWriter.write(mainGSONBuilderVariable.toJson(main_money));
					fileWriter.close();
				} catch (IOException exception) {
					exception.printStackTrace();
				}
			}
		}
	}
}
