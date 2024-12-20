package com.aetherteam.genesis.item.accessories.companion;

import com.aetherteam.aether.AetherConfig;
import com.aetherteam.aether.attachment.AetherDataAttachments;
import com.aetherteam.aether.client.AetherKeys;
import com.aetherteam.aether.mixin.mixins.common.accessor.LivingEntityAccessor;
import com.aetherteam.aether.network.packet.clientbound.SetInvisibilityPacket;
import com.aetherteam.genesis.entity.GenesisEntityTypes;
import com.aetherteam.genesis.entity.companion.EtherealWisp;
import com.aetherteam.nitrogen.attachment.INBTSynchable;
import io.wispforest.accessories.api.slot.SlotReference;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.network.PacketDistributor;

/**
 * [CODE COPY] - {@link com.aetherteam.aether.item.accessories.cape.InvisibilityCloakItem}
 */
public class EtherealStoneItem extends CompanionItem<EtherealWisp> {
    public EtherealStoneItem(Properties properties) {
        super(GenesisEntityTypes.ETHEREAL_WISP, properties);
    }

    @Override
    public void tick(ItemStack stack, SlotReference reference) {
        LivingEntity livingEntity = reference.entity();
        if (livingEntity.level().isClientSide() && livingEntity instanceof Player player) {
            if (AetherKeys.INVISIBILITY_TOGGLE.consumeClick()) {
                var data = player.getData(AetherDataAttachments.AETHER_PLAYER);
                data.setSynched(player.getId(), INBTSynchable.Direction.SERVER, "setInvisibilityEnabled", !data.isInvisibilityEnabled());
            }
        }
        if (!livingEntity.level().isClientSide() && livingEntity instanceof Player player) {
            var data = player.getData(AetherDataAttachments.AETHER_PLAYER);
            if (data.isInvisibilityEnabled()) {
                if (!AetherConfig.SERVER.balance_invisibility_cloak.get()) {
                    data.setSynched(player.getId(), INBTSynchable.Direction.CLIENT, "setWearingInvisibilityCloak", true);
                } else {
                    if (!data.attackedWithInvisibility() && !data.isWearingInvisibilityCloak()) {
                        data.setSynched(player.getId(), INBTSynchable.Direction.CLIENT, "setWearingInvisibilityCloak", true);
                    } else if (data.attackedWithInvisibility() && data.isWearingInvisibilityCloak()) {
                        data.setSynched(player.getId(), INBTSynchable.Direction.CLIENT, "setWearingInvisibilityCloak", false);
                    }
                }
            } else {
                data.setSynched(player.getId(), INBTSynchable.Direction.CLIENT, "setWearingInvisibilityCloak", false);
            }
        }
        if (!livingEntity.level().isClientSide()) {
            if (!livingEntity.isInvisible()) {
                if (livingEntity instanceof Player player) {
                    var data = player.getData(AetherDataAttachments.AETHER_PLAYER);
                    if (data.isWearingInvisibilityCloak()) {
                        player.setInvisible(true);
                        PacketDistributor.sendToAllPlayers(new SetInvisibilityPacket(player.getId(), true));
                    }
                } else {
                    livingEntity.setInvisible(true);
                }
            } else {
                if (livingEntity instanceof Player player) {
                    var data = player.getData(AetherDataAttachments.AETHER_PLAYER);
                    if (!data.isWearingInvisibilityCloak()) {
                        player.setInvisible(false);
                        PacketDistributor.sendToAllPlayers(new SetInvisibilityPacket(player.getId(), false));
                    }
                }
            }
        }
    }

    @Override
    public void onUnequip(ItemStack stack, SlotReference reference) {
        LivingEntity livingEntity = reference.entity();
        if (!livingEntity.level().isClientSide() && livingEntity instanceof Player player) {
            player.getData(AetherDataAttachments.AETHER_PLAYER).setSynched(player.getId(), INBTSynchable.Direction.CLIENT, "setWearingInvisibilityCloak", false);
        }
        livingEntity.setInvisible(false);
        ((LivingEntityAccessor) livingEntity).callUpdateEffectVisibility();
        super.onUnequip(stack, reference);
    }
}
