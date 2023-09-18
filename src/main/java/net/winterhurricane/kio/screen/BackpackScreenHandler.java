package net.winterhurricane.kio.screen;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;

public class BackpackScreenHandler extends ScreenHandler {
    private final Inventory inventory;

    public BackpackScreenHandler(int syncId, PlayerInventory playerInventory) {
        this(syncId, playerInventory, new SimpleInventory(54));
    }

    public BackpackScreenHandler(int syncId, PlayerInventory playerInventory, Inventory inventory){
        super(KIOScreenHandlers.BACKPACK_SCREEN_HANDLER, syncId);
        checkSize(inventory, 54);
        this.inventory = inventory;
        inventory.onOpen(playerInventory.player);
        for (int j = 0; j < 6; ++j) {
            for (int i = 0; i < 9; ++i) {
                this.addSlot(new Slot(inventory, i + j * 9, 8 + i * 18, -10 + j * 18));
            }
        }
        addPlayerInventory(playerInventory);
        addPlayerHotbar(playerInventory);
    }

    private void addPlayerInventory(PlayerInventory playerInventory) {
        for (int j = 0; j < 3; ++j) {
            for (int i = 0; i < 9; ++i){
                this.addSlot(new Slot(playerInventory, i + j * 9 + 9, 8 + i * 18, 112 + j * 18));
            }
        }
    }
    private void addPlayerHotbar(PlayerInventory playerInventory) {
        for (int i = 0; i < 9; ++i){
            this.addSlot(new Slot(playerInventory, i, 8 + i * 18, 170));
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
