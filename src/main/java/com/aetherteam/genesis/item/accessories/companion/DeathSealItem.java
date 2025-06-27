package com.aetherteam.genesis.item.accessories.companion;

import com.aetherteam.genesis.entity.GenesisEntityTypes;
import com.aetherteam.genesis.entity.companion.NexSpirit;
import com.aetherteam.genesis.item.GenesisDataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;

import java.util.List;

public class DeathSealItem extends CompanionItem<NexSpirit> {
    public DeathSealItem(Properties properties) {
        super(GenesisEntityTypes.NEX_SPIRIT, properties);
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        if (stack.has(GenesisDataComponents.NEX_SPIRIT_COOLDOWN)) { //todo styling
            tooltipComponents.add(Component.translatable("aether_genesis.death_seal.desc", 100 - stack.get(GenesisDataComponents.NEX_SPIRIT_COOLDOWN)).append("%"));
        }
    }
}
