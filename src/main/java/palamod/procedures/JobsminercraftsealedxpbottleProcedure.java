package palamod.procedures;

import palamod.network.PalamodModVariables;

import palamod.init.PalamodModItems;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;

import java.util.function.Supplier;
import java.util.Map;

public class JobsminercraftsealedxpbottleProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		{
			PalamodModVariables.PlayerVariables _vars = entity.getData(PalamodModVariables.PLAYER_VARIABLES);
			_vars.jobs_get_text = "A bottle that";
			_vars.syncPlayerVariables(entity);
		}
		{
			PalamodModVariables.PlayerVariables _vars = entity.getData(PalamodModVariables.PLAYER_VARIABLES);
			_vars.jobs_get_text2 = "can stock xp";
			_vars.syncPlayerVariables(entity);
		}
		{
			PalamodModVariables.PlayerVariables _vars = entity.getData(PalamodModVariables.PLAYER_VARIABLES);
			_vars.jobs_get_text3 = "";
			_vars.syncPlayerVariables(entity);
		}
		{
			PalamodModVariables.PlayerVariables _vars = entity.getData(PalamodModVariables.PLAYER_VARIABLES);
			_vars.jobs_get_text4 = "";
			_vars.syncPlayerVariables(entity);
		}
		{
			PalamodModVariables.PlayerVariables _vars = entity.getData(PalamodModVariables.PLAYER_VARIABLES);
			_vars.jobs_get_text5 = "";
			_vars.syncPlayerVariables(entity);
		}
		{
			PalamodModVariables.PlayerVariables _vars = entity.getData(PalamodModVariables.PLAYER_VARIABLES);
			_vars.jobs_durability = "No durability";
			_vars.syncPlayerVariables(entity);
		}
		{
			PalamodModVariables.PlayerVariables _vars = entity.getData(PalamodModVariables.PLAYER_VARIABLES);
			_vars.lvl_required = "Lvl required : 50";
			_vars.syncPlayerVariables(entity);
		}
		if ((entity instanceof Player _playerHasItem ? _playerHasItem.getInventory().contains(new ItemStack(PalamodModItems.PALADIUM_INGOT.get())) : false) && (new Object() {
			public int getAmount(int sltid) {
				if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
					ItemStack stack = ((Slot) _slots.get(sltid)).getItem();
					if (stack != null)
						return stack.getCount();
				}
				return 0;
			}
		}.getAmount(7) == 0
				|| (entity instanceof Player _plrSlotItem && _plrSlotItem.containerMenu instanceof Supplier _splr && _splr.get() instanceof Map _slt ? ((Slot) _slt.get(7)).getItem() : ItemStack.EMPTY).getItem() == PalamodModItems.PALADIUM_INGOT.get()
						&& new Object() {
							public int getAmount(int sltid) {
								if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
									ItemStack stack = ((Slot) _slots.get(sltid)).getItem();
									if (stack != null)
										return stack.getCount();
								}
								return 0;
							}
						}.getAmount(7) < 64)) {
			if (entity instanceof Player _player) {
				ItemStack _stktoremove = new ItemStack(PalamodModItems.PALADIUM_INGOT.get());
				_player.getInventory().clearOrCountMatchingItems(p -> _stktoremove.getItem() == p.getItem(), 1, _player.inventoryMenu.getCraftSlots());
			}
			if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
				ItemStack _setstack = new ItemStack(PalamodModItems.PALADIUM_INGOT.get()).copy();
				_setstack.setCount((int) (new Object() {
					public int getAmount(int sltid) {
						if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
							ItemStack stack = ((Slot) _slots.get(sltid)).getItem();
							if (stack != null)
								return stack.getCount();
						}
						return 0;
					}
				}.getAmount(7) + 1));
				((Slot) _slots.get(7)).set(_setstack);
				_player.containerMenu.broadcastChanges();
			}
		}
		if ((entity instanceof Player _playerHasItem ? _playerHasItem.getInventory().contains(new ItemStack(PalamodModItems.PALADIUM_INGOT.get())) : false) && (new Object() {
			public int getAmount(int sltid) {
				if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
					ItemStack stack = ((Slot) _slots.get(sltid)).getItem();
					if (stack != null)
						return stack.getCount();
				}
				return 0;
			}
		}.getAmount(3) == 0
				|| (entity instanceof Player _plrSlotItem && _plrSlotItem.containerMenu instanceof Supplier _splr && _splr.get() instanceof Map _slt ? ((Slot) _slt.get(3)).getItem() : ItemStack.EMPTY).getItem() == PalamodModItems.PALADIUM_INGOT.get()
						&& new Object() {
							public int getAmount(int sltid) {
								if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
									ItemStack stack = ((Slot) _slots.get(sltid)).getItem();
									if (stack != null)
										return stack.getCount();
								}
								return 0;
							}
						}.getAmount(3) < 64)) {
			if (entity instanceof Player _player) {
				ItemStack _stktoremove = new ItemStack(PalamodModItems.PALADIUM_INGOT.get());
				_player.getInventory().clearOrCountMatchingItems(p -> _stktoremove.getItem() == p.getItem(), 1, _player.inventoryMenu.getCraftSlots());
			}
			if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
				ItemStack _setstack = new ItemStack(PalamodModItems.PALADIUM_INGOT.get()).copy();
				_setstack.setCount((int) (new Object() {
					public int getAmount(int sltid) {
						if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
							ItemStack stack = ((Slot) _slots.get(sltid)).getItem();
							if (stack != null)
								return stack.getCount();
						}
						return 0;
					}
				}.getAmount(3) + 1));
				((Slot) _slots.get(3)).set(_setstack);
				_player.containerMenu.broadcastChanges();
			}
		}
		if ((entity instanceof Player _playerHasItem ? _playerHasItem.getInventory().contains(new ItemStack(PalamodModItems.XPBOTTLE.get())) : false) && (new Object() {
			public int getAmount(int sltid) {
				if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
					ItemStack stack = ((Slot) _slots.get(sltid)).getItem();
					if (stack != null)
						return stack.getCount();
				}
				return 0;
			}
		}.getAmount(4) == 0
				|| (entity instanceof Player _plrSlotItem && _plrSlotItem.containerMenu instanceof Supplier _splr && _splr.get() instanceof Map _slt ? ((Slot) _slt.get(4)).getItem() : ItemStack.EMPTY).getItem() == PalamodModItems.XPBOTTLE.get()
						&& new Object() {
							public int getAmount(int sltid) {
								if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
									ItemStack stack = ((Slot) _slots.get(sltid)).getItem();
									if (stack != null)
										return stack.getCount();
								}
								return 0;
							}
						}.getAmount(4) < 64)) {
			if (entity instanceof Player _player) {
				ItemStack _stktoremove = new ItemStack(PalamodModItems.XPBOTTLE.get());
				_player.getInventory().clearOrCountMatchingItems(p -> _stktoremove.getItem() == p.getItem(), 1, _player.inventoryMenu.getCraftSlots());
			}
			if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
				ItemStack _setstack = new ItemStack(PalamodModItems.XPBOTTLE.get()).copy();
				_setstack.setCount((int) (new Object() {
					public int getAmount(int sltid) {
						if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
							ItemStack stack = ((Slot) _slots.get(sltid)).getItem();
							if (stack != null)
								return stack.getCount();
						}
						return 0;
					}
				}.getAmount(4) + 1));
				((Slot) _slots.get(4)).set(_setstack);
				_player.containerMenu.broadcastChanges();
			}
		}
		if ((entity instanceof Player _playerHasItem ? _playerHasItem.getInventory().contains(new ItemStack(PalamodModItems.PALADIUM_INGOT.get())) : false) && (new Object() {
			public int getAmount(int sltid) {
				if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
					ItemStack stack = ((Slot) _slots.get(sltid)).getItem();
					if (stack != null)
						return stack.getCount();
				}
				return 0;
			}
		}.getAmount(5) == 0
				|| (entity instanceof Player _plrSlotItem && _plrSlotItem.containerMenu instanceof Supplier _splr && _splr.get() instanceof Map _slt ? ((Slot) _slt.get(5)).getItem() : ItemStack.EMPTY).getItem() == PalamodModItems.PALADIUM_INGOT.get()
						&& new Object() {
							public int getAmount(int sltid) {
								if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
									ItemStack stack = ((Slot) _slots.get(sltid)).getItem();
									if (stack != null)
										return stack.getCount();
								}
								return 0;
							}
						}.getAmount(5) < 64)) {
			if (entity instanceof Player _player) {
				ItemStack _stktoremove = new ItemStack(PalamodModItems.PALADIUM_INGOT.get());
				_player.getInventory().clearOrCountMatchingItems(p -> _stktoremove.getItem() == p.getItem(), 1, _player.inventoryMenu.getCraftSlots());
			}
			if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
				ItemStack _setstack = new ItemStack(PalamodModItems.PALADIUM_INGOT.get()).copy();
				_setstack.setCount((int) (new Object() {
					public int getAmount(int sltid) {
						if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
							ItemStack stack = ((Slot) _slots.get(sltid)).getItem();
							if (stack != null)
								return stack.getCount();
						}
						return 0;
					}
				}.getAmount(5) + 1));
				((Slot) _slots.get(5)).set(_setstack);
				_player.containerMenu.broadcastChanges();
			}
		}
	}
}
