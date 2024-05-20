package net.nico.soccermod.entity;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.nico.soccermod.SoccerMod;
import net.nico.soccermod.entity.custom.SoccerBallEntity;

public class ModEntities {
  public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES,
      SoccerMod.MOD_ID);

  public static final RegistryObject<EntityType<SoccerBallEntity>> SOCCERBALL_ENTITY = ENTITIES.register(
      "soccerball_entity",
      () -> EntityType.Builder.<SoccerBallEntity>of(SoccerBallEntity::new, MobCategory.CREATURE)
          .sized(1f, 1f)
          .build(new ResourceLocation(SoccerMod.MOD_ID, "soccerball_entity").toString()));

  public static void register(IEventBus eventBus) {
    ENTITIES.register(eventBus);
  }
}
