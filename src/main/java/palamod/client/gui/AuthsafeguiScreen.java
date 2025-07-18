package palamod.client.gui;

import palamod.world.inventory.AuthsafeguiMenu;

import palamod.network.AuthsafeguiButtonMessage;

import net.neoforged.neoforge.network.PacketDistributor;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.Minecraft;

import java.util.HashMap;

import com.mojang.blaze3d.systems.RenderSystem;

public class AuthsafeguiScreen extends AbstractContainerScreen<AuthsafeguiMenu> {
	private final static HashMap<String, Object> guistate = AuthsafeguiMenu.guistate;
	private final Level world;
	private final int x, y, z;
	private final Player entity;
	EditBox code_check;
	Button button_open;

	public AuthsafeguiScreen(AuthsafeguiMenu container, Inventory inventory, Component text) {
		super(container, inventory, text);
		this.world = container.world;
		this.x = container.x;
		this.y = container.y;
		this.z = container.z;
		this.entity = container.entity;
		this.imageWidth = 150;
		this.imageHeight = 60;
	}

	@Override
	public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
		this.renderBackground(guiGraphics, mouseX, mouseY, partialTicks);
		super.render(guiGraphics, mouseX, mouseY, partialTicks);
		code_check.render(guiGraphics, mouseX, mouseY, partialTicks);
		this.renderTooltip(guiGraphics, mouseX, mouseY);
	}

	@Override
	protected void renderBg(GuiGraphics guiGraphics, float partialTicks, int gx, int gy) {
		RenderSystem.setShaderColor(1, 1, 1, 1);
		RenderSystem.enableBlend();
		RenderSystem.defaultBlendFunc();

		guiGraphics.blit(ResourceLocation.parse("palamod:textures/screens/authsafegui.png"), this.leftPos + -1, this.topPos + 0, 0, 0, 150, 60, 150, 60);

		RenderSystem.disableBlend();
	}

	@Override
	public boolean keyPressed(int key, int b, int c) {
		if (key == 256) {
			this.minecraft.player.closeContainer();
			return true;
		}
		if (code_check.isFocused())
			return code_check.keyPressed(key, b, c);
		return super.keyPressed(key, b, c);
	}

	@Override
	public void resize(Minecraft minecraft, int width, int height) {
		String code_checkValue = code_check.getValue();
		super.resize(minecraft, width, height);
		code_check.setValue(code_checkValue);
	}

	@Override
	protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {
		guiGraphics.drawString(this.font, Component.translatable("gui.palamod.authsafegui.label_enter_code"), 3, 6, -12829636, false);
	}

	@Override
	public void init() {
		super.init();
		code_check = new EditBox(this.font, this.leftPos + 4, this.topPos + 16, 118, 18, Component.translatable("gui.palamod.authsafegui.code_check"));
		code_check.setMaxLength(32767);
		guistate.put("text:code_check", code_check);
		this.addWidget(this.code_check);
		button_open = Button.builder(Component.translatable("gui.palamod.authsafegui.button_open"), e -> {
			if (true) {
				PacketDistributor.sendToServer(new AuthsafeguiButtonMessage(0, x, y, z));
				AuthsafeguiButtonMessage.handleButtonAction(entity, 0, x, y, z);
			}
		}).bounds(this.leftPos + 3, this.topPos + 36, 46, 20).build();
		guistate.put("button:button_open", button_open);
		this.addRenderableWidget(button_open);
	}
}
