package net.nico.soccermod.events;

import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.nico.soccermod.SoccerMod;
import net.nico.soccermod.entity.ModEntities;
import net.nico.soccermod.entity.custom.SoccerBallEntity;

@Mod.EventBusSubscriber(modid = SoccerMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEventBusEvents {
  @SubscribeEvent
  public static void registerAttributes(EntityAttributeCreationEvent event) {
    event.put(ModEntities.SOCCERBALL_ENTITY.get(), SoccerBallEntity.createAttributes().build());
  }
}
