package com.aetherteam.aether_genesis.event.hooks;

import com.aetherteam.aether.entity.AetherEntityTypes;
import com.aetherteam.aether.entity.monster.Zephyr;
import com.aetherteam.aether_genesis.GenesisConfig;
import com.aetherteam.aether_genesis.attachment.GenesisDataAttachments;
import com.aetherteam.aether_genesis.attachment.ZephyrColorAttachment;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.LivingEntity;
import net.neoforged.neoforge.event.entity.EntityEvent;
import net.neoforged.neoforge.event.entity.EntityJoinLevelEvent;
import net.neoforged.neoforge.event.entity.living.MobSpawnEvent;
import org.apache.commons.lang3.tuple.Pair;

public class EntityHooks {
    /**
     * @see com.aetherteam.aether_genesis.event.listeners.EntityListener#onJoin(EntityJoinLevelEvent)
     */
    public static void setZephyrColor(Entity entity) {
        if (GenesisConfig.COMMON.tan_zephyr_variation.get()) {
            if (entity.getType() == AetherEntityTypes.ZEPHYR.get() && entity instanceof Zephyr zephyr) {
                if (zephyr.getRandom().nextInt(10) == 0) {
                    zephyr.getData(GenesisDataAttachments.ZEPHYR_COLOR).setTan(true);
                    zephyr.refreshDimensions();
                }
            }
        }
    }

    /**
     * @see com.aetherteam.aether_genesis.event.listeners.EntityListener#onSize(EntityEvent.Size)
     */
    public static Pair<EntityDimensions, Boolean> determineZephyrSize(Entity entity) {
        if (entity.getType() == AetherEntityTypes.ZEPHYR.get() && entity instanceof Zephyr zephyr) {
            ZephyrColorAttachment attachment = zephyr.getData(GenesisDataAttachments.ZEPHYR_COLOR);
            if (attachment.isTan()) {
                return Pair.of(EntityDimensions.fixed(3.5F, 2.25F), true);
            }
        }
        return null;
    }

    /**
     * @see com.aetherteam.aether_genesis.event.listeners.EntityListener#finalizeSpawn(MobSpawnEvent.FinalizeSpawn)
     */
    public static boolean shouldStopZephyrSpawn(LivingEntity zephyr) {
        if (zephyr.getType() == AetherEntityTypes.ZEPHYR.get() && !zephyr.level().isClientSide()) {
            return zephyr.level().isNight();
        }
        return false;
    }

    /**
     * @see com.aetherteam.aether_genesis.event.listeners.EntityListener#allowDespawn(MobSpawnEvent.AllowDespawn)
     */
    public static boolean shouldZephyrDespawn(LivingEntity zephyr) {
        return zephyr.getType() == AetherEntityTypes.ZEPHYR.get() && !zephyr.level().isClientSide() && zephyr.level().isNight() && zephyr.getRandom().nextInt(100) == 0;
    }
}
