package palamod.procedures;

import palamod.network.PalamodModVariables;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.resources.ResourceKey;
import net.minecraft.core.registries.Registries;

public class Ametyste_spike_damageProcedure {
	public static void execute(LevelAccessor world, Entity entity) {
		if (entity == null)
			return;
		if (PalamodModVariables.cooltick_spike == 0) {
			entity.hurt(new DamageSource(world.holderOrThrow(ResourceKey.create(Registries.DAMAGE_TYPE, ResourceLocation.parse("palamod:spikedamage")))), 10);
			PalamodModVariables.cooltick_spike = 20;
		} else {
			PalamodModVariables.cooltick_spike = PalamodModVariables.cooltick_spike - 1;
		}
	}
}
