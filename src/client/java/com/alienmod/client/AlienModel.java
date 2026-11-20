package com.alienmod.client;

import com.alienmod.AlienMod;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;

/** Grey alien: huge head, big slanted eyes, thin torso, long spindly limbs. Texture is 64x64. */
public class AlienModel extends EntityModel<LivingEntityRenderState> {
    public static final ModelLayerLocation LAYER = new ModelLayerLocation(AlienMod.id("alien"), "main");

    private final ModelPart head, rightArm, leftArm, rightLeg, leftLeg;

    public AlienModel(ModelPart root) {
        super(root);
        this.head = root.getChild("head");
        this.rightArm = root.getChild("right_arm");
        this.leftArm = root.getChild("left_arm");
        this.rightLeg = root.getChild("right_leg");
        this.leftLeg = root.getChild("left_leg");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition root = mesh.getRoot();

        // Head: wide cranium + narrow jaw (pear shape), pivot at neck (y=5)
        PartDefinition head = root.addOrReplaceChild("head",
                CubeListBuilder.create()
                        .texOffs(0, 0).addBox(-5f, -10f, -5f, 10f, 7f, 10f)
                        .texOffs(0, 17).addBox(-3f, -3f, -4f, 6f, 3f, 8f),
                PartPose.offset(0f, 5f, 0f));

        // Eyes: big black slanted plates stuck on the front of the cranium
        head.addOrReplaceChild("right_eye",
                CubeListBuilder.create().texOffs(40, 0).addBox(-1.5f, -1.5f, -0.5f, 3f, 3f, 1f),
                PartPose.offsetAndRotation(-2.6f, -6.2f, -5.3f, 0f, 0f, 0.45f));
        head.addOrReplaceChild("left_eye",
                CubeListBuilder.create().texOffs(40, 0).addBox(-1.5f, -1.5f, -0.5f, 3f, 3f, 1f),
                PartPose.offsetAndRotation(2.6f, -6.2f, -5.3f, 0f, 0f, -0.45f));

        // Thin torso
        root.addOrReplaceChild("body",
                CubeListBuilder.create().texOffs(0, 28).addBox(-3f, -9f, -1.5f, 6f, 9f, 3f),
                PartPose.offset(0f, 14f, 0f));

        // Long arms (reach the knees)
        root.addOrReplaceChild("right_arm",
                CubeListBuilder.create().texOffs(0, 40).addBox(-1f, -1f, -1f, 2f, 14f, 2f),
                PartPose.offset(-4f, 6f, 0f));
        root.addOrReplaceChild("left_arm",
                CubeListBuilder.create().texOffs(8, 40).addBox(-1f, -1f, -1f, 2f, 14f, 2f),
                PartPose.offset(4f, 6f, 0f));

        // Thin legs
        root.addOrReplaceChild("right_leg",
                CubeListBuilder.create().texOffs(16, 40).addBox(-1f, 0f, -1f, 2f, 10f, 2f),
                PartPose.offset(-1.5f, 14f, 0f));
        root.addOrReplaceChild("left_leg",
                CubeListBuilder.create().texOffs(24, 40).addBox(-1f, 0f, -1f, 2f, 10f, 2f),
                PartPose.offset(1.5f, 14f, 0f));

        return LayerDefinition.create(mesh, 64, 64);
    }

    @Override
    public void setupAnim(LivingEntityRenderState state) {
        super.setupAnim(state);
        this.head.yRot = state.yRot * ((float) Math.PI / 180f);
        this.head.xRot = state.xRot * ((float) Math.PI / 180f);

        float pos = state.walkAnimationPos;
        float spd = state.walkAnimationSpeed;
        this.rightLeg.xRot = (float) Math.cos(pos * 0.6662f) * 1.2f * spd;
        this.leftLeg.xRot = (float) Math.cos(pos * 0.6662f + Math.PI) * 1.2f * spd;
        // arms dangle with a slight creepy sway
        float sway = (float) Math.sin(state.ageInTicks * 0.067f) * 0.04f;
        this.rightArm.xRot = (float) Math.cos(pos * 0.6662f + Math.PI) * 0.8f * spd;
        this.leftArm.xRot = (float) Math.cos(pos * 0.6662f) * 0.8f * spd;
        this.rightArm.zRot = 0.05f + sway;
        this.leftArm.zRot = -0.05f - sway;
    }
}
