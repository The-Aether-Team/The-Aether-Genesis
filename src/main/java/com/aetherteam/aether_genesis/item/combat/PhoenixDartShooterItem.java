package com.aetherteam.aether_genesis.item.combat;

import com.aetherteam.aether.client.AetherSoundEvents;
import com.aetherteam.aether.entity.projectile.dart.AbstractDart;
import com.aetherteam.aether.item.combat.DartItem;
import com.aetherteam.aether.item.combat.DartShooterItem;
import com.aetherteam.aether_genesis.GenesisTags;
import com.aetherteam.aether_genesis.entity.PhoenixDart;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.ForgeEventFactory;

import java.util.function.Predicate;
import java.util.function.Supplier;

public class PhoenixDartShooterItem extends DartShooterItem {

    public PhoenixDartShooterItem(Supplier<? extends Item> dartType, Properties properties) {
        super(dartType, properties);
    }

    /**
     * Based somewhat on {@link net.minecraft.world.item.BowItem#releaseUsing(ItemStack, Level, LivingEntity, int)}, although we use {@link Item#finishUsingItem(ItemStack, Level, LivingEntity)} instead to allow for the Dart Shooter to shoot continuously without releasing.
     * @param stack The {@link ItemStack} in use.
     * @param level The {@link Level} of the user.
     * @param user The {@link LivingEntity} using the stack.
     * @return The used {@link ItemStack}.
     */
    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity user) {
        if (user instanceof Player player) {
            ItemStack ammoItem = player.getProjectile(stack); // Gets matching ammo stack from inventory according to DartShooterItem#getAllSupportedProjectiles().
                boolean creativeOrShooterIsInfinite = player.getAbilities().instabuild || stack.getEnchantmentLevel(Enchantments.INFINITY_ARROWS) > 0; // Note: Dart shooters can't be enchanted with Infinity in survival, but we still implement the behavior.
                boolean stillHasAmmo = !ammoItem.isEmpty() || creativeOrShooterIsInfinite;

                ForgeEventFactory.onArrowLoose(stack, level, player, 0, stillHasAmmo);

                if (stillHasAmmo) { // Seems to be a failsafe check; under normal circumstances this should already be true because of the checks in DartShooterItem#use().
                    if (ammoItem.isEmpty()) {
                        ammoItem = stack; // Another failsafe to create a stack if somehow the ammoItem is empty at this stage of the code; under normal circumstances this seems to never get reached.
                    }
                    boolean creativeOrDartIsInfinite = player.getAbilities().instabuild || (ammoItem.getItem() instanceof DartItem dartItem && dartItem.isInfinite(stack));

                    if (!level.isClientSide()) {
                        AbstractDart dart = createDart(level, user);
                        dart = customDart(dart);
                        dart.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, 1.0F, 1.0F);
                        dart.setNoGravity(true); // Darts have no gravity.

                        int powerModifier = stack.getEnchantmentLevel(Enchantments.POWER_ARROWS);
                        if (powerModifier > 0) {
                            dart.setBaseDamage(dart.getBaseDamage() + powerModifier * 0.5 + 0.5);
                        }

                        int punchModifier = stack.getEnchantmentLevel(Enchantments.PUNCH_ARROWS);
                        if (punchModifier > 0) {
                            dart.setKnockback(punchModifier);
                        }

                        if (creativeOrDartIsInfinite || player.getAbilities().instabuild) {
                            dart.pickup = AbstractArrow.Pickup.CREATIVE_ONLY;
                        }

                        level.addFreshEntity(dart);
                    }
                    level.playSound(null, player.getX(), player.getY(), player.getZ(), AetherSoundEvents.ITEM_DART_SHOOTER_SHOOT.get(), SoundSource.PLAYERS, 1.0F, 1.0F / (level.getRandom().nextFloat() * 0.4F + 0.8F));

                    if (!creativeOrDartIsInfinite && !player.getAbilities().instabuild) {
                        ammoItem.shrink(1);
                        if (ammoItem.isEmpty()) {
                            player.getInventory().removeItem(ammoItem);
                        }
                    }
                    player.awardStat(Stats.ITEM_USED.get(this));
                }
            }
        return stack;
    }

    @Override
    public Predicate<ItemStack> getAllSupportedProjectiles() {
        return (stack) -> stack.is(GenesisTags.Items.DARTS);
    }

    public AbstractDart customDart(AbstractDart dart) {
        return dart;
    }

    public AbstractDart createDart(Level level, LivingEntity shooter) {
        AbstractDart dart = new PhoenixDart(level);
        dart.setPos(shooter.getX(), shooter.getEyeY() - 0.1D, shooter.getZ());
        dart.setOwner(shooter);
        return dart;
    }
}