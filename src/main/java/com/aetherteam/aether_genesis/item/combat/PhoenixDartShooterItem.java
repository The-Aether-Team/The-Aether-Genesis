package com.aetherteam.aether_genesis.item.combat;

import com.aetherteam.aether.entity.projectile.dart.AbstractDart;
import com.aetherteam.aether.item.combat.DartShooterItem;
import com.aetherteam.aether_genesis.GenesisTags;
import com.aetherteam.aether_genesis.entity.PhoenixDart;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import java.util.function.Predicate;
import java.util.function.Supplier;

public class PhoenixDartShooterItem extends DartShooterItem {

    public PhoenixDartShooterItem(Supplier<? extends Item> dartType, Properties properties) {
        super(dartType, properties);
    }

    @Override
    public Predicate<ItemStack> getAllSupportedProjectiles() {
        return (stack) -> stack.is(GenesisTags.Items.DARTS);
    }

    public AbstractDart customDart(AbstractDart dart) {
        PhoenixDart phoenixdart = new PhoenixDart(dart.level);
        phoenixdart.setOwner(dart.getOwner());
        if(phoenixdart.getOwner() != null)
        phoenixdart.setPos(phoenixdart.getOwner().getX(), phoenixdart.getOwner().getEyeY() - 0.1D, phoenixdart.getOwner().getZ());
        return phoenixdart;
    }
}