package com.aetherteam.genesis.item.combat;

import com.aetherteam.aether.entity.projectile.dart.AbstractDart;
import com.aetherteam.aether.item.AetherItems;
import com.aetherteam.aether.item.combat.DartShooterItem;
import com.aetherteam.genesis.GenesisTags;
import com.aetherteam.genesis.entity.projectile.PhoenixDart;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import java.util.function.Predicate;

public class PhoenixDartShooterItem extends DartShooterItem {
    public PhoenixDartShooterItem() {
        super(AetherItems.GOLDEN_DART, new Item.Properties().stacksTo(1).rarity(AetherItems.AETHER_LOOT));
    }

    @Override
    public Predicate<ItemStack> getAllSupportedProjectiles() {
        return (stack) -> stack.is(GenesisTags.Items.DARTS);
    }

    @Override
    public AbstractDart customDart(AbstractDart dart) {
        PhoenixDart phoenixDart = new PhoenixDart(dart.level());
        phoenixDart.setOwner(dart.getOwner());
        if (phoenixDart.getOwner() != null) {
            phoenixDart.setPos(phoenixDart.getOwner().getX(), phoenixDart.getOwner().getEyeY() - 0.1, phoenixDart.getOwner().getZ());
        }
        return phoenixDart;
    }
}