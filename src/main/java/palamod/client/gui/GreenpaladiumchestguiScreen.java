package palamod.client.gui;

import palamod.world.inventory.GreenpaladiumchestguiMenu;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.GuiGraphics;

import java.util.HashMap;

import com.mojang.blaze3d.systems.RenderSystem;

public class GreenpaladiumchestguiScreen extends AbstractContainerScreen<GreenpaladiumchestguiMenu> {
	private final static HashMap<String, Object> guistate = GreenpaladiumchestguiMenu.guistate;
	private final Level world;
	private final int x, y, z;
	private final Player entity;

	public GreenpaladiumchestguiScreen(GreenpaladiumchestguiMenu container, Inventory inventory, Component text) {
		super(container, inventory, text);
		this.world = container.world;
		this.x = container.x;
		this.y = container.y;
		this.z = container.z;
		this.entity = container.entity;
		this.imageWidth = 306;
		this.imageHeight = 225;
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

		guiGraphics.blit(ResourceLocation.parse("palamod:textures/screens/greenpaladiumchestgui.png"), this.leftPos + -1, this.topPos + 0, 0, 0, 306, 225, 306, 225);

		guiGraphics.blit(ResourceLocation.parse("palamod:textures/screens/ring_to_set.png"), this.leftPos + 284, this.topPos + 112, 0, 0, 16, 16, 16, 16);

		guiGraphics.blit(ResourceLocation.parse("palamod:textures/screens/ring_gui.png"), this.leftPos + 269, this.topPos + 163, 0, 0, 16, 16, 16, 16);

		guiGraphics.blit(ResourceLocation.parse("palamod:textures/screens/ring_gui.png"), this.leftPos + 251, this.topPos + 163, 0, 0, 16, 16, 16, 16);

		guiGraphics.blit(ResourceLocation.parse("palamod:textures/screens/ring_gui.png"), this.leftPos + 251, this.topPos + 181, 0, 0, 16, 16, 16, 16);

		guiGraphics.blit(ResourceLocation.parse("palamod:textures/screens/ring_gui.png"), this.leftPos + 269, this.topPos + 181, 0, 0, 16, 16, 16, 16);

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
		guiGraphics.drawString(this.font, Component.translatable("gui.palamod.greenpaladiumchestgui.label_inventory"), 70, 130, -12829636, false);
		guiGraphics.drawString(this.font, Component.translatable("gui.palamod.greenpaladiumchestgui.label_rings"), 248, 146, -12829636, false);
	}

	@Override
	public void init() {
		super.init();
	}
}
