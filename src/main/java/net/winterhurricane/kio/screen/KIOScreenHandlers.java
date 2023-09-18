package net.winterhurricane.kio.screen;

import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.resource.featuretoggle.FeatureFlags;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;

public class KIOScreenHandlers {
    public static final ScreenHandlerType<BackpackScreenHandler> BACKPACK_SCREEN_HANDLER =
            new ScreenHandlerType<>(BackpackScreenHandler::new, FeatureFlags.VANILLA_FEATURES);

    public static void registerAllScreenHandlers() {
        Registry.register(Registries.SCREEN_HANDLER, new Identifier("kio_backpack_screen_handler"), BACKPACK_SCREEN_HANDLER);
    }
}
