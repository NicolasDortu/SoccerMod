package net.nico.soccermod.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.nico.soccermod.SoccerMod;

public class ModEffects {
  public static final DeferredRegister<MobEffect> MOB_EFFECTS = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS,
      SoccerMod.MOD_ID);

  public static final RegistryObject<MobEffect> DRUNK = MOB_EFFECTS.register("drunk",
      () -> new DrunkEffect(MobEffectCategory.BENEFICIAL, 16776960));

  public static void register(IEventBus eventBus) {
    MOB_EFFECTS.register(eventBus);
  }
}
