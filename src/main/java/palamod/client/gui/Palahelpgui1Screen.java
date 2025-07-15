package palamod.client.gui;

import palamod.world.inventory.Palahelpgui1Menu;

import palamod.network.Palahelpgui1ButtonMessage;

import net.neoforged.neoforge.network.PacketDistributor;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.GuiGraphics;

import java.util.HashMap;

import com.mojang.blaze3d.systems.RenderSystem;

public class Palahelpgui1Screen extends AbstractContainerScreen<Palahelpgui1Menu> {
	private final static HashMap<String, Object> guistate = Palahelpgui1Menu.guistate;
	private final Level world;
	private final int x, y, z;
	private final Player entity;
	Button button_blocks;
	Button button_items;
	Button button_gui;
	Button button_lucky_blocks;
	Button button_metiers_soon;

	public Palahelpgui1Screen(Palahelpgui1Menu container, Inventory inventory, Component text) {
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
		this.renderTooltip(guiGraphics, mouseX, mouseY);
	}

	@Override
	protected void renderBg(GuiGraphics guiGraphics, float partialTicks, int gx, int gy) {
		RenderSystem.setShaderColor(1, 1, 1, 1);
		RenderSystem.enableBlend();
		RenderSystem.defaultBlendFunc();

		guiGraphics.blit(ResourceLocation.parse("palamod:textures/screens/gui176_166.png"), this.leftPos + 0, this.topPos + 1, 0, 0, 176, 166, 176, 166);

		guiGraphics.blit(ResourceLocation.parse("palamod:textures/screens/paladium_block_photo.png"), this.leftPos + 3, this.topPos + 20, 0, 0, 85, 84, 85, 84);

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
		guiGraphics.drawString(this.font, Component.translatable("gui.palamod.palahelpgui_1.label_palahelp"), 6, 5, -52429, false);
		guiGraphics.drawString(this.font, Component.translatable("gui.palamod.palahelpgui_1.label_12"), 6, 147, -16750951, false);
		guiGraphics.drawString(this.font, Component.translatable("gui.palamod.palahelpgui_1.label_soon"), 87, 82, -1, false);
	}

	@Override
	public void init() {
		super.init();
		button_blocks = Button.builder(Component.translatable("gui.palamod.palahelpgui_1.button_blocks"), e -> {
			if (true) {
				PacketDistributor.sendToServer(new Palahelpgui1ButtonMessage(0, x, y, z));
				Palahelpgui1ButtonMessage.handleButtonAction(entity, 0, x, y, z);
			}
		}).bounds(this.leftPos + 88, this.topPos + 6, 81, 20).build();
		guistate.put("button:button_blocks", button_blocks);
		this.addRenderableWidget(button_blocks);
		button_items = Button.builder(Component.translatable("gui.palamod.palahelpgui_1.button_items"), e -> {
			if (true) {
				PacketDistributor.sendToServer(new Palahelpgui1ButtonMessage(1, x, y, z));
				Palahelpgui1ButtonMessage.handleButtonAction(entity, 1, x, y, z);
			}
		}).bounds(this.leftPos + 88, this.topPos + 31, 81, 20).build();
		guistate.put("button:button_items", button_items);
		this.addRenderableWidget(button_items);
		button_gui = Button.builder(Component.translatable("gui.palamod.palahelpgui_1.button_gui"), e -> {
			if (true) {
				PacketDistributor.sendToServer(new Palahelpgui1ButtonMessage(2, x, y, z));
				Palahelpgui1ButtonMessage.handleButtonAction(entity, 2, x, y, z);
			}
		}).bounds(this.leftPos + 88, this.topPos + 57, 81, 20).build();
		guistate.put("button:button_gui", button_gui);
		this.addRenderableWidget(button_gui);
		button_lucky_blocks = Button.builder(Component.translatable("gui.palamod.palahelpgui_1.button_lucky_blocks"), e -> {
			if (true) {
				PacketDistributor.sendToServer(new Palahelpgui1ButtonMessage(3, x, y, z));
				Palahelpgui1ButtonMessage.handleButtonAction(entity, 3, x, y, z);
			}
		}).bounds(this.leftPos + 88, this.topPos + 122, 81, 20).build();
		guistate.put("button:button_lucky_blocks", button_lucky_blocks);
		this.addRenderableWidget(button_lucky_blocks);
		button_metiers_soon = Button.builder(Component.translatable("gui.palamod.palahelpgui_1.button_metiers_soon"), e -> {
			if (true) {
				PacketDistributor.sendToServer(new Palahelpgui1ButtonMessage(4, x, y, z));
				Palahelpgui1ButtonMessage.handleButtonAction(entity, 4, x, y, z);
			}
		}).bounds(this.leftPos + 88, this.topPos + 97, 81, 20).build();
		guistate.put("button:button_metiers_soon", button_metiers_soon);
		this.addRenderableWidget(button_metiers_soon);
	}
}
