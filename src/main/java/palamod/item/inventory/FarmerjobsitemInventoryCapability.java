package palamod.item.inventory;

import palamod.init.PalamodModItems;

import palamod.client.gui.JobsfarmercraftguiScreen;

import net.neoforged.neoforge.items.ComponentItemHandler;
import net.neoforged.neoforge.event.entity.item.ItemTossEvent;
import net.neoforged.neoforge.common.MutableDataComponentHolder;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.api.distmarker.Dist;

import net.minecraft.world.item.ItemStack;
import net.minecraft.core.component.DataComponents;
import net.minecraft.client.Minecraft;

import javax.annotation.Nonnull;

@EventBusSubscriber(Dist.CLIENT)
public class FarmerjobsitemInventoryCapability extends ComponentItemHandler {
	@SubscribeEvent
	@OnlyIn(Dist.CLIENT)
	public static void onItemDropped(ItemTossEvent event) {
		if (event.getEntity().getItem().getItem() == PalamodModItems.FARMERJOBSITEM.get()) {
			if (Minecraft.getInstance().screen instanceof JobsfarmercraftguiScreen) {
				Minecraft.getInstance().player.closeContainer();
			}
		}
	}

	public FarmerjobsitemInventoryCapability(MutableDataComponentHolder parent) {
		super(parent, DataComponents.CONTAINER, 10);
	}

	@Override
	public int getSlotLimit(int slot) {
		return 64;
	}

	@Override
	public boolean isItemValid(int slot, @Nonnull ItemStack stack) {
		return stack.getItem() != PalamodModItems.FARMERJOBSITEM.get();
	}

	@Override
	public ItemStack getStackInSlot(int slot) {
		return super.getStackInSlot(slot).copy();
	}
}
