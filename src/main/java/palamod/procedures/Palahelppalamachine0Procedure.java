package palamod.procedures;

import net.minecraft.world.entity.Entity;

public class Palahelppalamachine0Procedure {
	public static String execute(Entity entity) {
		if (entity == null)
			return "";
		if ((entity.getPersistentData().getString("language")).equals("french")) {
			return "La palamachine permet de crafter";
		}
		return "The palamachine allow you to craft";
	}
}
