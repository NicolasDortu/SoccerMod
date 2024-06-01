package net.nico.soccermod.entity.client;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.nico.soccermod.SoccerMod;
import net.nico.soccermod.entity.custom.SoccerBallEntity;

import org.jetbrains.annotations.NotNull;

@SuppressWarnings("null")
public class SoccerBallEntityRenderer extends MobRenderer<SoccerBallEntity, SoccerBallEntityModel> {
  public static final ResourceLocation TEXTURE = new ResourceLocation(SoccerMod.MOD_ID,
      "textures/entity/soccerball_entity.png");

  public SoccerBallEntityRenderer(EntityRendererProvider.Context ctx) {
    super(ctx, new SoccerBallEntityModel(ctx.bakeLayer(ModModelLayers.SOCCERBALL_ENTITY)), 0.25F);
  }

  @Override
  public @NotNull ResourceLocation getTextureLocation(@NotNull SoccerBallEntity entity) {
    return TEXTURE;
  }
}
