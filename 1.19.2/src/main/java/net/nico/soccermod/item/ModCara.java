package net.nico.soccermod.item;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.nico.soccermod.effect.ModEffects;
import net.nico.soccermod.sound.ModSounds;

@SuppressWarnings("null")
public class ModCara {
  public static final FoodProperties CARA = (new FoodProperties.Builder()).nutrition(2).saturationMod(0.3F)
      .effect(() -> new MobEffectInstance(ModEffects.DRUNK.get(), 1500), 1).build();

  public static final FoodProperties JUPILER = (new FoodProperties.Builder()).nutrition(3).saturationMod(0.3F)
      .effect(() -> new MobEffectInstance(ModEffects.DRUNK.get(), 3000), 1).build();

  public static class CaraItem extends Item {
    public CaraItem(Properties properties) {
      super(properties);
    }

    // Override getEatingSound to return the drinking sound
    @Override
    public SoundEvent getEatingSound() {
      // This will play the default drinking sound while consuming the item
      return SoundEvents.GENERIC_DRINK;
    }

    // Override finishUsingItem to play your custom sound at the end
    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level world, LivingEntity entity) {
      // Play the custom sound here without checking if world is client-side
      // This ensures the sound is played on the client where it's needed
      entity.playSound(ModSounds.CARA_SOUND.get(), 3.0F, 1.0F);

      // Call super to ensure the item is consumed properly and any effects are
      // applied
      return super.finishUsingItem(stack, world, entity);
    }
  }
}
