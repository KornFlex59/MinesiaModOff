
package palamod.world.inventory;

import palamod.init.PalamodModMenus;

import net.neoforged.neoforge.items.wrapper.InvWrapper;
import net.neoforged.neoforge.items.SlotItemHandler;
import net.neoforged.neoforge.items.ItemStackHandler;
import net.neoforged.neoforge.items.IItemHandlerModifiable;
import net.neoforged.neoforge.items.IItemHandler;
import net.neoforged.neoforge.capabilities.Capabilities;

import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BaseContainerBlockEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.core.BlockPos;

import java.util.function.Supplier;
import java.util.Map;
import java.util.HashMap;

public class StockhdvMenu extends AbstractContainerMenu implements Supplier<Map<Integer, Slot>> {
	public final static HashMap<String, Object> guistate = new HashMap<>();
	public final Level world;
	public final Player entity;
	public int x, y, z;
	private ContainerLevelAccess access = ContainerLevelAccess.NULL;
	private IItemHandler internal;
	private final Map<Integer, Slot> customSlots = new HashMap<>();
	private boolean bound = false;
	private Supplier<Boolean> boundItemMatcher = null;
	private Entity boundEntity = null;
	private BlockEntity boundBlockEntity = null;

	public StockhdvMenu(int id, Inventory inv, FriendlyByteBuf extraData) {
		super(PalamodModMenus.STOCKHDV.get(), id);
		this.entity = inv.player;
		this.world = inv.player.level();
		this.internal = new ItemStackHandler(92);
		BlockPos pos = null;
		if (extraData != null) {
			pos = extraData.readBlockPos();
			this.x = pos.getX();
			this.y = pos.getY();
			this.z = pos.getZ();
			access = ContainerLevelAccess.create(world, pos);
		}
		if (pos != null) {
			if (extraData.readableBytes() == 1) { // bound to item
				byte hand = extraData.readByte();
				ItemStack itemstack = hand == 0 ? this.entity.getMainHandItem() : this.entity.getOffhandItem();
				this.boundItemMatcher = () -> itemstack == (hand == 0 ? this.entity.getMainHandItem() : this.entity.getOffhandItem());
				IItemHandler cap = itemstack.getCapability(Capabilities.ItemHandler.ITEM);
				if (cap != null) {
					this.internal = cap;
					this.bound = true;
				}
			} else if (extraData.readableBytes() > 1) { // bound to entity
				extraData.readByte(); // drop padding
				boundEntity = world.getEntity(extraData.readVarInt());
				if (boundEntity != null) {
					IItemHandler cap = boundEntity.getCapability(Capabilities.ItemHandler.ENTITY);
					if (cap != null) {
						this.internal = cap;
						this.bound = true;
					}
				}
			} else { // might be bound to block
				boundBlockEntity = this.world.getBlockEntity(pos);
				if (boundBlockEntity instanceof BaseContainerBlockEntity baseContainerBlockEntity) {
					this.internal = new InvWrapper(baseContainerBlockEntity);
					this.bound = true;
				}
			}
		}
		this.customSlots.put(0, this.addSlot(new SlotItemHandler(internal, 0, 3, 14) {
			private final int slot = 0;
			private int x = StockhdvMenu.this.x;
			private int y = StockhdvMenu.this.y;
		}));
		this.customSlots.put(1, this.addSlot(new SlotItemHandler(internal, 1, 21, 14) {
			private final int slot = 1;
			private int x = StockhdvMenu.this.x;
			private int y = StockhdvMenu.this.y;
		}));
		this.customSlots.put(2, this.addSlot(new SlotItemHandler(internal, 2, 39, 14) {
			private final int slot = 2;
			private int x = StockhdvMenu.this.x;
			private int y = StockhdvMenu.this.y;
		}));
		this.customSlots.put(3, this.addSlot(new SlotItemHandler(internal, 3, 57, 14) {
			private final int slot = 3;
			private int x = StockhdvMenu.this.x;
			private int y = StockhdvMenu.this.y;
		}));
		this.customSlots.put(4, this.addSlot(new SlotItemHandler(internal, 4, 75, 14) {
			private final int slot = 4;
			private int x = StockhdvMenu.this.x;
			private int y = StockhdvMenu.this.y;
		}));
		this.customSlots.put(5, this.addSlot(new SlotItemHandler(internal, 5, 93, 14) {
			private final int slot = 5;
			private int x = StockhdvMenu.this.x;
			private int y = StockhdvMenu.this.y;
		}));
		this.customSlots.put(6, this.addSlot(new SlotItemHandler(internal, 6, 111, 14) {
			private final int slot = 6;
			private int x = StockhdvMenu.this.x;
			private int y = StockhdvMenu.this.y;
		}));
		this.customSlots.put(7, this.addSlot(new SlotItemHandler(internal, 7, 129, 14) {
			private final int slot = 7;
			private int x = StockhdvMenu.this.x;
			private int y = StockhdvMenu.this.y;
		}));
		this.customSlots.put(8, this.addSlot(new SlotItemHandler(internal, 8, 147, 14) {
			private final int slot = 8;
			private int x = StockhdvMenu.this.x;
			private int y = StockhdvMenu.this.y;
		}));
		this.customSlots.put(9, this.addSlot(new SlotItemHandler(internal, 9, 165, 14) {
			private final int slot = 9;
			private int x = StockhdvMenu.this.x;
			private int y = StockhdvMenu.this.y;
		}));
		this.customSlots.put(10, this.addSlot(new SlotItemHandler(internal, 10, 183, 14) {
			private final int slot = 10;
			private int x = StockhdvMenu.this.x;
			private int y = StockhdvMenu.this.y;
		}));
		this.customSlots.put(11, this.addSlot(new SlotItemHandler(internal, 11, 201, 14) {
			private final int slot = 11;
			private int x = StockhdvMenu.this.x;
			private int y = StockhdvMenu.this.y;
		}));
		this.customSlots.put(12, this.addSlot(new SlotItemHandler(internal, 12, 219, 14) {
			private final int slot = 12;
			private int x = StockhdvMenu.this.x;
			private int y = StockhdvMenu.this.y;
		}));
		this.customSlots.put(13, this.addSlot(new SlotItemHandler(internal, 13, 237, 14) {
			private final int slot = 13;
			private int x = StockhdvMenu.this.x;
			private int y = StockhdvMenu.this.y;
		}));
		this.customSlots.put(14, this.addSlot(new SlotItemHandler(internal, 14, 255, 14) {
			private final int slot = 14;
			private int x = StockhdvMenu.this.x;
			private int y = StockhdvMenu.this.y;
		}));
		this.customSlots.put(15, this.addSlot(new SlotItemHandler(internal, 15, 273, 14) {
			private final int slot = 15;
			private int x = StockhdvMenu.this.x;
			private int y = StockhdvMenu.this.y;
		}));
		this.customSlots.put(16, this.addSlot(new SlotItemHandler(internal, 16, 291, 14) {
			private final int slot = 16;
			private int x = StockhdvMenu.this.x;
			private int y = StockhdvMenu.this.y;
		}));
		this.customSlots.put(17, this.addSlot(new SlotItemHandler(internal, 17, 309, 14) {
			private final int slot = 17;
			private int x = StockhdvMenu.this.x;
			private int y = StockhdvMenu.this.y;
		}));
		this.customSlots.put(18, this.addSlot(new SlotItemHandler(internal, 18, 327, 14) {
			private final int slot = 18;
			private int x = StockhdvMenu.this.x;
			private int y = StockhdvMenu.this.y;
		}));
		this.customSlots.put(19, this.addSlot(new SlotItemHandler(internal, 19, 345, 14) {
			private final int slot = 19;
			private int x = StockhdvMenu.this.x;
			private int y = StockhdvMenu.this.y;
		}));
		this.customSlots.put(20, this.addSlot(new SlotItemHandler(internal, 20, 363, 14) {
			private final int slot = 20;
			private int x = StockhdvMenu.this.x;
			private int y = StockhdvMenu.this.y;
		}));
		this.customSlots.put(21, this.addSlot(new SlotItemHandler(internal, 21, 381, 14) {
			private final int slot = 21;
			private int x = StockhdvMenu.this.x;
			private int y = StockhdvMenu.this.y;
		}));
		this.customSlots.put(22, this.addSlot(new SlotItemHandler(internal, 22, 399, 14) {
			private final int slot = 22;
			private int x = StockhdvMenu.this.x;
			private int y = StockhdvMenu.this.y;
		}));
		this.customSlots.put(23, this.addSlot(new SlotItemHandler(internal, 23, 3, 43) {
			private final int slot = 23;
			private int x = StockhdvMenu.this.x;
			private int y = StockhdvMenu.this.y;
		}));
		this.customSlots.put(24, this.addSlot(new SlotItemHandler(internal, 24, 21, 43) {
			private final int slot = 24;
			private int x = StockhdvMenu.this.x;
			private int y = StockhdvMenu.this.y;
		}));
		this.customSlots.put(25, this.addSlot(new SlotItemHandler(internal, 25, 39, 43) {
			private final int slot = 25;
			private int x = StockhdvMenu.this.x;
			private int y = StockhdvMenu.this.y;
		}));
		this.customSlots.put(26, this.addSlot(new SlotItemHandler(internal, 26, 57, 43) {
			private final int slot = 26;
			private int x = StockhdvMenu.this.x;
			private int y = StockhdvMenu.this.y;
		}));
		this.customSlots.put(27, this.addSlot(new SlotItemHandler(internal, 27, 75, 43) {
			private final int slot = 27;
			private int x = StockhdvMenu.this.x;
			private int y = StockhdvMenu.this.y;
		}));
		this.customSlots.put(28, this.addSlot(new SlotItemHandler(internal, 28, 93, 43) {
			private final int slot = 28;
			private int x = StockhdvMenu.this.x;
			private int y = StockhdvMenu.this.y;
		}));
		this.customSlots.put(29, this.addSlot(new SlotItemHandler(internal, 29, 111, 43) {
			private final int slot = 29;
			private int x = StockhdvMenu.this.x;
			private int y = StockhdvMenu.this.y;
		}));
		this.customSlots.put(30, this.addSlot(new SlotItemHandler(internal, 30, 129, 43) {
			private final int slot = 30;
			private int x = StockhdvMenu.this.x;
			private int y = StockhdvMenu.this.y;
		}));
		this.customSlots.put(31, this.addSlot(new SlotItemHandler(internal, 31, 147, 43) {
			private final int slot = 31;
			private int x = StockhdvMenu.this.x;
			private int y = StockhdvMenu.this.y;
		}));
		this.customSlots.put(32, this.addSlot(new SlotItemHandler(internal, 32, 165, 43) {
			private final int slot = 32;
			private int x = StockhdvMenu.this.x;
			private int y = StockhdvMenu.this.y;
		}));
		this.customSlots.put(33, this.addSlot(new SlotItemHandler(internal, 33, 183, 43) {
			private final int slot = 33;
			private int x = StockhdvMenu.this.x;
			private int y = StockhdvMenu.this.y;
		}));
		this.customSlots.put(34, this.addSlot(new SlotItemHandler(internal, 34, 201, 43) {
			private final int slot = 34;
			private int x = StockhdvMenu.this.x;
			private int y = StockhdvMenu.this.y;
		}));
		this.customSlots.put(35, this.addSlot(new SlotItemHandler(internal, 35, 219, 43) {
			private final int slot = 35;
			private int x = StockhdvMenu.this.x;
			private int y = StockhdvMenu.this.y;
		}));
		this.customSlots.put(36, this.addSlot(new SlotItemHandler(internal, 36, 237, 43) {
			private final int slot = 36;
			private int x = StockhdvMenu.this.x;
			private int y = StockhdvMenu.this.y;
		}));
		this.customSlots.put(37, this.addSlot(new SlotItemHandler(internal, 37, 255, 43) {
			private final int slot = 37;
			private int x = StockhdvMenu.this.x;
			private int y = StockhdvMenu.this.y;
		}));
		this.customSlots.put(38, this.addSlot(new SlotItemHandler(internal, 38, 273, 43) {
			private final int slot = 38;
			private int x = StockhdvMenu.this.x;
			private int y = StockhdvMenu.this.y;
		}));
		this.customSlots.put(39, this.addSlot(new SlotItemHandler(internal, 39, 291, 43) {
			private final int slot = 39;
			private int x = StockhdvMenu.this.x;
			private int y = StockhdvMenu.this.y;
		}));
		this.customSlots.put(40, this.addSlot(new SlotItemHandler(internal, 40, 309, 43) {
			private final int slot = 40;
			private int x = StockhdvMenu.this.x;
			private int y = StockhdvMenu.this.y;
		}));
		this.customSlots.put(41, this.addSlot(new SlotItemHandler(internal, 41, 327, 43) {
			private final int slot = 41;
			private int x = StockhdvMenu.this.x;
			private int y = StockhdvMenu.this.y;
		}));
		this.customSlots.put(42, this.addSlot(new SlotItemHandler(internal, 42, 345, 43) {
			private final int slot = 42;
			private int x = StockhdvMenu.this.x;
			private int y = StockhdvMenu.this.y;
		}));
		this.customSlots.put(43, this.addSlot(new SlotItemHandler(internal, 43, 363, 43) {
			private final int slot = 43;
			private int x = StockhdvMenu.this.x;
			private int y = StockhdvMenu.this.y;
		}));
		this.customSlots.put(44, this.addSlot(new SlotItemHandler(internal, 44, 381, 43) {
			private final int slot = 44;
			private int x = StockhdvMenu.this.x;
			private int y = StockhdvMenu.this.y;
		}));
		this.customSlots.put(45, this.addSlot(new SlotItemHandler(internal, 45, 399, 43) {
			private final int slot = 45;
			private int x = StockhdvMenu.this.x;
			private int y = StockhdvMenu.this.y;
		}));
		this.customSlots.put(46, this.addSlot(new SlotItemHandler(internal, 46, 3, 71) {
			private final int slot = 46;
			private int x = StockhdvMenu.this.x;
			private int y = StockhdvMenu.this.y;
		}));
		this.customSlots.put(47, this.addSlot(new SlotItemHandler(internal, 47, 21, 71) {
			private final int slot = 47;
			private int x = StockhdvMenu.this.x;
			private int y = StockhdvMenu.this.y;
		}));
		this.customSlots.put(48, this.addSlot(new SlotItemHandler(internal, 48, 39, 71) {
			private final int slot = 48;
			private int x = StockhdvMenu.this.x;
			private int y = StockhdvMenu.this.y;
		}));
		this.customSlots.put(49, this.addSlot(new SlotItemHandler(internal, 49, 57, 71) {
			private final int slot = 49;
			private int x = StockhdvMenu.this.x;
			private int y = StockhdvMenu.this.y;
		}));
		this.customSlots.put(50, this.addSlot(new SlotItemHandler(internal, 50, 75, 71) {
			private final int slot = 50;
			private int x = StockhdvMenu.this.x;
			private int y = StockhdvMenu.this.y;
		}));
		this.customSlots.put(51, this.addSlot(new SlotItemHandler(internal, 51, 93, 71) {
			private final int slot = 51;
			private int x = StockhdvMenu.this.x;
			private int y = StockhdvMenu.this.y;
		}));
		this.customSlots.put(52, this.addSlot(new SlotItemHandler(internal, 52, 111, 71) {
			private final int slot = 52;
			private int x = StockhdvMenu.this.x;
			private int y = StockhdvMenu.this.y;
		}));
		this.customSlots.put(53, this.addSlot(new SlotItemHandler(internal, 53, 129, 71) {
			private final int slot = 53;
			private int x = StockhdvMenu.this.x;
			private int y = StockhdvMenu.this.y;
		}));
		this.customSlots.put(54, this.addSlot(new SlotItemHandler(internal, 54, 147, 71) {
			private final int slot = 54;
			private int x = StockhdvMenu.this.x;
			private int y = StockhdvMenu.this.y;
		}));
		this.customSlots.put(55, this.addSlot(new SlotItemHandler(internal, 55, 165, 71) {
			private final int slot = 55;
			private int x = StockhdvMenu.this.x;
			private int y = StockhdvMenu.this.y;
		}));
		this.customSlots.put(56, this.addSlot(new SlotItemHandler(internal, 56, 183, 71) {
			private final int slot = 56;
			private int x = StockhdvMenu.this.x;
			private int y = StockhdvMenu.this.y;
		}));
		this.customSlots.put(57, this.addSlot(new SlotItemHandler(internal, 57, 201, 71) {
			private final int slot = 57;
			private int x = StockhdvMenu.this.x;
			private int y = StockhdvMenu.this.y;
		}));
		this.customSlots.put(58, this.addSlot(new SlotItemHandler(internal, 58, 219, 71) {
			private final int slot = 58;
			private int x = StockhdvMenu.this.x;
			private int y = StockhdvMenu.this.y;
		}));
		this.customSlots.put(59, this.addSlot(new SlotItemHandler(internal, 59, 237, 71) {
			private final int slot = 59;
			private int x = StockhdvMenu.this.x;
			private int y = StockhdvMenu.this.y;
		}));
		this.customSlots.put(60, this.addSlot(new SlotItemHandler(internal, 60, 255, 71) {
			private final int slot = 60;
			private int x = StockhdvMenu.this.x;
			private int y = StockhdvMenu.this.y;
		}));
		this.customSlots.put(61, this.addSlot(new SlotItemHandler(internal, 61, 273, 71) {
			private final int slot = 61;
			private int x = StockhdvMenu.this.x;
			private int y = StockhdvMenu.this.y;
		}));
		this.customSlots.put(62, this.addSlot(new SlotItemHandler(internal, 62, 291, 71) {
			private final int slot = 62;
			private int x = StockhdvMenu.this.x;
			private int y = StockhdvMenu.this.y;
		}));
		this.customSlots.put(63, this.addSlot(new SlotItemHandler(internal, 63, 309, 71) {
			private final int slot = 63;
			private int x = StockhdvMenu.this.x;
			private int y = StockhdvMenu.this.y;
		}));
		this.customSlots.put(64, this.addSlot(new SlotItemHandler(internal, 64, 327, 71) {
			private final int slot = 64;
			private int x = StockhdvMenu.this.x;
			private int y = StockhdvMenu.this.y;
		}));
		this.customSlots.put(65, this.addSlot(new SlotItemHandler(internal, 65, 345, 71) {
			private final int slot = 65;
			private int x = StockhdvMenu.this.x;
			private int y = StockhdvMenu.this.y;
		}));
		this.customSlots.put(66, this.addSlot(new SlotItemHandler(internal, 66, 363, 71) {
			private final int slot = 66;
			private int x = StockhdvMenu.this.x;
			private int y = StockhdvMenu.this.y;
		}));
		this.customSlots.put(67, this.addSlot(new SlotItemHandler(internal, 67, 381, 71) {
			private final int slot = 67;
			private int x = StockhdvMenu.this.x;
			private int y = StockhdvMenu.this.y;
		}));
		this.customSlots.put(68, this.addSlot(new SlotItemHandler(internal, 68, 399, 71) {
			private final int slot = 68;
			private int x = StockhdvMenu.this.x;
			private int y = StockhdvMenu.this.y;
		}));
		this.customSlots.put(69, this.addSlot(new SlotItemHandler(internal, 69, 3, 98) {
			private final int slot = 69;
			private int x = StockhdvMenu.this.x;
			private int y = StockhdvMenu.this.y;
		}));
		this.customSlots.put(70, this.addSlot(new SlotItemHandler(internal, 70, 21, 98) {
			private final int slot = 70;
			private int x = StockhdvMenu.this.x;
			private int y = StockhdvMenu.this.y;
		}));
		this.customSlots.put(71, this.addSlot(new SlotItemHandler(internal, 71, 39, 98) {
			private final int slot = 71;
			private int x = StockhdvMenu.this.x;
			private int y = StockhdvMenu.this.y;
		}));
		this.customSlots.put(72, this.addSlot(new SlotItemHandler(internal, 72, 57, 98) {
			private final int slot = 72;
			private int x = StockhdvMenu.this.x;
			private int y = StockhdvMenu.this.y;
		}));
		this.customSlots.put(73, this.addSlot(new SlotItemHandler(internal, 73, 75, 98) {
			private final int slot = 73;
			private int x = StockhdvMenu.this.x;
			private int y = StockhdvMenu.this.y;
		}));
		this.customSlots.put(74, this.addSlot(new SlotItemHandler(internal, 74, 93, 98) {
			private final int slot = 74;
			private int x = StockhdvMenu.this.x;
			private int y = StockhdvMenu.this.y;
		}));
		this.customSlots.put(75, this.addSlot(new SlotItemHandler(internal, 75, 111, 98) {
			private final int slot = 75;
			private int x = StockhdvMenu.this.x;
			private int y = StockhdvMenu.this.y;
		}));
		this.customSlots.put(76, this.addSlot(new SlotItemHandler(internal, 76, 129, 98) {
			private final int slot = 76;
			private int x = StockhdvMenu.this.x;
			private int y = StockhdvMenu.this.y;
		}));
		this.customSlots.put(77, this.addSlot(new SlotItemHandler(internal, 77, 147, 98) {
			private final int slot = 77;
			private int x = StockhdvMenu.this.x;
			private int y = StockhdvMenu.this.y;
		}));
		this.customSlots.put(78, this.addSlot(new SlotItemHandler(internal, 78, 165, 98) {
			private final int slot = 78;
			private int x = StockhdvMenu.this.x;
			private int y = StockhdvMenu.this.y;
		}));
		this.customSlots.put(79, this.addSlot(new SlotItemHandler(internal, 79, 183, 98) {
			private final int slot = 79;
			private int x = StockhdvMenu.this.x;
			private int y = StockhdvMenu.this.y;
		}));
		this.customSlots.put(80, this.addSlot(new SlotItemHandler(internal, 80, 201, 98) {
			private final int slot = 80;
			private int x = StockhdvMenu.this.x;
			private int y = StockhdvMenu.this.y;
		}));
		this.customSlots.put(81, this.addSlot(new SlotItemHandler(internal, 81, 219, 98) {
			private final int slot = 81;
			private int x = StockhdvMenu.this.x;
			private int y = StockhdvMenu.this.y;
		}));
		this.customSlots.put(82, this.addSlot(new SlotItemHandler(internal, 82, 237, 98) {
			private final int slot = 82;
			private int x = StockhdvMenu.this.x;
			private int y = StockhdvMenu.this.y;
		}));
		this.customSlots.put(83, this.addSlot(new SlotItemHandler(internal, 83, 255, 98) {
			private final int slot = 83;
			private int x = StockhdvMenu.this.x;
			private int y = StockhdvMenu.this.y;
		}));
		this.customSlots.put(84, this.addSlot(new SlotItemHandler(internal, 84, 273, 98) {
			private final int slot = 84;
			private int x = StockhdvMenu.this.x;
			private int y = StockhdvMenu.this.y;
		}));
		this.customSlots.put(85, this.addSlot(new SlotItemHandler(internal, 85, 291, 98) {
			private final int slot = 85;
			private int x = StockhdvMenu.this.x;
			private int y = StockhdvMenu.this.y;
		}));
		this.customSlots.put(86, this.addSlot(new SlotItemHandler(internal, 86, 309, 98) {
			private final int slot = 86;
			private int x = StockhdvMenu.this.x;
			private int y = StockhdvMenu.this.y;
		}));
		this.customSlots.put(87, this.addSlot(new SlotItemHandler(internal, 87, 327, 98) {
			private final int slot = 87;
			private int x = StockhdvMenu.this.x;
			private int y = StockhdvMenu.this.y;
		}));
		this.customSlots.put(88, this.addSlot(new SlotItemHandler(internal, 88, 345, 98) {
			private final int slot = 88;
			private int x = StockhdvMenu.this.x;
			private int y = StockhdvMenu.this.y;
		}));
		this.customSlots.put(89, this.addSlot(new SlotItemHandler(internal, 89, 363, 98) {
			private final int slot = 89;
			private int x = StockhdvMenu.this.x;
			private int y = StockhdvMenu.this.y;
		}));
		this.customSlots.put(90, this.addSlot(new SlotItemHandler(internal, 90, 381, 98) {
			private final int slot = 90;
			private int x = StockhdvMenu.this.x;
			private int y = StockhdvMenu.this.y;
		}));
		this.customSlots.put(91, this.addSlot(new SlotItemHandler(internal, 91, 399, 98) {
			private final int slot = 91;
			private int x = StockhdvMenu.this.x;
			private int y = StockhdvMenu.this.y;
		}));
		for (int si = 0; si < 3; ++si)
			for (int sj = 0; sj < 9; ++sj)
				this.addSlot(new Slot(inv, sj + (si + 1) * 9, 245 + 8 + sj * 18, 72 + 84 + si * 18));
		for (int si = 0; si < 9; ++si)
			this.addSlot(new Slot(inv, si, 245 + 8 + si * 18, 72 + 142));
	}

	@Override
	public boolean stillValid(Player player) {
		if (this.bound) {
			if (this.boundItemMatcher != null)
				return this.boundItemMatcher.get();
			else if (this.boundBlockEntity != null)
				return AbstractContainerMenu.stillValid(this.access, player, this.boundBlockEntity.getBlockState().getBlock());
			else if (this.boundEntity != null)
				return this.boundEntity.isAlive();
		}
		return true;
	}

	@Override
	public ItemStack quickMoveStack(Player playerIn, int index) {
		ItemStack itemstack = ItemStack.EMPTY;
		Slot slot = (Slot) this.slots.get(index);
		if (slot != null && slot.hasItem()) {
			ItemStack itemstack1 = slot.getItem();
			itemstack = itemstack1.copy();
			if (index < 92) {
				if (!this.moveItemStackTo(itemstack1, 92, this.slots.size(), true))
					return ItemStack.EMPTY;
				slot.onQuickCraft(itemstack1, itemstack);
			} else if (!this.moveItemStackTo(itemstack1, 0, 92, false)) {
				if (index < 92 + 27) {
					if (!this.moveItemStackTo(itemstack1, 92 + 27, this.slots.size(), true))
						return ItemStack.EMPTY;
				} else {
					if (!this.moveItemStackTo(itemstack1, 92, 92 + 27, false))
						return ItemStack.EMPTY;
				}
				return ItemStack.EMPTY;
			}
			if (itemstack1.getCount() == 0)
				slot.set(ItemStack.EMPTY);
			else
				slot.setChanged();
			if (itemstack1.getCount() == itemstack.getCount())
				return ItemStack.EMPTY;
			slot.onTake(playerIn, itemstack1);
		}
		return itemstack;
	}

	@Override
	protected boolean moveItemStackTo(ItemStack p_38904_, int p_38905_, int p_38906_, boolean p_38907_) {
		boolean flag = false;
		int i = p_38905_;
		if (p_38907_) {
			i = p_38906_ - 1;
		}
		if (p_38904_.isStackable()) {
			while (!p_38904_.isEmpty() && (p_38907_ ? i >= p_38905_ : i < p_38906_)) {
				Slot slot = this.slots.get(i);
				ItemStack itemstack = slot.getItem();
				if (slot.mayPlace(itemstack) && !itemstack.isEmpty() && ItemStack.isSameItemSameComponents(p_38904_, itemstack)) {
					int j = itemstack.getCount() + p_38904_.getCount();
					int k = slot.getMaxStackSize(itemstack);
					if (j <= k) {
						p_38904_.setCount(0);
						itemstack.setCount(j);
						slot.set(itemstack);
						flag = true;
					} else if (itemstack.getCount() < k) {
						p_38904_.shrink(k - itemstack.getCount());
						itemstack.setCount(k);
						slot.set(itemstack);
						flag = true;
					}
				}
				if (p_38907_) {
					i--;
				} else {
					i++;
				}
			}
		}
		if (!p_38904_.isEmpty()) {
			if (p_38907_) {
				i = p_38906_ - 1;
			} else {
				i = p_38905_;
			}
			while (p_38907_ ? i >= p_38905_ : i < p_38906_) {
				Slot slot1 = this.slots.get(i);
				ItemStack itemstack1 = slot1.getItem();
				if (itemstack1.isEmpty() && slot1.mayPlace(p_38904_)) {
					int l = slot1.getMaxStackSize(p_38904_);
					slot1.setByPlayer(p_38904_.split(Math.min(p_38904_.getCount(), l)));
					slot1.setChanged();
					flag = true;
					break;
				}
				if (p_38907_) {
					i--;
				} else {
					i++;
				}
			}
		}
		return flag;
	}

	@Override
	public void removed(Player playerIn) {
		super.removed(playerIn);
		if (!bound && playerIn instanceof ServerPlayer serverPlayer) {
			if (!serverPlayer.isAlive() || serverPlayer.hasDisconnected()) {
				for (int j = 0; j < internal.getSlots(); ++j) {
					playerIn.drop(internal.getStackInSlot(j), false);
					if (internal instanceof IItemHandlerModifiable ihm)
						ihm.setStackInSlot(j, ItemStack.EMPTY);
				}
			} else {
				for (int i = 0; i < internal.getSlots(); ++i) {
					playerIn.getInventory().placeItemBackInInventory(internal.getStackInSlot(i));
					if (internal instanceof IItemHandlerModifiable ihm)
						ihm.setStackInSlot(i, ItemStack.EMPTY);
				}
			}
		}
	}

	public Map<Integer, Slot> get() {
		return customSlots;
	}
}
