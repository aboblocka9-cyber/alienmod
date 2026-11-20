package com.alienmod.client;

import com.alienmod.AlienMod;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.ModelLayerRegistry;

public class AlienModClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ModelLayerRegistry.registerModelLayer(AlienModel.LAYER, AlienModel::createBodyLayer);
        EntityRendererRegistry.register(AlienMod.ALIEN, AlienRenderer::new);
    }
}
