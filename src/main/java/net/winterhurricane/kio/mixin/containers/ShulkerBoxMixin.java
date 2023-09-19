//package net.winterhurricane.kio.mixin.containers;
//
//import net.minecraft.block.entity.LootableContainerBlockEntity;
//import net.minecraft.block.entity.ShulkerBoxBlockEntity;
//import net.minecraft.client.gui.screen.Screen;
//import net.minecraft.entity.player.PlayerInventory;
//import net.minecraft.inventory.SidedInventory;
//import net.minecraft.screen.ScreenHandler;
//import net.minecraft.screen.ShulkerBoxScreenHandler;
//import org.spongepowered.asm.mixin.Mixin;
//import org.spongepowered.asm.mixin.injection.At;
//import org.spongepowered.asm.mixin.injection.Inject;
//import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
//
//@Mixin(ShulkerBoxBlockEntity.class)
//public class ShulkerBoxMixin {
//
//    @Inject(at = @At("HEAD"), method = "createScreenHandler")
//    protected ScreenHandler createScreenHandler(int syncId, PlayerInventory playerInventory, CallbackInfoReturnable<ScreenHandler> cir) {
//        return new ShulkerBoxScreenHandler(syncId, playerInventory, this);
//    }
//
//}
