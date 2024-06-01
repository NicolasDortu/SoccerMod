package net.nico.soccermod.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
//import net.nico.soccermod.entity.animations.SoccerBallEntityAnimation;
import net.nico.soccermod.entity.custom.SoccerBallEntity;
import org.jetbrains.annotations.NotNull;

@SuppressWarnings("null")
public class SoccerBallEntityModel extends HierarchicalModel<SoccerBallEntity> {

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
    // Custom walk animation logic
    animateCustomWalk(entity, limbSwing, limbSwingAmount);
  }

  private void animateCustomWalk(SoccerBallEntity entity, float limbSwing, float limbSwingAmount) {
    float minMovementThreshold = 0.02F; // Increased threshold for minimum movement
    float baseRotationScaleFactor = 1.1F; // Base rotation speed scale factor

    // Check if the ball is in the air
    boolean isInAir = !entity.isOnGround();

    // Calculate the speed of the ball
    double speed = Math.sqrt(entity.getDeltaMovement().x * entity.getDeltaMovement().x
        + entity.getDeltaMovement().z * entity.getDeltaMovement().z);
    float rotationScaleFactor = (float) (baseRotationScaleFactor * speed);

    if (isInAir && limbSwingAmount > minMovementThreshold) { // If the ball is in the air and there's enough movement,
                                                             // rotate the ball
      float rotationAngle = limbSwing * 0.6662F * limbSwingAmount * rotationScaleFactor;
      this.parts.body().xRot += rotationAngle;

      // Adjust the position to keep the ball above the ground
      this.parts.body().setPos(this.parts.body().x, this.parts.body().y - 0.1F, this.parts.body().z);
    } else {
      // Stop the rotation instantly when the ball is on the ground
      this.parts.body().xRot = 0.0F;

      // Reset the position to keep the ball above the ground
      this.parts.body().setPos(this.parts.body().x, this.parts.body().y, this.parts.body().z);
    }
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
