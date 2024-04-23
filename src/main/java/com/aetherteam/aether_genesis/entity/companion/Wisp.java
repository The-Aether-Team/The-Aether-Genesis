package com.aetherteam.aether_genesis.entity.companion;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.common.NeoForgeMod;

import java.util.function.Supplier;

public class Wisp extends FloatingCompanion {
    private float armRotO;
    private float armRot;

    public Wisp(EntityType<? extends Wisp> entityType, Level level, Supplier<ItemStack> summoningItem) {
        super(entityType, level, summoningItem);
    }

    @Override
    public void tick() {
        super.tick();
        if (this.level().isClientSide()) {
            this.armRotO = this.armRot;
            this.armRot += 0.5F;
        }
    }

    public float getArmRotO() {
        return this.armRotO;
    }

    public float getArmRot() {
        return this.armRot;
    }
}
