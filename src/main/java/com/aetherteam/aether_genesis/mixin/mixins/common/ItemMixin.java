package com.aetherteam.aether_genesis.mixin.mixins.common;

import com.aetherteam.aether.item.AetherItems;
import com.aetherteam.aether_genesis.GenesisConfig;
import net.minecraft.world.item.Item;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Item.class)
public class ItemMixin {
    /**
     * Changes Golden Parachutes to have only one use.
     */
    @Inject(at = @At(value = "HEAD"), method = "getMaxDamage()I", cancellable = true)
    private void getMaxDamage(CallbackInfoReturnable<Integer> cir) {
        Item item = (Item) (Object) this;
        if (item == AetherItems.GOLDEN_PARACHUTE.get() && GenesisConfig.COMMON.gold_aercloud_ability.get()) {
            cir.setReturnValue(1);
        }
    }
}
