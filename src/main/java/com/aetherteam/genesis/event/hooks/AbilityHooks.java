package com.aetherteam.genesis.event.hooks;

import com.aetherteam.aether.item.EquipmentUtil;
import com.aetherteam.genesis.advancement.GenesisAdvancementTriggers;
import com.aetherteam.genesis.attachment.GenesisDataAttachments;
import com.aetherteam.genesis.attachment.GenesisPlayerAttachment;
import com.aetherteam.genesis.entity.GenesisEntityTypes;
import com.aetherteam.genesis.entity.companion.NexSpirit;
import com.aetherteam.genesis.entity.projectile.DaggerfrostSnowball;
import com.aetherteam.genesis.entity.projectile.PhoenixDart;
import com.aetherteam.genesis.item.GenesisItems;
import com.aetherteam.genesis.network.clientbound.NexResurrectionEffectPacket;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Snowball;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.event.entity.EntityJoinLevelEvent;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;
import net.neoforged.neoforge.network.PacketDistributor;

public class AbilityHooks {
    public static class AccessoryHooks {
        /**
         * @see com.aetherteam.genesis.event.listeners.abilities.AccessoryAbilityListener#entityJoinLevel(EntityJoinLevelEvent)
         */
        public static boolean convertSnowball(Entity entity, Level level) {
            if (entity.getType() == EntityType.SNOWBALL) {
                Snowball snowball = (Snowball) entity;
                if (snowball.getOwner() instanceof LivingEntity livingEntity) {
                    if (EquipmentUtil.hasAccessory(livingEntity, GenesisItems.DAGGERFROST_LOCKET.get())) {
                        Entity createdEntity = GenesisEntityTypes.DAGGERFROST_SNOWBALL.get().create(level);
                        if (createdEntity instanceof DaggerfrostSnowball daggerfrostSnowball) {
                            daggerfrostSnowball.setDeltaMovement(snowball.getDeltaMovement());
                            daggerfrostSnowball.setPos(snowball.position());
                            daggerfrostSnowball.setOwner(snowball.getOwner());
                            daggerfrostSnowball.setItem(snowball.getItem());
                            level.addFreshEntity(daggerfrostSnowball);
                            return true;
                        }
                    }
                }
            }
            return false;
        }

        /**
         * @see com.aetherteam.genesis.event.listeners.abilities.AccessoryAbilityListener#entityDie(LivingDeathEvent)
         */
        public static boolean saveFromDeath(LivingEntity entity) {
            if (entity instanceof Player player) {
                GenesisPlayerAttachment attachment = player.getData(GenesisDataAttachments.GENESIS_PLAYER);
                for (Entity companion : attachment.getCompanions()) {
                    if (companion instanceof NexSpirit nexSpirit) {
                        if (!nexSpirit.isBroken()) {
                            player.setHealth(player.getMaxHealth());
                            nexSpirit.setCooldown(100); //todo balance
                            if (player instanceof ServerPlayer serverPlayer) {
                                GenesisAdvancementTriggers.NEX_REVIVE.get().trigger(serverPlayer);
                                PacketDistributor.sendToPlayer(serverPlayer, new NexResurrectionEffectPacket());
                            }
                            return true;
                        }
                    }
                }
            }
            return false;
        }
    }

    public static class WeaponHooks {
        /**
         * @see com.aetherteam.genesis.event.listeners.WeaponAbilityListener#onDartHurt(LivingDamageEvent.Pre)
         */
        public static void stickDart(LivingEntity entity, DamageSource source) {
            if (entity instanceof Player player && !player.level().isClientSide()) {
                Entity sourceEntity = source.getDirectEntity();
                if (sourceEntity instanceof PhoenixDart) {
                    GenesisPlayerAttachment attachment = player.getData(GenesisDataAttachments.GENESIS_PLAYER);
                    attachment.setPhoenixDartCount(attachment.getPhoenixDartCount() + 1);
                }
            }
        }
    }
}
