package net.winterhurricane.kio.block.entity;

import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.winterhurricane.kio.block.KIOBlocks;

public class KIOBlockEntities {
    public static BlockEntityType<BackpackBlockEntity> BACKPACK_BLOCK_ENTITY;

    public static void registerBlockEntities() {
        BACKPACK_BLOCK_ENTITY = Registry.register(Registries.BLOCK_ENTITY_TYPE, new Identifier("kio", "backpack_block_entity"),
                FabricBlockEntityTypeBuilder.create(BackpackBlockEntity::new, KIOBlocks.BACKPACK_BLOCK).build(null));

    }
}
