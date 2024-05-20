package net.nico.soccermod.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;

import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.nico.soccermod.entity.animations.SoccerBallEntityAnimation;
import net.nico.soccermod.entity.custom.SoccerBallEntity;

import org.jetbrains.annotations.NotNull;

@SuppressWarnings("null")
public class SoccerBallEntityModel extends HierarchicalModel<SoccerBallEntity> {
  // public static final ModelLayerLocation LAYER_LOCATION = new
  // ModelLayerLocation(
  // new ResourceLocation(soccermod.MOD_ID, "soccerball_entity"), "main");

  private final ModelParts parts;

  public SoccerBallEntityModel(ModelPart root) {
    ModelPart body = root.getChild("body");
    ModelPart head = body.getChild("head");

    this.parts = new ModelParts(body, head);
  }

  public static LayerDefinition createBodyLayer() {
    MeshDefinition meshdefinition = new MeshDefinition();
    PartDefinition partdefinition = meshdefinition.getRoot();

    PartDefinition body = partdefinition.addOrReplaceChild("body",
        CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, -2.0F, -1.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)),
        PartPose.offset(0.0F, 23.0F, 0.0F));

    @SuppressWarnings("unused")
    PartDefinition head = body.addOrReplaceChild("head",
        CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -5.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)),
        PartPose.offset(0.0F, -2.0F, 0.0F));

    return LayerDefinition.create(meshdefinition, 32, 32);
  }

  @Override
  public void setupAnim(@NotNull SoccerBallEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks,
      float netHeadYaw, float headPitch) {
    root().getAllParts().forEach(ModelPart::resetPose);
    // animate(entity.idleAnimationState, SoccerBallEntityAnimation.IDLE,
    // ageInTicks);
    // animate(entity.attackAnimationState, ExampleAnimatedEntityAnimation.ATTACK,
    // ageInTicks);
    // etc...

    animateWalk(SoccerBallEntityAnimation.WALK, limbSwing, limbSwingAmount, 10.0F, 2.5F);
  }

  @Override
  public void renderToBuffer(@NotNull PoseStack poseStack, @NotNull VertexConsumer vertexConsumer, int packedLight,
      int packedOverlay, float red, float green, float blue, float alpha) {
    this.parts.body().render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
  }

  @Override
  public ModelPart root() {
    return this.parts.body();
  }

  private record ModelParts(ModelPart body, ModelPart head) {
  }
}
