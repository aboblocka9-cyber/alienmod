package com.alienmod.client;

import com.alienmod.AlienMod;
import com.alienmod.entity.AlienEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.resources.Identifier;

public class AlienRenderer extends MobRenderer<AlienEntity, LivingEntityRenderState, AlienModel> {
    private static final Identifier TEXTURE = AlienMod.id("textures/entity/alien.png");

    public AlienRenderer(EntityRendererProvider.Context ctx) {
        super(ctx, new AlienModel(ctx.bakeLayer(AlienModel.LAYER)), 0.4f);
    }

    @Override
    public LivingEntityRenderState createRenderState() {
        return new LivingEntityRenderState();
    }

    @Override
    public Identifier getTextureLocation(LivingEntityRenderState state) {
        return TEXTURE;
    }
}
