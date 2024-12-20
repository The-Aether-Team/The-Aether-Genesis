package com.aetherteam.genesis.item.accessories.pendant;

import com.aetherteam.aether.client.AetherSoundEvents;
import com.aetherteam.aether.item.accessories.pendant.PendantItem;
import com.aetherteam.genesis.AetherGenesis;
import io.wispforest.accessories.api.slot.SlotReference;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.Vec3;

import static com.aetherteam.aether.item.AetherItems.AETHER_LOOT;

public class SwettyPendantItem extends PendantItem {
    public SwettyPendantItem() {
        super(ResourceLocation.fromNamespaceAndPath(AetherGenesis.MODID, "swetty_pendant"), AetherSoundEvents.ITEM_ACCESSORY_EQUIP_ICE_PENDANT, new Item.Properties().stacksTo(1).rarity(AETHER_LOOT));
    }

    /**
     * Slows the wearer's downwards and horizontal movement when colliding against a wall.
     *
     * @param reference The {@link SlotReference} of the Accessory.
     * @param stack     The Accessory {@link ItemStack}.
     */
    @Override
    public void tick(ItemStack stack, SlotReference reference) {
        LivingEntity livingEntity = reference.entity();
        if (!livingEntity.isShiftKeyDown() && livingEntity.horizontalCollision) {
            livingEntity.resetFallDistance();
            double d0 = Mth.clamp(livingEntity.getDeltaMovement().x, -0.15F, 0.15F);
            double d1 = Mth.clamp(livingEntity.getDeltaMovement().z, -0.15F, 0.15F);
            double d2 = Math.max(livingEntity.getDeltaMovement().y, -0.15F);
            livingEntity.setDeltaMovement(new Vec3(d0, d2, d1));
        }
    }
}
