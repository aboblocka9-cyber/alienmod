package com.alienmod;

import com.alienmod.entity.AlienEntity;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SpawnEggItem;

public class AlienMod implements ModInitializer {
    public static final String MOD_ID = "alienmod";

    public static final ResourceKey<EntityType<?>> ALIEN_KEY =
            ResourceKey.create(Registries.ENTITY_TYPE, id("alien"));

    public static final EntityType<AlienEntity> ALIEN = Registry.register(
            BuiltInRegistries.ENTITY_TYPE, ALIEN_KEY,
            EntityType.Builder.of(AlienEntity::new, MobCategory.MONSTER)
                    .sized(0.6f, 1.9f)
                    .eyeHeight(1.7f)
                    .build(ALIEN_KEY));

    public static Identifier id(String path) {
        return Identifier.fromNamespaceAndPath(MOD_ID, path);
    }

    @Override
    public void onInitialize() {
        FabricDefaultAttributeRegistry.register(ALIEN, AlienEntity.createAttributes());

        ResourceKey<Item> eggKey = ResourceKey.create(Registries.ITEM, id("alien_spawn_egg"));
        Registry.register(BuiltInRegistries.ITEM, eggKey,
                new SpawnEggItem(new Item.Properties().setId(eggKey).spawnEgg(ALIEN)));
    }
}
