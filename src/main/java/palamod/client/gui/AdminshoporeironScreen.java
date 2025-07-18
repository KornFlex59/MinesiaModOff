package palamod.client.gui;

import palamod.world.inventory.AdminshoporeironMenu;

import palamod.procedures.ReturnadminshoporemenuProcedure;
import palamod.procedures.ReturnadminshopmainmenuProcedure;
import palamod.procedures.ClosetheguitransProcedure;
import palamod.procedures.AdshoppreviewamountironProcedure;

import palamod.network.AdminshoporeironButtonMessage;

import net.neoforged.neoforge.network.PacketDistributor;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.components.WidgetSprites;
import net.minecraft.client.gui.components.ImageButton;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.Minecraft;

import java.util.HashMap;

import com.mojang.blaze3d.systems.RenderSystem;

public class AdminshoporeironScreen extends AbstractContainerScreen<AdminshoporeironMenu> {
	private final static HashMap<String, Object> guistate = AdminshoporeironMenu.guistate;
	private final Level world;
	private final int x, y, z;
	private final Player entity;
	EditBox number_buy;
	Button button_buy;
	Button button_sell;
	ImageButton imagebutton_cross_no_button;
	ImageButton imagebutton_arrow_adminshop;
	ImageButton imagebutton_home_pixel_adminshop;

	public AdminshoporeironScreen(AdminshoporeironMenu container, Inventory inventory, Component text) {
		super(container, inventory, text);
		this.world = container.world;
		this.x = container.x;
		this.y = container.y;
		this.z = container.z;
		this.entity = container.entity;
		this.imageWidth = 176;
		this.imageHeight = 166;
	}

	@Override
	public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
		this.renderBackground(guiGraphics, mouseX, mouseY, partialTicks);
		super.render(guiGraphics, mouseX, mouseY, partialTicks);
		number_buy.render(guiGraphics, mouseX, mouseY, partialTicks);
		this.renderTooltip(guiGraphics, mouseX, mouseY);
		if (mouseX > leftPos + 155 && mouseX < leftPos + 171 && mouseY > topPos + 3 && mouseY < topPos + 19)
			guiGraphics.renderTooltip(font, Component.literal(ClosetheguitransProcedure.execute()), mouseX, mouseY);
		if (mouseX > leftPos + 138 && mouseX < leftPos + 153 && mouseY > topPos + 5 && mouseY < topPos + 19)
			guiGraphics.renderTooltip(font, Component.literal(ReturnadminshoporemenuProcedure.execute()), mouseX, mouseY);
		if (mouseX > leftPos + 123 && mouseX < leftPos + 135 && mouseY > topPos + 4 && mouseY < topPos + 19)
			guiGraphics.renderTooltip(font, Component.literal(ReturnadminshopmainmenuProcedure.execute()), mouseX, mouseY);
	}

	@Override
	protected void renderBg(GuiGraphics guiGraphics, float partialTicks, int gx, int gy) {
		RenderSystem.setShaderColor(1, 1, 1, 1);
		RenderSystem.enableBlend();
		RenderSystem.defaultBlendFunc();

		guiGraphics.blit(ResourceLocation.parse("palamod:textures/screens/gui176_166.png"), this.leftPos + -1, this.topPos + 0, 0, 0, 176, 166, 176, 166);

		guiGraphics.blit(ResourceLocation.parse("palamod:textures/screens/left_gray_line.png"), this.leftPos + -1, this.topPos + 0, 0, 0, 100, 24, 100, 24);

		guiGraphics.blit(ResourceLocation.parse("palamod:textures/screens/right_gray_line.png"), this.leftPos + 75, this.topPos + 0, 0, 0, 100, 24, 100, 24);

		RenderSystem.disableBlend();
	}

	@Override
	public boolean keyPressed(int key, int b, int c) {
		if (key == 256) {
			this.minecraft.player.closeContainer();
			return true;
		}
		if (number_buy.isFocused())
			return number_buy.keyPressed(key, b, c);
		return super.keyPressed(key, b, c);
	}

	@Override
	public void resize(Minecraft minecraft, int width, int height) {
		String number_buyValue = number_buy.getValue();
		super.resize(minecraft, width, height);
		number_buy.setValue(number_buyValue);
	}

	@Override
	protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {
		guiGraphics.drawString(this.font, Component.translatable("gui.palamod.adminshoporeiron.label_iron"), 69, 5, -1, false);
		guiGraphics.drawString(this.font,

				AdshoppreviewamountironProcedure.execute(world, entity, guistate), 29, 74, -4671036, false);
		guiGraphics.drawString(this.font, Component.translatable("gui.palamod.adminshoporeiron.label_empty"), 34, 22, -12829636, false);
		guiGraphics.drawString(this.font, Component.translatable("gui.palamod.adminshoporeiron.label_sell_price_15"), 33, 26, -4671036, false);
		guiGraphics.drawString(this.font, Component.translatable("gui.palamod.adminshoporeiron.label_buy_price_20"), 39, 37, -4671036, false);
	}

	@Override
	public void init() {
		super.init();
		number_buy = new EditBox(this.font, this.leftPos + 27, this.topPos + 88, 118, 18, Component.translatable("gui.palamod.adminshoporeiron.number_buy")) {
			@Override
			public void insertText(String text) {
				super.insertText(text);
				if (getValue().isEmpty())
					setSuggestion(Component.translatable("gui.palamod.adminshoporeiron.number_buy").getString());
				else
					setSuggestion(null);
			}

			@Override
			public void moveCursorTo(int pos, boolean flag) {
				super.moveCursorTo(pos, flag);
				if (getValue().isEmpty())
					setSuggestion(Component.translatable("gui.palamod.adminshoporeiron.number_buy").getString());
				else
					setSuggestion(null);
			}
		};
		number_buy.setMaxLength(32767);
		number_buy.setSuggestion(Component.translatable("gui.palamod.adminshoporeiron.number_buy").getString());
		guistate.put("text:number_buy", number_buy);
		this.addWidget(this.number_buy);
		button_buy = Button.builder(Component.translatable("gui.palamod.adminshoporeiron.button_buy"), e -> {
			if (true) {
				PacketDistributor.sendToServer(new AdminshoporeironButtonMessage(0, x, y, z));
				AdminshoporeironButtonMessage.handleButtonAction(entity, 0, x, y, z);
			}
		}).bounds(this.leftPos + 26, this.topPos + 108, 40, 20).build();
		guistate.put("button:button_buy", button_buy);
		this.addRenderableWidget(button_buy);
		button_sell = Button.builder(Component.translatable("gui.palamod.adminshoporeiron.button_sell"), e -> {
			if (true) {
				PacketDistributor.sendToServer(new AdminshoporeironButtonMessage(1, x, y, z));
				AdminshoporeironButtonMessage.handleButtonAction(entity, 1, x, y, z);
			}
		}).bounds(this.leftPos + 100, this.topPos + 108, 46, 20).build();
		guistate.put("button:button_sell", button_sell);
		this.addRenderableWidget(button_sell);
		imagebutton_cross_no_button = new ImageButton(this.leftPos + 155, this.topPos + 3, 16, 16,
				new WidgetSprites(ResourceLocation.parse("palamod:textures/screens/cross_no_button.png"), ResourceLocation.parse("palamod:textures/screens/pointed_cross_no_button.png")), e -> {
					if (true) {
						PacketDistributor.sendToServer(new AdminshoporeironButtonMessage(2, x, y, z));
						AdminshoporeironButtonMessage.handleButtonAction(entity, 2, x, y, z);
					}
				}) {
			@Override
			public void renderWidget(GuiGraphics guiGraphics, int x, int y, float partialTicks) {
				guiGraphics.blit(sprites.get(isActive(), isHoveredOrFocused()), getX(), getY(), 0, 0, width, height, width, height);
			}
		};
		guistate.put("button:imagebutton_cross_no_button", imagebutton_cross_no_button);
		this.addRenderableWidget(imagebutton_cross_no_button);
		imagebutton_arrow_adminshop = new ImageButton(this.leftPos + 137, this.topPos + 3, 16, 16,
				new WidgetSprites(ResourceLocation.parse("palamod:textures/screens/arrow_adminshop.png"), ResourceLocation.parse("palamod:textures/screens/arrow_adminshop_poi.png")), e -> {
					if (true) {
						PacketDistributor.sendToServer(new AdminshoporeironButtonMessage(3, x, y, z));
						AdminshoporeironButtonMessage.handleButtonAction(entity, 3, x, y, z);
					}
				}) {
			@Override
			public void renderWidget(GuiGraphics guiGraphics, int x, int y, float partialTicks) {
				guiGraphics.blit(sprites.get(isActive(), isHoveredOrFocused()), getX(), getY(), 0, 0, width, height, width, height);
			}
		};
		guistate.put("button:imagebutton_arrow_adminshop", imagebutton_arrow_adminshop);
		this.addRenderableWidget(imagebutton_arrow_adminshop);
		imagebutton_home_pixel_adminshop = new ImageButton(this.leftPos + 121, this.topPos + 3, 16, 16,
				new WidgetSprites(ResourceLocation.parse("palamod:textures/screens/home_pixel_adminshop.png"), ResourceLocation.parse("palamod:textures/screens/pointec_home_pixel_adminshop.png")), e -> {
					if (true) {
						PacketDistributor.sendToServer(new AdminshoporeironButtonMessage(4, x, y, z));
						AdminshoporeironButtonMessage.handleButtonAction(entity, 4, x, y, z);
					}
				}) {
			@Override
			public void renderWidget(GuiGraphics guiGraphics, int x, int y, float partialTicks) {
				guiGraphics.blit(sprites.get(isActive(), isHoveredOrFocused()), getX(), getY(), 0, 0, width, height, width, height);
			}
		};
		guistate.put("button:imagebutton_home_pixel_adminshop", imagebutton_home_pixel_adminshop);
		this.addRenderableWidget(imagebutton_home_pixel_adminshop);
	}
}
