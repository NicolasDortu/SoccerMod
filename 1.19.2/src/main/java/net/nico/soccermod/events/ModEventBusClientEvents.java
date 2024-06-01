package net.nico.soccermod.events;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.nico.soccermod.SoccerMod;
import net.nico.soccermod.entity.client.ModModelLayers;
import net.nico.soccermod.entity.client.SoccerBallEntityModel;

@Mod.EventBusSubscriber(modid = SoccerMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ModEventBusClientEvents {
  @SubscribeEvent
  public static void registerLayer(EntityRenderersEvent.RegisterLayerDefinitions event) {
    event.registerLayerDefinition(ModModelLayers.SOCCERBALL_ENTITY, SoccerBallEntityModel::createBodyLayer);
  }
}
