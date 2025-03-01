package com.aetherteam.genesis.entity.projectile;

import com.aetherteam.aether.entity.projectile.dart.AbstractDart;
import com.aetherteam.genesis.entity.GenesisEntityTypes;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;

public class EnchantedNeedle extends AbstractDart {
    public EnchantedNeedle(EntityType<? extends EnchantedNeedle> type, Level level) {
        super(type, level);
        this.setBaseDamage(1.5);
        this.pickup = Pickup.DISALLOWED;
    }

    public EnchantedNeedle(Level level, LivingEntity shooter) {
        super(GenesisEntityTypes.ENCHANTED_NEEDLE.get(), level, shooter, new ItemStack(Items.ARROW), null);
        this.setBaseDamage(1.5);
        this.pickup = Pickup.DISALLOWED;
    }

    @Override
    protected ItemStack getDefaultPickupItem() {
        return new ItemStack(Items.ARROW);
    }
}

