package com.aetherteam.aether_genesis.item.accessories.companion;

import com.aetherteam.aether_genesis.entity.GenesisEntityTypes;
import com.aetherteam.aether_genesis.entity.companion.NexSpirit;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import top.theillusivec4.curios.api.SlotContext;

public class DeathSealItem extends CompanionItem<NexSpirit> {
    public DeathSealItem(Properties properties) {
        super(GenesisEntityTypes.NEX_SPIRIT, properties);
    }

    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {
        LivingEntity livingEntity = slotContext.entity();
        if (stack.getTag() != null && stack.getTag().contains("Cooldown")) {
            int cooldown = stack.getTag().getInt("Cooldown");
            if (livingEntity.tickCount % 10 == 0) {
                if (cooldown > 0) {
                    stack.getTag().putInt("Cooldown", cooldown - 1); //todo cant change nbt of equipped curio, maybe i have to do itemstack capability or handle cooldown in the player capability somehow per item.
                }
            }
        }
    }
}
