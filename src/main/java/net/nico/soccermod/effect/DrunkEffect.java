package net.nico.soccermod.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

public class DrunkEffect extends MobEffect {
  public DrunkEffect(MobEffectCategory mobEffectCategory, int color) {
    super(mobEffectCategory, color);
  }

  @Override
  public void applyEffectTick(@SuppressWarnings("null") LivingEntity entity, int amplifier) {
    if (entity instanceof Player) {
      Player player = (Player) entity;
      if (player.hasEffect(ModEffects.DRUNK.get())) {
        // Apply Resistance effect
        player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 2, 0, false, false));

      }
    }

    super.applyEffectTick(entity, amplifier);
  }

  @Override
  public boolean isDurationEffectTick(int pDuration, int pAmplifier) {
    return true;
  }
}
