package net.winterhurricane.kio.screen.kioscreenhandlers.backpack;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;
import net.winterhurricane.kio.screen.KIOScreenHandlers;

public class BackpackScreenHandler extends ScreenHandler {
    private final Inventory inventory;

    int containerHeight = 133;
    int inventoryHeight = 176;
    int toolbeltHeight = 47;
    int backpackWidth = 69;
    int inventoryWidth = 176;

    public BackpackScreenHandler(int syncId, PlayerInventory playerInventory) {
        this(syncId, playerInventory, new SimpleInventory(70));
    }

    public BackpackScreenHandler(int syncId, PlayerInventory playerInventory, Inventory inventory){
        super(KIOScreenHandlers.BACKPACK_SCREEN_HANDLER, syncId);
        checkSize(inventory, 70);
        this.inventory = inventory;
        inventory.onOpen(playerInventory.player);
        for (int j = 0; j < 6; ++j) {
            for (int i = 0; i < 9; ++i) {
                this.addSlot(new Slot(inventory, i + j * 9, 8 + backpackWidth + i * 18, 18 + j * 18));
            }
        }
        addPlayerInventory(playerInventory);
        addPlayerHotbar(playerInventory);
        addPlayerArmorAndOffhand(playerInventory);
        addBackpackSlots(inventory);
        addBackpackToolbar(inventory);
    }

    private void addPlayerInventory(PlayerInventory playerInventory) {
        for (int j = 0; j < 3; ++j) {
            for (int i = 0; i < 9; ++i){
                this.addSlot(new Slot(playerInventory, i + j * 9 + 9, 8 + backpackWidth + i * 18, 94 + containerHeight + j * 18));
            }
        }
    }
    private void addPlayerHotbar(PlayerInventory playerInventory) {
        for (int i = 0; i < 9; ++i){
            this.addSlot(new Slot(playerInventory, i, 8 + backpackWidth + i * 18, 152 + containerHeight));
        }
    }

    private void addPlayerArmorAndOffhand(PlayerInventory playerInventory) {
        for (int i = 0; i < 4; ++i){
            this.addSlot(new Slot(playerInventory, 39 - i, 8 + backpackWidth, 8 + containerHeight + i *18));
        }
        this.addSlot(new Slot(playerInventory, 40, 77 + backpackWidth, 62 + containerHeight));
    }

    private void addBackpackSlots(Inventory inventory) {
        for (int i =0; i < 3; ++i) {
            for (int j = 0; j < 9; ++j) {
                // Left Side
                this.addSlot(new Slot(inventory, i + j * 3, 8 + i * 18, containerHeight + 8 + j * 18));
                // Right Side
                this.addSlot(new Slot(inventory, 27 + i + j * 3, 9 + inventoryWidth + backpackWidth + i * 18,
                        containerHeight + 8 + j * 18));
            }
        }
    }

    private void addBackpackToolbar(Inventory inventory) {
        for (int i = 0; i < 4; ++i) {
            for (int j = 0; j < 2; ++j) {
                // Left side: tools
                this.addSlot(new Slot(inventory, 54+ i + j * 4, 11 + backpackWidth + i * 18,
                        7 + inventoryHeight + containerHeight + j * 18));
                // Right side: offhand
                this.addSlot(new Slot(inventory, 62 + i + j * 4, 95 + backpackWidth + i * 18,
                        7 + inventoryHeight + containerHeight + j * 18));
            }
        }
    }

    @Override
    public boolean canUse(PlayerEntity player) {
        return this.inventory.canPlayerUse(player);
    }

    @Override
    public ItemStack quickMove(PlayerEntity player, int invSlot) {
        ItemStack newStack = ItemStack.EMPTY;
        Slot slot = this.slots.get(invSlot);
        if (slot != null && slot.hasStack()) {
            ItemStack originalStack = slot.getStack();
            newStack = originalStack.copy();
            if (invSlot < this.inventory.size()) {
                if (!this.insertItem(originalStack, this.inventory.size(), this.slots.size(), true)){
                    return ItemStack.EMPTY;
                }
            } else if (!this.insertItem(originalStack, 0, this.inventory.size(), false)) {
                return ItemStack.EMPTY;
            }

            if (originalStack.isEmpty()) {
                slot.setStack(ItemStack.EMPTY);
            } else {
                slot.markDirty();
            }
        }
        return newStack;
    }
}
