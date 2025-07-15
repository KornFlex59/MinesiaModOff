
package palamod.item;

import palamod.procedures.PaladiumArmorLeggingsTickEventProcedure;
import palamod.procedures.PaladiumArmorHelmetTickEventProcedure;
import palamod.procedures.PaladiumArmorBootsTickEventProcedure;
import palamod.procedures.PaladiumArmorBodyTickEventProcedure;

import palamod.init.PalamodModItems;

import net.neoforged.neoforge.registries.RegisterEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;

import net.minecraft.world.level.Level;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.registries.Registries;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.Holder;
import net.minecraft.Util;

import java.util.List;
import java.util.EnumMap;

import com.google.common.collect.Iterables;

@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD)
public abstract class Paladiumarmorcustom8Item extends ArmorItem {
	public static Holder<ArmorMaterial> ARMOR_MATERIAL = null;

	@SubscribeEvent
	public static void registerArmorMaterial(RegisterEvent event) {
		event.register(Registries.ARMOR_MATERIAL, registerHelper -> {
			ArmorMaterial armorMaterial = new ArmorMaterial(Util.make(new EnumMap<>(ArmorItem.Type.class), map -> {
				map.put(ArmorItem.Type.BOOTS, 7);
				map.put(ArmorItem.Type.LEGGINGS, 8);
				map.put(ArmorItem.Type.CHESTPLATE, 8);
				map.put(ArmorItem.Type.HELMET, 7);
				map.put(ArmorItem.Type.BODY, 8);
			}), 55, DeferredHolder.create(Registries.SOUND_EVENT, ResourceLocation.parse("item.armor.equip_diamond")), () -> Ingredient.of(new ItemStack(PalamodModItems.PALADIUM_INGOT.get())),
					List.of(new ArmorMaterial.Layer(ResourceLocation.parse("palamod:paladium_custom_8"))), 3.5f, 0.15f);
			registerHelper.register(ResourceLocation.parse("palamod:paladiumarmorcustom_8"), armorMaterial);
			ARMOR_MATERIAL = BuiltInRegistries.ARMOR_MATERIAL.wrapAsHolder(armorMaterial);
		});
	}

	public Paladiumarmorcustom8Item(ArmorItem.Type type, Item.Properties properties) {
		super(ARMOR_MATERIAL, type, properties);
	}

	public static class Helmet extends Paladiumarmorcustom8Item {
		public Helmet() {
			super(ArmorItem.Type.HELMET, new Item.Properties().durability(ArmorItem.Type.HELMET.getDurability(260)));
		}

		@Override
		public void inventoryTick(ItemStack itemstack, Level world, Entity entity, int slot, boolean selected) {
			super.inventoryTick(itemstack, world, entity, slot, selected);
			if (entity instanceof Player player && Iterables.contains(player.getArmorSlots(), itemstack)) {
				PaladiumArmorHelmetTickEventProcedure.execute(entity);
			}
		}
	}

	public static class Chestplate extends Paladiumarmorcustom8Item {
		public Chestplate() {
			super(ArmorItem.Type.CHESTPLATE, new Item.Properties().durability(ArmorItem.Type.CHESTPLATE.getDurability(260)));
		}

		@Override
		public void inventoryTick(ItemStack itemstack, Level world, Entity entity, int slot, boolean selected) {
			super.inventoryTick(itemstack, world, entity, slot, selected);
			if (entity instanceof Player player && Iterables.contains(player.getArmorSlots(), itemstack)) {
				PaladiumArmorBodyTickEventProcedure.execute(entity);
			}
		}
	}

	public static class Leggings extends Paladiumarmorcustom8Item {
		public Leggings() {
			super(ArmorItem.Type.LEGGINGS, new Item.Properties().durability(ArmorItem.Type.LEGGINGS.getDurability(260)));
		}

		@Override
		public void inventoryTick(ItemStack itemstack, Level world, Entity entity, int slot, boolean selected) {
			super.inventoryTick(itemstack, world, entity, slot, selected);
			if (entity instanceof Player player && Iterables.contains(player.getArmorSlots(), itemstack)) {
				PaladiumArmorLeggingsTickEventProcedure.execute(entity);
			}
		}
	}

	public static class Boots extends Paladiumarmorcustom8Item {
		public Boots() {
			super(ArmorItem.Type.BOOTS, new Item.Properties().durability(ArmorItem.Type.BOOTS.getDurability(260)));
		}

		@Override
		public void inventoryTick(ItemStack itemstack, Level world, Entity entity, int slot, boolean selected) {
			super.inventoryTick(itemstack, world, entity, slot, selected);
			if (entity instanceof Player player && Iterables.contains(player.getArmorSlots(), itemstack)) {
				PaladiumArmorBootsTickEventProcedure.execute(entity);
			}
		}
	}
}
