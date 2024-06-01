package net.nico.soccermod.item;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.nico.soccermod.entity.custom.SoccerBallEntity;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.InteractionHand;

@SuppressWarnings("null")
public class SoccerBallItem extends Item {
  public SoccerBallItem(Properties properties) {
    super(properties);
  }

  @Override
  public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
    // Do something when the item is used
    if (!level.isClientSide() && hand == InteractionHand.MAIN_HAND) {
      // Calculate the spawn coordinates
      double distance = 1.5D; // The distance from the player
      double dx = -Math.sin(Math.toRadians(player.getYRot())) * distance;
      double dz = Math.cos(Math.toRadians(player.getYRot())) * distance;
      double x = player.getX() + dx;
      double y = player.getY();
      double z = player.getZ() + dz;

      // Create a new instance of the custom entity
      SoccerBallEntity soccerBallEntity = new SoccerBallEntity(level, x, y, z);

      // Add the entity to the world
      level.addFreshEntity(soccerBallEntity);

      // delete the item in the inventory
      player.getInventory().removeItem(player.getMainHandItem());
    }

    return super.use(level, player, hand);
  }

}
