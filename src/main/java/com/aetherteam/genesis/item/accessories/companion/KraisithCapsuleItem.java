package com.aetherteam.genesis.item.accessories.companion;

import com.aetherteam.genesis.entity.GenesisEntityTypes;
import com.aetherteam.genesis.entity.companion.Kraisith;
import com.aetherteam.genesis.item.components.GenesisDataComponents;
import io.wispforest.accessories.api.slot.SlotReference;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;

import java.util.List;

public class KraisithCapsuleItem extends CompanionItem<Kraisith> {
    public KraisithCapsuleItem(Properties properties) {
        super(GenesisEntityTypes.KRAISITH, properties);
    }

    @Override
    public void tick(ItemStack stack, SlotReference reference) {
        super.tick(stack, reference);
        Boolean downed = stack.get(GenesisDataComponents.COMPANION_DOWNED);
        Integer health = stack.get(GenesisDataComponents.COMPANION_HEALTH);
        Integer maxHealth = stack.get(GenesisDataComponents.COMPANION_MAX_HEALTH);
        if (downed != null && health != null && maxHealth != null) {
            if (downed) {
                if (health < maxHealth) {
                    if (!reference.entity().level().isClientSide() && reference.entity().tickCount % 10 == 0) {
                        stack.set(GenesisDataComponents.COMPANION_HEALTH, Math.min(health + 1, maxHealth));
                    }
                } else if (health.equals(maxHealth)) {
                    stack.set(GenesisDataComponents.COMPANION_DOWNED, false);
                    this.onEquip(stack, reference);
                }
            }
        }
    }

    @Override
    public void onEquip(ItemStack stack, SlotReference reference) {
        Boolean downed = stack.get(GenesisDataComponents.COMPANION_DOWNED);
        if (downed == null || !downed) {
            super.onEquip(stack, reference);
        }
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        Boolean downed = stack.get(GenesisDataComponents.COMPANION_DOWNED);
        Integer health = stack.get(GenesisDataComponents.COMPANION_HEALTH);
        Integer maxHealth = stack.get(GenesisDataComponents.COMPANION_MAX_HEALTH);
        if (downed != null && downed && health != null && maxHealth != null) {
            tooltipComponents.add(Component.translatable("aether_genesis.companion_health.desc", health, maxHealth).withStyle(ChatFormatting.GRAY));
        }
    }
}
