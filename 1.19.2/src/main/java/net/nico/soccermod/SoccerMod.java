package net.nico.soccermod;

import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.nico.soccermod.effect.ModEffects;
import net.nico.soccermod.entity.ModEntities;
import net.nico.soccermod.entity.client.SoccerBallEntityRenderer;
import net.nico.soccermod.item.ModItems;
import net.nico.soccermod.sound.ModSounds;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(SoccerMod.MOD_ID)
public class SoccerMod {
    // Define mod id in a common place for everything to reference
    public static final String MOD_ID = "soccermod";
    // Directly reference a log4j logger
    @SuppressWarnings("unused")
    private static final Logger LOGGER = LogManager.getLogger();

    public SoccerMod() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        // Register the setup method for modloading
        modEventBus.addListener(this::commonSetup);

        // Register the client setup method for modloading
        modEventBus.addListener(ClientModEvents::onClientSetup);

        // Register the items, blocks, sounds, effects, entities, and creative tabs
        ModItems.register(modEventBus);
        ModSounds.SOUND_EVENTS.register(modEventBus);
        ModEffects.register(modEventBus);
        ModEntities.register(modEventBus);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        // Some common setup code
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
        // Do something when the server starts
    }

    // You can use EventBusSubscriber to automatically register all static methods
    // in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            EntityRenderers.register(ModEntities.SOCCERBALL_ENTITY.get(), SoccerBallEntityRenderer::new);
        }
    }
}
