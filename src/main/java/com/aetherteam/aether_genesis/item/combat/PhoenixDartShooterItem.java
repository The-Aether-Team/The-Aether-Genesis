package com.aetherteam.aether_genesis.item.combat;

import com.aetherteam.aether.entity.projectile.dart.AbstractDart;
import com.aetherteam.aether.item.combat.DartShooterItem;
import com.aetherteam.aether_genesis.GenesisTags;
import com.aetherteam.aether_genesis.entity.projectile.PhoenixDart;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import java.util.function.Predicate;

import static com.aetherteam.aether.item.AetherItems.AETHER_LOOT;
import static com.aetherteam.aether.item.AetherItems.GOLDEN_DART;

public class PhoenixDartShooterItem extends DartShooterItem {
    public PhoenixDartShooterItem() {
        super(GOLDEN_DART, new Item.Properties().stacksTo(1).rarity(AETHER_LOOT)); //todo might be able to switch dart type to itemstack empty.
    }

    @Override
    public Predicate<ItemStack> getAllSupportedProjectiles() {
        return (stack) -> stack.is(GenesisTags.Items.DARTS);
    }

    public AbstractDart customDart(AbstractDart dart) {
        PhoenixDart phoenixDart = new PhoenixDart(dart.level());
        phoenixDart.setOwner(dart.getOwner());
        if (phoenixDart.getOwner() != null) {
            phoenixDart.setPos(phoenixDart.getOwner().getX(), phoenixDart.getOwner().getEyeY() - 0.1, phoenixDart.getOwner().getZ());
        }
        return phoenixDart;
    }
}