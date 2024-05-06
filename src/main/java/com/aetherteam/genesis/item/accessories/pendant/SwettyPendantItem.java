package com.aetherteam.genesis.item.accessories.pendant;

import com.aetherteam.aether.client.AetherSoundEvents;
import com.aetherteam.aether.item.accessories.pendant.PendantItem;
import com.aetherteam.genesis.AetherGenesis;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.Vec3;
import top.theillusivec4.curios.api.SlotContext;

import static com.aetherteam.aether.item.AetherItems.AETHER_LOOT;

public class SwettyPendantItem extends PendantItem {
    public SwettyPendantItem() {
        super(new ResourceLocation(AetherGenesis.MODID, "swetty_pendant"), AetherSoundEvents.ITEM_ACCESSORY_EQUIP_ICE_PENDANT, new Item.Properties().stacksTo(1).rarity(AETHER_LOOT));
    }

    /**
     * Slows the wearer's downwards and horizontal movement when colliding against a wall.
     *
     * @param slotContext The {@link SlotContext} of the Curio.
     * @param stack       The Curio {@link ItemStack}.
     */
    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {
        LivingEntity livingEntity = slotContext.entity();
        if (!livingEntity.isShiftKeyDown() && livingEntity.horizontalCollision) {
            livingEntity.resetFallDistance();
            double d0 = Mth.clamp(livingEntity.getDeltaMovement().x, -0.15F, 0.15F);
            double d1 = Mth.clamp(livingEntity.getDeltaMovement().z, -0.15F, 0.15F);
            double d2 = Math.max(livingEntity.getDeltaMovement().y, -0.15F);
            livingEntity.setDeltaMovement(new Vec3(d0, d2, d1));
        }
    }
}
