package palamod.client.gui;

import palamod.world.inventory.ExamplegrinderguiMenu;

import palamod.procedures.Grindersubprocessarrowsplit9Procedure;
import palamod.procedures.Grindersubprocessarrowsplit8Procedure;
import palamod.procedures.Grindersubprocessarrowsplit7Procedure;
import palamod.procedures.Grindersubprocessarrowsplit6Procedure;
import palamod.procedures.Grindersubprocessarrowsplit5Procedure;
import palamod.procedures.Grindersubprocessarrowsplit4Procedure;
import palamod.procedures.Grindersubprocessarrowsplit3Procedure;
import palamod.procedures.Grindersubprocessarrowsplit2Procedure;
import palamod.procedures.Grindersubprocessarrowsplit1Procedure;
import palamod.procedures.Grindersubprocessarrowsplit10Procedure;
import palamod.procedures.Grindersubprocessarrowinsertn4Procedure;
import palamod.procedures.Grindersubprocessarrowinsertn3Procedure;
import palamod.procedures.Grindersubprocessarrowinsertn2Procedure;
import palamod.procedures.Grindersubprocessarrowinsertn1Procedure;
import palamod.procedures.Grindersubprocessarrowinsertn0Procedure;
import palamod.procedures.Grinderguiarrow2n9Procedure;
import palamod.procedures.Grinderguiarrow2n8Procedure;
import palamod.procedures.Grinderguiarrow2n7Procedure;
import palamod.procedures.Grinderguiarrow2n5Procedure;
import palamod.procedures.Grinderguiarrow2n4Procedure;
import palamod.procedures.Grinderguiarrow2n3Procedure;
import palamod.procedures.Grinderguiarrow2n2Procedure;
import palamod.procedures.Grinderguiarrow2n1Procedure;
import palamod.procedures.Grinderguiarrow2n10Procedure;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.GuiGraphics;

import java.util.HashMap;

import com.mojang.blaze3d.systems.RenderSystem;

public class ExamplegrinderguiScreen extends AbstractContainerScreen<ExamplegrinderguiMenu> {
	private final static HashMap<String, Object> guistate = ExamplegrinderguiMenu.guistate;
	private final Level world;
	private final int x, y, z;
	private final Player entity;

	public ExamplegrinderguiScreen(ExamplegrinderguiMenu container, Inventory inventory, Component text) {
		super(container, inventory, text);
		this.world = container.world;
		this.x = container.x;
		this.y = container.y;
		this.z = container.z;
		this.entity = container.entity;
		this.imageWidth = 215;
		this.imageHeight = 163;
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

		guiGraphics.blit(ResourceLocation.parse("palamod:textures/screens/grindergui_v4.png"), this.leftPos + 0, this.topPos + 0, 0, 0, 215, 163, 215, 163);

		if (Grindersubprocessarrowinsertn0Procedure.execute(world, x, y, z)) {
			guiGraphics.blit(ResourceLocation.parse("palamod:textures/screens/arr0.png"), this.leftPos + 121, this.topPos + 23, 0, 0, 32, 16, 32, 16);
		}
		if (Grindersubprocessarrowinsertn1Procedure.execute(world, x, y, z)) {
			guiGraphics.blit(ResourceLocation.parse("palamod:textures/screens/arr1.png"), this.leftPos + 121, this.topPos + 23, 0, 0, 32, 16, 32, 16);
		}
		if (Grindersubprocessarrowinsertn2Procedure.execute(world, x, y, z)) {
			guiGraphics.blit(ResourceLocation.parse("palamod:textures/screens/arr2.png"), this.leftPos + 121, this.topPos + 23, 0, 0, 32, 16, 32, 16);
		}
		if (Grindersubprocessarrowinsertn3Procedure.execute(world, x, y, z)) {
			guiGraphics.blit(ResourceLocation.parse("palamod:textures/screens/arr3.png"), this.leftPos + 121, this.topPos + 23, 0, 0, 32, 16, 32, 16);
		}
		if (Grindersubprocessarrowinsertn4Procedure.execute(world, x, y, z)) {
			guiGraphics.blit(ResourceLocation.parse("palamod:textures/screens/arr4.png"), this.leftPos + 121, this.topPos + 23, 0, 0, 32, 16, 32, 16);
		}

		guiGraphics.blit(ResourceLocation.parse("palamod:textures/screens/arrow_right01.png"), this.leftPos + 53, this.topPos + 12, 0, 0, 16, 14, 16, 14);

		if (Grinderguiarrow2n1Procedure.execute(world, x, y, z)) {
			guiGraphics.blit(ResourceLocation.parse("palamod:textures/screens/arrow_right03.png"), this.leftPos + 53, this.topPos + 12, 0, 0, 16, 14, 16, 14);
		}
		if (Grinderguiarrow2n2Procedure.execute(world, x, y, z)) {
			guiGraphics.blit(ResourceLocation.parse("palamod:textures/screens/arrow_right05.png"), this.leftPos + 53, this.topPos + 12, 0, 0, 16, 14, 16, 14);
		}
		if (Grinderguiarrow2n3Procedure.execute(world, x, y, z)) {
			guiGraphics.blit(ResourceLocation.parse("palamod:textures/screens/arrow_right07.png"), this.leftPos + 53, this.topPos + 12, 0, 0, 16, 14, 16, 14);
		}
		if (Grinderguiarrow2n4Procedure.execute(world, x, y, z)) {
			guiGraphics.blit(ResourceLocation.parse("palamod:textures/screens/arrow_right09.png"), this.leftPos + 53, this.topPos + 12, 0, 0, 16, 14, 16, 14);
		}
		if (Grinderguiarrow2n5Procedure.execute(world, x, y, z)) {
			guiGraphics.blit(ResourceLocation.parse("palamod:textures/screens/arrow_right11.png"), this.leftPos + 53, this.topPos + 12, 0, 0, 16, 14, 16, 14);
		}

		guiGraphics.blit(ResourceLocation.parse("palamod:textures/screens/arrow_right13.png"), this.leftPos + 53, this.topPos + 12, 0, 0, 16, 14, 16, 14);

		if (Grinderguiarrow2n7Procedure.execute(world, x, y, z)) {
			guiGraphics.blit(ResourceLocation.parse("palamod:textures/screens/arrow_right15.png"), this.leftPos + 53, this.topPos + 12, 0, 0, 16, 14, 16, 14);
		}
		if (Grinderguiarrow2n8Procedure.execute(world, x, y, z)) {
			guiGraphics.blit(ResourceLocation.parse("palamod:textures/screens/arrow_right17.png"), this.leftPos + 53, this.topPos + 12, 0, 0, 16, 14, 16, 14);
		}
		if (Grinderguiarrow2n9Procedure.execute(world, x, y, z)) {
			guiGraphics.blit(ResourceLocation.parse("palamod:textures/screens/arrow_right19.png"), this.leftPos + 53, this.topPos + 12, 0, 0, 16, 14, 16, 14);
		}
		if (Grinderguiarrow2n10Procedure.execute(world, x, y, z)) {
			guiGraphics.blit(ResourceLocation.parse("palamod:textures/screens/arrow_right20.png"), this.leftPos + 53, this.topPos + 12, 0, 0, 16, 14, 16, 14);
		}

		guiGraphics.blit(ResourceLocation.parse("palamod:textures/screens/arrow_1red_new.png"), this.leftPos + 54, this.topPos + 62, 0, 0, 24, 10, 24, 10);

		if (Grindersubprocessarrowsplit1Procedure.execute(world, x, y, z)) {
			guiGraphics.blit(ResourceLocation.parse("palamod:textures/screens/arrow_3red_new.png"), this.leftPos + 54, this.topPos + 62, 0, 0, 24, 10, 24, 10);
		}
		if (Grindersubprocessarrowsplit2Procedure.execute(world, x, y, z)) {
			guiGraphics.blit(ResourceLocation.parse("palamod:textures/screens/arrow_5red_new.png"), this.leftPos + 54, this.topPos + 62, 0, 0, 24, 10, 24, 10);
		}
		if (Grindersubprocessarrowsplit3Procedure.execute(world, x, y, z)) {
			guiGraphics.blit(ResourceLocation.parse("palamod:textures/screens/arrow_7red_new.png"), this.leftPos + 54, this.topPos + 62, 0, 0, 24, 10, 24, 10);
		}
		if (Grindersubprocessarrowsplit4Procedure.execute(world, x, y, z)) {
			guiGraphics.blit(ResourceLocation.parse("palamod:textures/screens/arrow_9red_new.png"), this.leftPos + 54, this.topPos + 62, 0, 0, 24, 10, 24, 10);
		}
		if (Grindersubprocessarrowsplit5Procedure.execute(world, x, y, z)) {
			guiGraphics.blit(ResourceLocation.parse("palamod:textures/screens/arrow_11red_new.png"), this.leftPos + 54, this.topPos + 62, 0, 0, 24, 10, 24, 10);
		}
		if (Grindersubprocessarrowsplit6Procedure.execute(world, x, y, z)) {
			guiGraphics.blit(ResourceLocation.parse("palamod:textures/screens/arrow_13red_new.png"), this.leftPos + 54, this.topPos + 62, 0, 0, 24, 10, 24, 10);
		}
		if (Grindersubprocessarrowsplit7Procedure.execute(world, x, y, z)) {
			guiGraphics.blit(ResourceLocation.parse("palamod:textures/screens/arrow_15red_new.png"), this.leftPos + 54, this.topPos + 62, 0, 0, 24, 10, 24, 10);
		}
		if (Grindersubprocessarrowsplit8Procedure.execute(world, x, y, z)) {
			guiGraphics.blit(ResourceLocation.parse("palamod:textures/screens/arrow_17red_new.png"), this.leftPos + 54, this.topPos + 62, 0, 0, 24, 10, 24, 10);
		}
		if (Grindersubprocessarrowsplit9Procedure.execute(world, x, y, z)) {
			guiGraphics.blit(ResourceLocation.parse("palamod:textures/screens/arrow_18red_new.png"), this.leftPos + 54, this.topPos + 62, 0, 0, 24, 10, 24, 10);
		}
		if (Grindersubprocessarrowsplit10Procedure.execute(world, x, y, z)) {
			guiGraphics.blit(ResourceLocation.parse("palamod:textures/screens/arrow_19red_new.png"), this.leftPos + 54, this.topPos + 62, 0, 0, 24, 10, 24, 10);
		}

		guiGraphics.blit(ResourceLocation.parse("palamod:textures/screens/btn-all_off.png"), this.leftPos + 160, this.topPos + 63, 0, 0, 37, 7, 37, 7);

		guiGraphics.blit(ResourceLocation.parse("palamod:textures/screens/grinder_sprite_full.png"), this.leftPos + 152, this.topPos + 9, 0, 0, 51, 50, 2601, 50);

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
		guiGraphics.drawString(this.font, Component.translatable("gui.palamod.examplegrindergui.label_varg_num_100"), 149, 70, -12829636, false);
	}

	@Override
	public void init() {
		super.init();
	}
}
