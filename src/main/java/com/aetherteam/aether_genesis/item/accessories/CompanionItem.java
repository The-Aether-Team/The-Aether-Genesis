package com.aetherteam.aether_genesis.item.accessories;

import com.aetherteam.aether.Aether;
import com.aetherteam.aether.item.accessories.AccessoryItem;
import com.aetherteam.aether_genesis.capability.player.GenesisPlayer;
import com.aetherteam.aether_genesis.entity.companion.Companion;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import top.theillusivec4.curios.api.SlotContext;

import java.util.function.Supplier;

public class CompanionItem extends AccessoryItem {
    private final Supplier<? extends EntityType<? extends Entity>> companionType;

    public CompanionItem(Supplier<? extends EntityType<? extends Entity>> companionType, Properties properties) {
        super(properties);
        this.companionType = companionType;
    }

    @Override
    public void onEquip(SlotContext slotContext, ItemStack prevStack, ItemStack stack) {
        LivingEntity wearer = slotContext.entity();
        if (wearer.getLevel() instanceof ServerLevel serverLevel) {
            Entity entity = this.getCompanionType().spawn(serverLevel, wearer.blockPosition(), MobSpawnType.MOB_SUMMONED);
            if (entity != null && wearer instanceof Player player) {
                GenesisPlayer.get(player).ifPresent(genesisPlayer -> genesisPlayer.addCompanion(entity));
            }
        }
        super.onEquip(slotContext, prevStack, stack);
    }

    @Override
    public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
        LivingEntity wearer = slotContext.entity();
        if (wearer instanceof Player player) {
            GenesisPlayer.get(player).ifPresent(genesisPlayer -> genesisPlayer.removeCompanion((entity) -> entity.getType() == this.getCompanionType()));
        }
        super.onUnequip(slotContext, newStack, stack);
    }

    public EntityType<? extends Entity> getCompanionType() {
        return this.companionType.get();
    }
}
