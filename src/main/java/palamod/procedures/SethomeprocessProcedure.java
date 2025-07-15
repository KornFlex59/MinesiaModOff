package palamod.procedures;

import net.neoforged.neoforge.server.ServerLifecycleHooks;
import net.neoforged.fml.loading.FMLPaths;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.network.chat.Component;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.CommandSource;
import net.minecraft.client.Minecraft;

import java.io.IOException;
import java.io.FileWriter;
import java.io.File;

import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.arguments.StringArgumentType;

public class SethomeprocessProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, CommandContext<CommandSourceStack> arguments, Entity entity) {
		if (entity == null)
			return;
		double lvl = 0;
		com.google.gson.JsonObject main = new com.google.gson.JsonObject();
		File jobs = new File("");
		File home = new File("");
		home = new File((FMLPaths.GAMEDIR.get().toString() + "\\saves\\" + (world.isClientSide() ? Minecraft.getInstance().getSingleplayerServer().getWorldData().getLevelName() : ServerLifecycleHooks.getCurrentServer().getWorldData().getLevelName())
				+ "\\home\\" + entity.getUUID().toString()), File.separator + (StringArgumentType.getString(arguments, "home_name") + ".json"));
		main.addProperty("home_x", x);
		main.addProperty("home_y", y);
		main.addProperty("home_z", z);
		main.addProperty("deleted", false);
		main.addProperty("dim_id", ("" + entity.level().dimension()));
		if (!home.exists()) {
			try {
				home.getParentFile().mkdirs();
				home.createNewFile();
			} catch (IOException exception) {
				exception.printStackTrace();
			}
		}
		{
			com.google.gson.Gson mainGSONBuilderVariable = new com.google.gson.GsonBuilder().setPrettyPrinting().create();
			try {
				FileWriter fileWriter = new FileWriter(home);
				fileWriter.write(mainGSONBuilderVariable.toJson(main));
				fileWriter.close();
			} catch (IOException exception) {
				exception.printStackTrace();
			}
		}
		if (world instanceof ServerLevel _level)
			_level.getServer().getCommands().performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, new Vec3(x, y, z), Vec2.ZERO, _level, 4, "", Component.literal(""), _level.getServer(), null).withSuppressedOutput(),
					("tellraw @p [\"\",{\"text\":\"[ Palamod ] :\",\"color\":\"dark_red\"},{\"text\":\" The home " + "" + StringArgumentType.getString(arguments, "home_name")
							+ " has been created at your position\",\"color\":\"gold\"},{\"text\":\"\\n \"}]"));
	}
}
