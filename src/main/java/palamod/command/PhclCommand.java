
package palamod.command;

import palamod.procedures.ForcechlanguageProcedure;

import net.neoforged.neoforge.event.RegisterCommandsEvent;
import net.neoforged.neoforge.common.util.FakePlayerFactory;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.core.Direction;
import net.minecraft.commands.Commands;

import java.util.HashMap;

import com.mojang.brigadier.arguments.StringArgumentType;

@EventBusSubscriber
public class PhclCommand {
	@SubscribeEvent
	public static void registerCommand(RegisterCommandsEvent event) {
		event.getDispatcher().register(Commands.literal("phcl")

				.then(Commands.argument("arguments", StringArgumentType.greedyString()).executes(arguments -> {
					Level world = arguments.getSource().getUnsidedLevel();
					double x = arguments.getSource().getPosition().x();
					double y = arguments.getSource().getPosition().y();
					double z = arguments.getSource().getPosition().z();
					Entity entity = arguments.getSource().getEntity();
					if (entity == null && world instanceof ServerLevel _servLevel)
						entity = FakePlayerFactory.getMinecraft(_servLevel);
					Direction direction = Direction.DOWN;
					if (entity != null)
						direction = entity.getDirection();
					HashMap<String, String> cmdparams = new HashMap<>();
					int index = -1;
					for (String param : arguments.getInput().split("\\s+")) {
						if (index >= 0)
							cmdparams.put(Integer.toString(index), param);
						index++;
					}

					ForcechlanguageProcedure.execute(world, x, y, z, entity);
					return 0;
				})).executes(arguments -> {
					Level world = arguments.getSource().getUnsidedLevel();
					double x = arguments.getSource().getPosition().x();
					double y = arguments.getSource().getPosition().y();
					double z = arguments.getSource().getPosition().z();
					Entity entity = arguments.getSource().getEntity();
					if (entity == null && world instanceof ServerLevel _servLevel)
						entity = FakePlayerFactory.getMinecraft(_servLevel);
					Direction direction = Direction.DOWN;
					if (entity != null)
						direction = entity.getDirection();
					HashMap<String, String> cmdparams = new HashMap<>();
					int index = -1;
					for (String param : arguments.getInput().split("\\s+")) {
						if (index >= 0)
							cmdparams.put(Integer.toString(index), param);
						index++;
					}

					ForcechlanguageProcedure.execute(world, x, y, z, entity);
					return 0;
				}));
	}
}
