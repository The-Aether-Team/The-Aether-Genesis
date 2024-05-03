package com.aetherteam.genesis.entity.companion;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

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
        if (this.level().isClientSide()) { // Rotates the arm parts around the Wisp.
            this.armRotO = this.armRot;
            this.armRot += 0.5F;
        }
    }

    /**
     * @return The old {@link Float} rotation value for the arms of the Wisp.
     */
    public float getArmRotO() {
        return this.armRotO;
    }

    /**
     * @return The {@link Float} rotation value for the arms of the Wisp.
     */
    public float getArmRot() {
        return this.armRot;
    }
}
