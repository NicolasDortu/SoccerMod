package net.nico.soccermod;

import com.mojang.logging.LogUtils;

import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.nico.soccermod.block.ModBlocks;
import net.nico.soccermod.effect.ModEffects;
import net.nico.soccermod.entity.ModEntities;
import net.nico.soccermod.entity.client.SoccerBallEntityRenderer;
import net.nico.soccermod.item.ModCreativeModTabs;
import net.nico.soccermod.item.ModItems;
import net.nico.soccermod.sound.ModSounds;

import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(SoccerMod.MOD_ID)
public class SoccerMod {
    // Define mod id in a common place for everything to reference
    public static final String MOD_ID = "soccermod";
    // Directly reference a slf4j logger
    @SuppressWarnings("unused")
    private static final Logger LOGGER = LogUtils.getLogger();

    public SoccerMod() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModCreativeModTabs.register(modEventBus);

        ModItems.register(modEventBus);
        ModBlocks.register(modEventBus);

        modEventBus.addListener(this::commonSetup);

        MinecraftForge.EVENT_BUS.register(this);
        modEventBus.addListener(this::addCreative);

        ModSounds.SOUND_EVENTS.register(FMLJavaModLoadingContext.get().getModEventBus());

        ModEffects.register(modEventBus);

        ModEntities.register(modEventBus);

    }

    private void commonSetup(final FMLCommonSetupEvent event) {
    }

    // Add the example block item to the building blocks tab
    private void addCreative(BuildCreativeModeTabContentsEvent event) {

    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {

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
