//package net.winterhurricane.kio.mixin;
//
//import net.minecraft.entity.player.PlayerEntity;
//import net.minecraft.entity.player.PlayerInventory;
//import net.minecraft.item.ItemStack;
//import net.minecraft.util.collection.DefaultedList;
//import org.spongepowered.asm.mixin.Mixin;
//import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
//
//@Mixin(PlayerInventory.class)
//public class PlayerInventoryMixin {
//    private static final int BACKPACK_SLOT = 41;
//    private final DefaultedList<ItemStack> backpackSlot;
//
//    public PlayerInventory(PlayerEntity player, CallbackInfo ci) {
//        this.backpackSlot = DefaultedList.ofSize(1, ItemStack.EMPTY);
//    }
//}
