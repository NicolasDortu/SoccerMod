package net.nico.soccermod.sound;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.nico.soccermod.SoccerMod;

public class ModSounds {
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS = DeferredRegister
            .create(ForgeRegistries.SOUND_EVENTS, SoccerMod.MOD_ID);

    // Burp
    public static final RegistryObject<SoundEvent> CARA_SOUND = registerSoundEvent("cara_sound");

    // SoccerBall
    public static final RegistryObject<SoundEvent> SOCCER_BALL = registerSoundEvent("kick_ball");

    // Whistle 1
    public static final RegistryObject<SoundEvent> WHISTLE_SOUND = registerSoundEvent("whistle");

    private static RegistryObject<SoundEvent> registerSoundEvent(String name) {
        ResourceLocation id = new ResourceLocation(SoccerMod.MOD_ID, name);
        return SOUND_EVENTS.register(name, () -> new SoundEvent(id));
    }

    public static void register(IEventBus eventBus) {
        SOUND_EVENTS.register(eventBus);
    }
}
