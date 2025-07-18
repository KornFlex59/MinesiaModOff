package palamod.client.gui;

import palamod.world.inventory.UploaderguipalahelpMenu;

import palamod.procedures.Palahelpuploader2Procedure;
import palamod.procedures.Palahelpuploader1Procedure;
import palamod.procedures.Palahelpuploader0Procedure;

import palamod.network.UploaderguipalahelpButtonMessage;

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

public class UploaderguipalahelpScreen extends AbstractContainerScreen<UploaderguipalahelpMenu> {
	private final static HashMap<String, Object> guistate = UploaderguipalahelpMenu.guistate;
	private final Level world;
	private final int x, y, z;
	private final Player entity;
	Button button_gui_example_wip;

	public UploaderguipalahelpScreen(UploaderguipalahelpMenu container, Inventory inventory, Component text) {
		super(container, inventory, text);
		this.world = container.world;
		this.x = container.x;
		this.y = container.y;
		this.z = container.z;
		this.entity = container.entity;
		this.imageWidth = 319;
		this.imageHeight = 200;
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

		guiGraphics.blit(ResourceLocation.parse("palamod:textures/screens/uploaderguipalahelp.png"), this.leftPos + 0, this.topPos + 0, 0, 0, 319, 200, 319, 200);

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
		guiGraphics.drawString(this.font, Component.translatable("gui.palamod.uploaderguipalahelp.label_page_en_cours_de_redaction"), 21, 24, -12829636, false);
		guiGraphics.drawString(this.font, Component.translatable("gui.palamod.uploaderguipalahelp.label_this_page_is_working_in_progress"), 20, 11, -12829636, false);
		guiGraphics.drawString(this.font, Component.translatable("gui.palamod.uploaderguipalahelp.label_uploader"), 125, 46, -65536, false);
		guiGraphics.drawString(this.font,

				Palahelpuploader0Procedure.execute(entity), 4, 60, -12829636, false);
		guiGraphics.drawString(this.font,

				Palahelpuploader1Procedure.execute(entity), 4, 71, -12829636, false);
		guiGraphics.drawString(this.font,

				Palahelpuploader2Procedure.execute(entity), 4, 82, -12829636, false);
	}

	@Override
	public void init() {
		super.init();
		button_gui_example_wip = Button.builder(Component.translatable("gui.palamod.uploaderguipalahelp.button_gui_example_wip"), e -> {
			if (true) {
				PacketDistributor.sendToServer(new UploaderguipalahelpButtonMessage(0, x, y, z));
				UploaderguipalahelpButtonMessage.handleButtonAction(entity, 0, x, y, z);
			}
		}).bounds(this.leftPos + 6, this.topPos + 170, 123, 20).build();
		guistate.put("button:button_gui_example_wip", button_gui_example_wip);
		this.addRenderableWidget(button_gui_example_wip);
	}
}
