package net.nico.soccermod.events;

import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.nico.soccermod.SoccerMod;
import net.nico.soccermod.effect.ModEffects;

@SuppressWarnings("null")
@Mod.EventBusSubscriber(modid = SoccerMod.MOD_ID)
public class ModEvents {

  @SubscribeEvent
  public static void onLivingFall(LivingFallEvent event) {
    if (event.getEntity() instanceof Player) {
      Player player = (Player) event.getEntity();
      if (player.hasEffect(ModEffects.DRUNK.get())) {
        event.setCanceled(true);
      }
    }
  }
}