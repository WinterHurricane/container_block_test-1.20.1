package net.winterhurricane.kio.block.entity;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.winterhurricane.kio.screen.kioscreenhandlers.backpack.BackpackScreenHandler;
import org.jetbrains.annotations.Nullable;

public class BackpackBlockEntity extends BlockEntity implements NamedScreenHandlerFactory, ImplementedInventory {
    private DefaultedList<ItemStack> inventory = DefaultedList.ofSize(70, ItemStack.EMPTY);

    public BackpackBlockEntity(BlockPos pos, BlockState state) {
        super(KIOBlockEntities.BACKPACK_BLOCK_ENTITY, pos, state);
    }

    @Override
    public int size() {
        return 70;
    }

    @Override
    public Text getDisplayName() {
        return Text.translatable("screen.kio.backpack");
    }

    @Nullable
    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
        return new BackpackScreenHandler(syncId, playerInventory, this);
    }

    @Override
    public DefaultedList<ItemStack> getItems() {
        return inventory;
    }

    @Override
    protected void writeNbt(NbtCompound nbt){
        super.writeNbt(nbt);
        Inventories.writeNbt(nbt, inventory);
    }

    @Override
    public void readNbt(NbtCompound nbt){
        Inventories.readNbt(nbt, inventory);
        super.readNbt(nbt);
    }
}
