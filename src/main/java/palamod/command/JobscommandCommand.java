
package palamod.command;

import palamod.procedures.JobsminersetxpProcedure;
import palamod.procedures.JobsminersetlevelProcedure;
import palamod.procedures.JobsmineraddxpProcedure;
import palamod.procedures.JobsmineraddlevelsProcedure;
import palamod.procedures.JobshuntersetxpProcedure;
import palamod.procedures.JobshuntersetlevelProcedure;
import palamod.procedures.JobshunteraddxpProcedure;
import palamod.procedures.JobshunteraddlevelProcedure;
import palamod.procedures.JobsfarmersetxpProcedure;
import palamod.procedures.JobsfarmersetlevelProcedure;
import palamod.procedures.JobsfarmeraddxpProcedure;
import palamod.procedures.JobsfarmeraddlevelProcedure;
import palamod.procedures.JobsalchisetxpProcedure;
import palamod.procedures.JobsalchisetlevelProcedure;
import palamod.procedures.JobsalchiaddxpProcedure;
import palamod.procedures.JobsalchiaddlevelProcedure;
import palamod.procedures.ConnectOpenjobsguiProcedure;

import org.checkerframework.checker.units.qual.s;

import net.neoforged.neoforge.event.RegisterCommandsEvent;
import net.neoforged.neoforge.common.util.FakePlayerFactory;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.core.Direction;
import net.minecraft.commands.Commands;

import com.mojang.brigadier.arguments.DoubleArgumentType;

@EventBusSubscriber
public class JobscommandCommand {
	@SubscribeEvent
	public static void registerCommand(RegisterCommandsEvent event) {
		event.getDispatcher().register(Commands.literal("jobs").requires(s -> s.hasPermission(2))
				.then(Commands.literal("miner").then(Commands.literal("set").then(Commands.argument("num", DoubleArgumentType.doubleArg()).then(Commands.literal("levels").executes(arguments -> {
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

					JobsminersetlevelProcedure.execute(world, arguments, entity);
					return 0;
				}))).then(Commands.argument("num", DoubleArgumentType.doubleArg()).then(Commands.literal("xp").executes(arguments -> {
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

					JobsminersetxpProcedure.execute(world, arguments, entity);
					return 0;
				})))).then(Commands.literal("add").then(Commands.argument("num", DoubleArgumentType.doubleArg()).then(Commands.literal("levels").executes(arguments -> {
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

					JobsmineraddlevelsProcedure.execute(world, arguments, entity);
					return 0;
				}))).then(Commands.argument("num", DoubleArgumentType.doubleArg()).then(Commands.literal("xp").executes(arguments -> {
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

					JobsmineraddxpProcedure.execute(world, arguments, entity);
					return 0;
				}))))).then(Commands.literal("farmer").then(Commands.literal("set").then(Commands.argument("num", DoubleArgumentType.doubleArg()).then(Commands.literal("levels").executes(arguments -> {
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

					JobsfarmersetlevelProcedure.execute(world, arguments, entity);
					return 0;
				}))).then(Commands.argument("num", DoubleArgumentType.doubleArg()).then(Commands.literal("xp").executes(arguments -> {
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

					JobsfarmersetxpProcedure.execute(world, arguments, entity);
					return 0;
				})))).then(Commands.literal("add").then(Commands.argument("num", DoubleArgumentType.doubleArg()).then(Commands.literal("levels").executes(arguments -> {
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

					JobsfarmeraddlevelProcedure.execute(world, arguments, entity);
					return 0;
				}))).then(Commands.argument("num", DoubleArgumentType.doubleArg()).then(Commands.literal("xp").executes(arguments -> {
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

					JobsfarmeraddxpProcedure.execute(world, arguments, entity);
					return 0;
				}))))).then(Commands.literal("hunter").then(Commands.literal("set").then(Commands.argument("num", DoubleArgumentType.doubleArg()).then(Commands.literal("levels").executes(arguments -> {
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

					JobshuntersetlevelProcedure.execute(world, arguments, entity);
					return 0;
				}))).then(Commands.argument("num", DoubleArgumentType.doubleArg()).then(Commands.literal("xp").executes(arguments -> {
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

					JobshuntersetxpProcedure.execute(world, arguments, entity);
					return 0;
				})))).then(Commands.literal("add").then(Commands.argument("num", DoubleArgumentType.doubleArg()).then(Commands.literal("levels").executes(arguments -> {
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

					JobshunteraddlevelProcedure.execute(world, arguments, entity);
					return 0;
				}))).then(Commands.argument("num", DoubleArgumentType.doubleArg()).then(Commands.literal("xp").executes(arguments -> {
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

					JobshunteraddxpProcedure.execute(world, arguments, entity);
					return 0;
				}))))).then(Commands.literal("alchimist").then(Commands.literal("set").then(Commands.argument("num", DoubleArgumentType.doubleArg()).then(Commands.literal("levels").executes(arguments -> {
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

					JobsalchisetlevelProcedure.execute(world, arguments, entity);
					return 0;
				}))).then(Commands.argument("num", DoubleArgumentType.doubleArg()).then(Commands.literal("xp").executes(arguments -> {
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

					JobsalchisetxpProcedure.execute(world, arguments, entity);
					return 0;
				})))).then(Commands.literal("add").then(Commands.argument("num", DoubleArgumentType.doubleArg()).then(Commands.literal("levels").executes(arguments -> {
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

					JobsalchiaddlevelProcedure.execute(world, arguments, entity);
					return 0;
				}))).then(Commands.argument("num", DoubleArgumentType.doubleArg()).then(Commands.literal("xp").executes(arguments -> {
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

					JobsalchiaddxpProcedure.execute(world, arguments, entity);
					return 0;
				}))))).then(Commands.literal("gui").executes(arguments -> {
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

					ConnectOpenjobsguiProcedure.execute(world, x, y, z, entity);
					return 0;
				})));
	}
}
