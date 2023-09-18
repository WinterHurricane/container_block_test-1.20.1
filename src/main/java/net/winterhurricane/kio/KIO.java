package net.winterhurricane.kio;

import net.fabricmc.api.ModInitializer;

import net.winterhurricane.kio.block.entity.KIOBlockEntities;
import net.winterhurricane.kio.screen.KIOScreenHandlers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class KIO implements ModInitializer {
    public static final Logger LOGGER = LoggerFactory.getLogger("kio");

	@Override
	public void onInitialize() {
		KIOBlockEntities.registerBlockEntities();
		KIOScreenHandlers.registerAllScreenHandlers();
	}
}