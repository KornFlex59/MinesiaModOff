package palamod.client.gui;

import palamod.world.inventory.ForgepalahelpguiMenu;

import palamod.procedures.Palahelpforge3Procedure;
import palamod.procedures.Palahelpforge2Procedure;
import palamod.procedures.Palahelpforge1Procedure;
import palamod.procedures.Palahelpforge0Procedure;

import palamod.network.ForgepalahelpguiButtonMessage;

import net.neoforged.neoforge.network.PacketDistributor;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.components.WidgetSprites;
import net.minecraft.client.gui.components.ImageButton;
import net.minecraft.client.gui.GuiGraphics;

import java.util.HashMap;

import com.mojang.blaze3d.systems.RenderSystem;

public class ForgepalahelpguiScreen extends AbstractContainerScreen<ForgepalahelpguiMenu> {
	private final static HashMap<String, Object> guistate = ForgepalahelpguiMenu.guistate;
	private final Level world;
	private final int x, y, z;
	private final Player entity;
	ImageButton imagebutton_home_pixel_adminshop;
	ImageButton imagebutton_cross_no_button;
	ImageButton imagebutton_example_gui_button;
	ImageButton imagebutton_arrow_adminshop;

	public ForgepalahelpguiScreen(ForgepalahelpguiMenu container, Inventory inventory, Component text) {
		super(container, inventory, text);
		this.world = container.world;
		this.x = container.x;
		this.y = container.y;
		this.z = container.z;
		this.entity = container.entity;
		this.imageWidth = 300;
		this.imageHeight = 175;
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

		guiGraphics.blit(ResourceLocation.parse("palamod:textures/screens/forgepalahelpgui.png"), this.leftPos + -1, this.topPos + 0, 0, 0, 300, 175, 300, 175);

		guiGraphics.blit(ResourceLocation.parse("palamod:textures/screens/left_gray_line.png"), this.leftPos + -1, this.topPos + 0, 0, 0, 100, 24, 100, 24);

		guiGraphics.blit(ResourceLocation.parse("palamod:textures/screens/mid_gray_line.png"), this.leftPos + 99, this.topPos + 0, 0, 0, 100, 24, 100, 24);

		guiGraphics.blit(ResourceLocation.parse("palamod:textures/screens/right_gray_line.png"), this.leftPos + 199, this.topPos + 0, 0, 0, 100, 24, 100, 24);

		guiGraphics.blit(ResourceLocation.parse("palamod:textures/screens/forge_front_on.png"), this.leftPos + 7, this.topPos + 4, 0, 0, 16, 16, 16, 16);

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
		guiGraphics.drawString(this.font, Component.translatable("gui.palamod.forgepalahelpgui.label_paladium_forge"), 76, 7, -1, false);
		guiGraphics.drawString(this.font,

				Palahelpforge0Procedure.execute(entity), 3, 55, -12829636, false);
		guiGraphics.drawString(this.font,

				Palahelpforge1Procedure.execute(entity), 4, 68, -12829636, false);
		guiGraphics.drawString(this.font,

				Palahelpforge2Procedure.execute(entity), 3, 81, -12829636, false);
		guiGraphics.drawString(this.font,

				Palahelpforge3Procedure.execute(entity), 3, 94, -12829636, false);
		guiGraphics.drawString(this.font, Component.translatable("gui.palamod.forgepalahelpgui.label_an_example_gui_is_available_next"), 4, 157, -12829636, false);
	}

	@Override
	public void init() {
		super.init();
		imagebutton_home_pixel_adminshop = new ImageButton(this.leftPos + 215, this.topPos + 4, 16, 16,
				new WidgetSprites(ResourceLocation.parse("palamod:textures/screens/home_pixel_adminshop.png"), ResourceLocation.parse("palamod:textures/screens/pointec_home_pixel_adminshop.png")), e -> {
					if (true) {
						PacketDistributor.sendToServer(new ForgepalahelpguiButtonMessage(0, x, y, z));
						ForgepalahelpguiButtonMessage.handleButtonAction(entity, 0, x, y, z);
					}
				}) {
			@Override
			public void renderWidget(GuiGraphics guiGraphics, int x, int y, float partialTicks) {
				guiGraphics.blit(sprites.get(isActive(), isHoveredOrFocused()), getX(), getY(), 0, 0, width, height, width, height);
			}
		};
		guistate.put("button:imagebutton_home_pixel_adminshop", imagebutton_home_pixel_adminshop);
		this.addRenderableWidget(imagebutton_home_pixel_adminshop);
		imagebutton_cross_no_button = new ImageButton(this.leftPos + 278, this.topPos + 4, 16, 16,
				new WidgetSprites(ResourceLocation.parse("palamod:textures/screens/cross_no_button.png"), ResourceLocation.parse("palamod:textures/screens/pointed_cross_no_button.png")), e -> {
					if (true) {
						PacketDistributor.sendToServer(new ForgepalahelpguiButtonMessage(1, x, y, z));
						ForgepalahelpguiButtonMessage.handleButtonAction(entity, 1, x, y, z);
					}
				}) {
			@Override
			public void renderWidget(GuiGraphics guiGraphics, int x, int y, float partialTicks) {
				guiGraphics.blit(sprites.get(isActive(), isHoveredOrFocused()), getX(), getY(), 0, 0, width, height, width, height);
			}
		};
		guistate.put("button:imagebutton_cross_no_button", imagebutton_cross_no_button);
		this.addRenderableWidget(imagebutton_cross_no_button);
		imagebutton_example_gui_button = new ImageButton(this.leftPos + 257, this.topPos + 4, 16, 16,
				new WidgetSprites(ResourceLocation.parse("palamod:textures/screens/example_gui_button.png"), ResourceLocation.parse("palamod:textures/screens/example_gui_button_poi.png")), e -> {
					if (true) {
						PacketDistributor.sendToServer(new ForgepalahelpguiButtonMessage(2, x, y, z));
						ForgepalahelpguiButtonMessage.handleButtonAction(entity, 2, x, y, z);
					}
				}) {
			@Override
			public void renderWidget(GuiGraphics guiGraphics, int x, int y, float partialTicks) {
				guiGraphics.blit(sprites.get(isActive(), isHoveredOrFocused()), getX(), getY(), 0, 0, width, height, width, height);
			}
		};
		guistate.put("button:imagebutton_example_gui_button", imagebutton_example_gui_button);
		this.addRenderableWidget(imagebutton_example_gui_button);
		imagebutton_arrow_adminshop = new ImageButton(this.leftPos + 235, this.topPos + 4, 16, 16,
				new WidgetSprites(ResourceLocation.parse("palamod:textures/screens/arrow_adminshop.png"), ResourceLocation.parse("palamod:textures/screens/arrow_adminshop_poi.png")), e -> {
					if (true) {
						PacketDistributor.sendToServer(new ForgepalahelpguiButtonMessage(3, x, y, z));
						ForgepalahelpguiButtonMessage.handleButtonAction(entity, 3, x, y, z);
					}
				}) {
			@Override
			public void renderWidget(GuiGraphics guiGraphics, int x, int y, float partialTicks) {
				guiGraphics.blit(sprites.get(isActive(), isHoveredOrFocused()), getX(), getY(), 0, 0, width, height, width, height);
			}
		};
		guistate.put("button:imagebutton_arrow_adminshop", imagebutton_arrow_adminshop);
		this.addRenderableWidget(imagebutton_arrow_adminshop);
	}
}
