package net.winterhurricane.kio;

import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.winterhurricane.kio.screen.BackpackScreen;
import net.winterhurricane.kio.screen.KIOScreenHandlers;

public class KIOClient implements ClientModInitializer {

    @Override
    public void onInitializeClient(){
        HandledScreens.register(KIOScreenHandlers.BACKPACK_SCREEN_HANDLER, BackpackScreen::new);
    }
}
