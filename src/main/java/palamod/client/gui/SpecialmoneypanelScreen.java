package palamod.client.gui;

import palamod.world.inventory.SpecialmoneypanelMenu;

import palamod.network.SpecialmoneypanelButtonMessage;

import net.neoforged.neoforge.network.PacketDistributor;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.components.Checkbox;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.Minecraft;

import java.util.HashMap;

import com.mojang.blaze3d.systems.RenderSystem;

public class SpecialmoneypanelScreen extends AbstractContainerScreen<SpecialmoneypanelMenu> {
	private final static HashMap<String, Object> guistate = SpecialmoneypanelMenu.guistate;
	private final Level world;
	private final int x, y, z;
	private final Player entity;
	EditBox player_name;
	EditBox money;
	Checkbox custom_destructible;
	Button button_give;

	public SpecialmoneypanelScreen(SpecialmoneypanelMenu container, Inventory inventory, Component text) {
		super(container, inventory, text);
		this.world = container.world;
		this.x = container.x;
		this.y = container.y;
		this.z = container.z;
		this.entity = container.entity;
		this.imageWidth = 176;
		this.imageHeight = 224;
	}

	@Override
	public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
		this.renderBackground(guiGraphics, mouseX, mouseY, partialTicks);
		super.render(guiGraphics, mouseX, mouseY, partialTicks);
		player_name.render(guiGraphics, mouseX, mouseY, partialTicks);
		money.render(guiGraphics, mouseX, mouseY, partialTicks);
		this.renderTooltip(guiGraphics, mouseX, mouseY);
	}

	@Override
	protected void renderBg(GuiGraphics guiGraphics, float partialTicks, int gx, int gy) {
		RenderSystem.setShaderColor(1, 1, 1, 1);
		RenderSystem.enableBlend();
		RenderSystem.defaultBlendFunc();

		guiGraphics.blit(ResourceLocation.parse("palamod:textures/screens/specialmoneypanel.png"), this.leftPos + -1, this.topPos + 0, 0, 0, 176, 224, 176, 224);

		RenderSystem.disableBlend();
	}

	@Override
	public boolean keyPressed(int key, int b, int c) {
		if (key == 256) {
			this.minecraft.player.closeContainer();
			return true;
		}
		if (player_name.isFocused())
			return player_name.keyPressed(key, b, c);
		if (money.isFocused())
			return money.keyPressed(key, b, c);
		return super.keyPressed(key, b, c);
	}

	@Override
	public void resize(Minecraft minecraft, int width, int height) {
		String player_nameValue = player_name.getValue();
		String moneyValue = money.getValue();
		super.resize(minecraft, width, height);
		player_name.setValue(player_nameValue);
		money.setValue(moneyValue);
	}

	@Override
	protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {
		guiGraphics.drawString(this.font, Component.translatable("gui.palamod.specialmoneypanel.label_specific_player"), 26, 8, -12829636, false);
		guiGraphics.drawString(this.font, Component.translatable("gui.palamod.specialmoneypanel.label_amount_of_money"), 26, 40, -12829636, false);
		guiGraphics.drawString(this.font, Component.translatable("gui.palamod.specialmoneypanel.label_item_default_money_item"), 7, 74, -12829636, false);
	}

	@Override
	public void init() {
		super.init();
		player_name = new EditBox(this.font, this.leftPos + 8, this.topPos + 19, 118, 18, Component.translatable("gui.palamod.specialmoneypanel.player_name")) {
			@Override
			public void insertText(String text) {
				super.insertText(text);
				if (getValue().isEmpty())
					setSuggestion(Component.translatable("gui.palamod.specialmoneypanel.player_name").getString());
				else
					setSuggestion(null);
			}

			@Override
			public void moveCursorTo(int pos, boolean flag) {
				super.moveCursorTo(pos, flag);
				if (getValue().isEmpty())
					setSuggestion(Component.translatable("gui.palamod.specialmoneypanel.player_name").getString());
				else
					setSuggestion(null);
			}
		};
		player_name.setMaxLength(32767);
		player_name.setSuggestion(Component.translatable("gui.palamod.specialmoneypanel.player_name").getString());
		guistate.put("text:player_name", player_name);
		this.addWidget(this.player_name);
		money = new EditBox(this.font, this.leftPos + 8, this.topPos + 51, 118, 18, Component.translatable("gui.palamod.specialmoneypanel.money")) {
			@Override
			public void insertText(String text) {
				super.insertText(text);
				if (getValue().isEmpty())
					setSuggestion(Component.translatable("gui.palamod.specialmoneypanel.money").getString());
				else
					setSuggestion(null);
			}

			@Override
			public void moveCursorTo(int pos, boolean flag) {
				super.moveCursorTo(pos, flag);
				if (getValue().isEmpty())
					setSuggestion(Component.translatable("gui.palamod.specialmoneypanel.money").getString());
				else
					setSuggestion(null);
			}
		};
		money.setMaxLength(32767);
		money.setSuggestion(Component.translatable("gui.palamod.specialmoneypanel.money").getString());
		guistate.put("text:money", money);
		this.addWidget(this.money);
		button_give = Button.builder(Component.translatable("gui.palamod.specialmoneypanel.button_give"), e -> {
			if (true) {
				PacketDistributor.sendToServer(new SpecialmoneypanelButtonMessage(0, x, y, z));
				SpecialmoneypanelButtonMessage.handleButtonAction(entity, 0, x, y, z);
			}
		}).bounds(this.leftPos + 119, this.topPos + 90, 46, 20).build();
		guistate.put("button:button_give", button_give);
		this.addRenderableWidget(button_give);
		custom_destructible = Checkbox.builder(Component.translatable("gui.palamod.specialmoneypanel.custom_destructible"), this.font).pos(this.leftPos + 6, this.topPos + 114)

				.build();
		guistate.put("checkbox:custom_destructible", custom_destructible);
		this.addRenderableWidget(custom_destructible);
	}
}
