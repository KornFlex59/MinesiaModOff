package palamod.procedures;

import net.minecraft.world.entity.Entity;

public class MinegeprocesssetlayerendiumonProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		entity.getPersistentData().putBoolean("Minage_setlayer_dynamic_enda_hide", false);
		entity.getPersistentData().putBoolean("Minage_setlayer_dynamic_enda_", true);
	}
}
