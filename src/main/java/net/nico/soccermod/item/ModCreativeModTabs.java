package net.nico.soccermod.item;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import net.nico.soccermod.SoccerMod;

public class ModCreativeModTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister
            .create(Registries.CREATIVE_MODE_TAB, SoccerMod.MOD_ID);

    @SuppressWarnings("null")
    public static final RegistryObject<CreativeModeTab> SOCCER_TAB = CREATIVE_MODE_TABS.register("soccer_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.SOCCERBALLITEM.get()))
                    .title(Component.translatable("creativetab.soccer_tab"))
                    .displayItems((pParameters, pOutput) -> {
                        // pOutput.accept(ModItems.JEKO_MUSIC_DISC.get());
                        // pOutput.accept(ModItems.TOKYODRIFT_MUSIC_DISC.get());
                        pOutput.accept(ModItems.CARA.get());
                        pOutput.accept(ModItems.JUPILER.get());
                        pOutput.accept(ModItems.SOCCERBALLITEM.get());
                        pOutput.accept(ModItems.RED_CARD.get());
                        pOutput.accept(ModItems.YELLOW_CARD.get());
                        pOutput.accept(ModItems.WHISTLE.get());
                    })

                    .build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}