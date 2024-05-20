package net.nico.soccermod.item;

import net.minecraft.world.item.*;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.nico.soccermod.SoccerMod;
import net.nico.soccermod.item.ModCara.CaraItem;

@SuppressWarnings("null")
public class ModItems {
        public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS,
                        SoccerMod.MOD_ID);

        public static final RegistryObject<Item> CARA = ITEMS.register("cara",
                        () -> new CaraItem(new Item.Properties().food(ModCara.CARA)));

        public static final RegistryObject<Item> JUPILER = ITEMS.register("jupiler",
                        () -> new CaraItem(new Item.Properties().food(ModCara.JUPILER)));

        public static final RegistryObject<Item> WHISTLE = ITEMS.register("whistle",
                        () -> new Whistle(new Item.Properties().stacksTo(1)));

        public static final RegistryObject<Item> SOCCERBALLITEM = ITEMS.register("soccerball",
                        () -> new SoccerBallItem(new Item.Properties().stacksTo(1)));

        public static final RegistryObject<Item> RED_CARD = ITEMS.register("redcard",
                        () -> new CardItem(new Item.Properties()));

        public static final RegistryObject<Item> YELLOW_CARD = ITEMS.register("yellowcard",
                        () -> new CardItem(new Item.Properties()));

        public static void register(IEventBus eventBus) {
                ITEMS.register(eventBus);
        }
}
