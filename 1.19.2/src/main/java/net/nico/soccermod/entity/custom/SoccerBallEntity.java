package net.nico.soccermod.entity.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.registries.ForgeRegistries;
import net.nico.soccermod.entity.ModEntities;
import net.nico.soccermod.sound.ModSounds;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

@SuppressWarnings("null")
public class SoccerBallEntity extends Animal {
  public final AnimationState idleAnimationState = new AnimationState();

  public SoccerBallEntity(EntityType<? extends Animal> type, Level level) {
    super(type, level);
  }

  public SoccerBallEntity(Level level, double x, double y, double z) {
    this(ModEntities.SOCCERBALL_ENTITY.get(), level);
    setPos(x, y, z);
  }

  public SoccerBallEntity(Level level, BlockPos pos) {
    this(level, pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5);
  }

  @Nullable
  @Override
  public AgeableMob getBreedOffspring(@NotNull ServerLevel level, @NotNull AgeableMob otherParent) {
    SoccerBallEntity entity = new SoccerBallEntity(level, getX(), getY(), getZ());
    entity.finalizeSpawn(level, level.getCurrentDifficultyAt(entity.blockPosition()), MobSpawnType.BREEDING, null,
        null);
    return entity;
  }

  @Override
  protected void registerGoals() {
    this.goalSelector.addGoal(0, new FloatGoal(this));
    // this.goalSelector.addGoal(1, new LookAtPlayerGoal(this, Player.class, 1.0F));
    // this.goalSelector.addGoal(1, new PanicGoal(this, 1.25D));
    // this.goalSelector.addGoal(3, new BreedGoal(this, 1.0D));
    // // this.goalSelector.addGoal(4, new TemptGoal(this, 1.2D,
    // // Ingredient.of(ItemInit.EXAMPLE_BLOCK_ITEM.get()), false));
    // this.goalSelector.addGoal(5, new FollowParentGoal(this, 1.1D));
    // this.goalSelector.addGoal(6, new WaterAvoidingRandomStrollGoal(this, 1.0D));
    // this.goalSelector.addGoal(7, new LookAtPlayerGoal(this, Player.class, 6.0F));
    // this.goalSelector.addGoal(8, new RandomLookAroundGoal(this));
  }

  @Override
  public void tick() {
    super.tick();
    if (!this.getLevel().isClientSide()) {
      List<Player> players = this.getLevel().getEntitiesOfClass(Player.class, this.getBoundingBox().inflate(0.5));
      for (Player player : players) {
        Vec3 playerVelocity = player.getDeltaMovement();
        double yVelocity = (playerVelocity.x != 0 || playerVelocity.z != 0) ? 0.2 : 0;
        this.setYRot(player.getYRot());
        this.push(playerVelocity.x * -15, yVelocity, playerVelocity.z * -8);
      }
    }
  }

  @Override
  public boolean hurt(DamageSource source, float amount) {
    // Check if the damage source is of type "fall"
    if ("fall".equals(source.getMsgId())) {
      // Prevent any effects or damage from fall damage
      return false;
    }
    if (source.getEntity() instanceof Player) {
      Player player = (Player) source.getEntity();
      Vec3 lookDirection = player.getLookAngle(); // This gets the direction the player is facing

      // Apply strong kick force using the player's look direction
      // double kickForce = 15; // Adjust this to tweak how far the ball goes
      // double verticalBoost = 0.8; // Adjust for how much lift the ball should get
      // double baseForce = 4; // A base force to ensure there's always some movement

      // Set the entity's direction to the player's direction
      this.setYRot(player.getYRot());

      this.push(lookDirection.x * 4 + 0.3, 0.8, lookDirection.z * 4 + 0.1);
      this.playSound(ModSounds.SOCCER_BALL.get(), 1.0F, 1.0F);
      // System.out.println("Hurt Velocity: " + lookDirection.toString());
      return false;
    }
    return super.hurt(source, amount);
  }

  public static AttributeSupplier.Builder createAttributes() {
    return Mob.createMobAttributes()
        .add(Attributes.MAX_HEALTH, 5000.0D)
        .add(Attributes.MOVEMENT_SPEED, 0.0D)
        .add(ForgeMod.STEP_HEIGHT_ADDITION.get(), 0.0D)
        .add(Attributes.ARMOR_TOUGHNESS, 500.0D);
  }

  // public static boolean canSpawn(EntityType<SoccerBallEntity> tEntityType,
  // ServerLevelAccessor serverLevelAccessor,
  // MobSpawnType spawnType, BlockPos blockPos, RandomSource randomSource) {
  // return Animal.checkAnimalSpawnRules(tEntityType, serverLevelAccessor,
  // spawnType, blockPos, randomSource)
  // && !serverLevelAccessor.getLevelData().isRaining();
  // }

  @Override
  public InteractionResult mobInteract(Player player, InteractionHand hand) {
    ItemStack itemstack = player.getItemInHand(hand);
    // Check if the player's hand is empty
    if (itemstack.isEmpty()) {
      // Remove the entity
      this.remove(RemovalReason.DISCARDED);
      // Give the player a soccer ball item
      player.addItem(new ItemStack(ForgeRegistries.ITEMS.getValue(new ResourceLocation("soccermod:soccerball"))));
      // Play a smoke particle effect
      if (this.getCommandSenderWorld().getGameRules().getBoolean(GameRules.RULE_DOMOBLOOT)) {
        this.getCommandSenderWorld().addParticle(ParticleTypes.EXPLOSION, this.getX(), this.getY(), this.getZ(), 0.0D,
            0.0D, 0.0D);
      }
      return InteractionResult.SUCCESS;
    }
    return super.mobInteract(player, hand);
  }
}
