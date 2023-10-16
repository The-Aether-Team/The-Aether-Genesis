package com.aetherteam.aether_genesis.item.accessories;

import com.aetherteam.aether.item.accessories.abilities.SlowFallAccessory;
import com.aetherteam.aether_genesis.entity.companion.ShadeOfArkenzus;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import top.theillusivec4.curios.api.SlotContext;

import java.util.function.Supplier;

public class OrbOfArkenzus extends CompanionItem<ShadeOfArkenzus> implements SlowFallAccessory {
    public OrbOfArkenzus(Supplier<EntityType<ShadeOfArkenzus>> companionType, Properties properties) {
        super(companionType, properties);
    }

    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {
        this.handleSlowFall(slotContext.entity());
    }

    @Override
    public boolean canWalkOnPowderedSnow(ItemStack stack, LivingEntity wearer) {
        return true;
    }
}
