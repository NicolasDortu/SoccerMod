package net.nico.soccermod.item;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.nico.soccermod.sound.ModSounds;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.InteractionHand;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.item.UseAnim;

@SuppressWarnings("null")
public class Whistle extends Item {
  public Whistle(Properties properties) {
    super(properties);
  }

  @Override
  public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand hand) {
    ItemStack itemStack = player.getItemInHand(hand);
    // Removed the check for world.isClientSide for sound and particles to ensure
    // they are executed client-side as well
    // Play a Minecraft predefined sound
    world.playSound(null, player.getX(), player.getY(), player.getZ(), ModSounds.WHISTLE_SOUND.get(),
        SoundSource.PLAYERS, 1.0F, 1.0F);

    // This is outside the world.isClientSide check to ensure the animation triggers
    // client-side
    player.startUsingItem(hand);
    return InteractionResultHolder.consume(itemStack);
  }

  @Override
  public UseAnim getUseAnimation(ItemStack stack) {
    // Use the EAT animation as a stand-in for putting the item to the mouth
    return UseAnim.TOOT_HORN; // Corrected to EAT for consistency with previous explanations
  }

  @Override
  public int getUseDuration(ItemStack stack) {
    // Define the duration of the use animation in ticks (20 ticks = 1 second)
    return 30; // Standard eating duration
  }

  @Override
  public ItemStack finishUsingItem(ItemStack stack, Level world, LivingEntity entity) {
    // Ensure the entity is a Player before casting
    if (!world.isClientSide && entity instanceof Player) {
      @SuppressWarnings("unused")
      Player player = (Player) entity;
    }

    // Proceed with finishing the use of the item
    return super.finishUsingItem(stack, world, entity);
  }

}
