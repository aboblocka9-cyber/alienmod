package com.alienmod.client;

import com.alienmod.AlienMod;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;

public class AlienModClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        EntityModelLayerRegistry.registerModelLayer(AlienModel.LAYER, AlienModel::createBodyLayer);
        EntityRendererRegistry.register(AlienMod.ALIEN, AlienRenderer::new);
    }
}
