package com.aetherteam.genesis.event.hooks;

import com.aetherteam.aether.entity.AetherEntityTypes;
import com.aetherteam.aether.entity.monster.Zephyr;
import com.aetherteam.genesis.GenesisConfig;
import com.aetherteam.genesis.attachment.GenesisDataAttachments;
import com.aetherteam.genesis.attachment.ZephyrColorAttachment;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.ServerLevelAccessor;
import net.neoforged.neoforge.event.entity.EntityEvent;
import net.neoforged.neoforge.event.entity.EntityJoinLevelEvent;
import net.neoforged.neoforge.event.entity.living.MobSpawnEvent;

public class EntityHooks {
    /**
     * @see com.aetherteam.genesis.event.listeners.EntityListener#onJoin(EntityJoinLevelEvent)
     */
    public static void setZephyrColor(Entity entity) {
        if (GenesisConfig.SERVER.tan_zephyr_variation.get()) {
            if (entity.getType() == AetherEntityTypes.ZEPHYR.get() && entity instanceof Zephyr zephyr) {
                if (zephyr.getRandom().nextInt(10) == 0) {
                    zephyr.getData(GenesisDataAttachments.ZEPHYR_COLOR).setTan(true);
                    zephyr.refreshDimensions();
                }
            }
        }
    }

    /**
     * @see com.aetherteam.genesis.event.listeners.EntityListener#onSize(EntityEvent.Size)
     */
    public static EntityDimensions determineZephyrSize(Entity entity) {
        if (entity.getType() == AetherEntityTypes.ZEPHYR.get() && entity instanceof Zephyr zephyr) {
            ZephyrColorAttachment attachment = zephyr.getData(GenesisDataAttachments.ZEPHYR_COLOR);
            if (attachment.isTan()) {
                return EntityDimensions.fixed(3.5F, 2.25F);
            }
        }
        return null;
    }

    /**
     * @see com.aetherteam.genesis.event.listeners.EntityListener#finalizeSpawn(MobSpawnEvent.SpawnPlacementCheck)
     */
    public static boolean shouldStopZephyrSpawn(EntityType<?> entityType, ServerLevelAccessor level) {
        return entityType == AetherEntityTypes.ZEPHYR.get() && level.getLevel().isNight();
    }

    /**
     * @see com.aetherteam.genesis.event.listeners.EntityListener#allowDespawn(MobSpawnEvent.AllowDespawn)
     */
    public static boolean shouldZephyrDespawn(LivingEntity zephyr) {
        return zephyr.getType() == AetherEntityTypes.ZEPHYR.get() && !zephyr.level().isClientSide() && zephyr.level().isNight() && zephyr.getRandom().nextInt(100) == 0;
    }
}
