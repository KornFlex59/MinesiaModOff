package palamod.client.gui;

import palamod.world.inventory.PaladumfurnaceMenu;

import palamod.procedures.ReturntimerspritepaladiumfurnaceProcedure;
import palamod.procedures.ReturnfuelspritepaladiumfurnaceProcedure;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.util.Mth;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.GuiGraphics;

import java.util.HashMap;

import com.mojang.blaze3d.systems.RenderSystem;

public class PaladumfurnaceScreen extends AbstractContainerScreen<PaladumfurnaceMenu> {
	private final static HashMap<String, Object> guistate = PaladumfurnaceMenu.guistate;
	private final Level world;
	private final int x, y, z;
	private final Player entity;

	public PaladumfurnaceScreen(PaladumfurnaceMenu container, Inventory inventory, Component text) {
		super(container, inventory, text);
		this.world = container.world;
		this.x = container.x;
		this.y = container.y;
		this.z = container.z;
		this.entity = container.entity;
		this.imageWidth = 180;
		this.imageHeight = 166;
	}

	@Override
	public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
		this.renderBackground(guiGraphics, mouseX, mouseY, partialTicks);
		super.render(guiGraphics, mouseX, mouseY, partialTicks);
		this.renderTooltip(guiGraphics, mouseX, mouseY);
	}

	@Override
	protected void renderBg(GuiGraphics guiGraphics, float partialTicks, int gx, int gy) {
		RenderSystem.setShaderColor(1, 1, 1, 1);
		RenderSystem.enableBlend();
		RenderSystem.defaultBlendFunc();

		guiGraphics.blit(ResourceLocation.parse("palamod:textures/screens/paladiumfurnace.png"), this.leftPos + 0, this.topPos + 0, 0, 0, 180, 166, 180, 166);

		guiGraphics.blit(ResourceLocation.parse("palamod:textures/screens/paladium_upgrade.png"), this.leftPos + 14, this.topPos + 41, 0, 0, 16, 16, 16, 16);

		guiGraphics.blit(ResourceLocation.parse("palamod:textures/screens/fire_furnace.png"), this.leftPos + 60, this.topPos + 41, Mth.clamp((int) ReturnfuelspritepaladiumfurnaceProcedure.execute(world, x, y, z) * 16, 0, 224), 0, 16, 16, 240, 16);

		guiGraphics.blit(ResourceLocation.parse("palamod:textures/screens/arrow_right_furnace_sprite.png"), this.leftPos + 87, this.topPos + 42, Mth.clamp((int) ReturntimerspritepaladiumfurnaceProcedure.execute(world, x, y, z) * 22, 0, 484), 0, 22,
				15, 506, 15);

		RenderSystem.disableBlend();
	}

	@Override
	public boolean keyPressed(int key, int b, int c) {
		if (key == 256) {
			this.minecraft.player.closeContainer();
			return true;
		}
		return super.keyPressed(key, b, c);
	}

	@Override
	protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {
		guiGraphics.drawString(this.font, Component.translatable("gui.palamod.paladumfurnace.label_paladium_furnace"), 46, 4, -1, false);
		guiGraphics.drawString(this.font, Component.translatable("gui.palamod.paladumfurnace.label_inventory"), 7, 73, -1, false);
	}

	@Override
	public void init() {
		super.init();
	}
}
