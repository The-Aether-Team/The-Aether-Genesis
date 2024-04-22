package com.aetherteam.aether_genesis.event.hooks;

import com.aetherteam.aether.item.EquipmentUtil;
import com.aetherteam.aether_genesis.attachment.GenesisDataAttachments;
import com.aetherteam.aether_genesis.attachment.GenesisPlayerAttachment;
import com.aetherteam.aether_genesis.block.GenesisBlocks;
import com.aetherteam.aether_genesis.entity.GenesisEntityTypes;
import com.aetherteam.aether_genesis.entity.companion.NexSpirit;
import com.aetherteam.aether_genesis.entity.projectile.DaggerfrostSnowball;
import com.aetherteam.aether_genesis.entity.projectile.PhoenixDart;
import com.aetherteam.aether_genesis.item.GenesisItems;
import com.google.common.collect.ImmutableMap;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Snowball;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.common.ToolAction;
import net.neoforged.neoforge.common.ToolActions;
import net.neoforged.neoforge.event.entity.EntityJoinLevelEvent;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;
import net.neoforged.neoforge.event.entity.living.LivingHurtEvent;
import net.neoforged.neoforge.event.level.BlockEvent;
import top.theillusivec4.curios.api.SlotResult;

import java.util.Map;

public class AbilityHooks {
    public static class AccessoryHooks {
        /**
         * @see com.aetherteam.aether_genesis.event.listeners.abilities.AccessoryAbilityListener#entityJoinLevel(EntityJoinLevelEvent)
         */
        public static boolean convertSnowball(Entity entity, Level level) {
            if (entity.getType() == EntityType.SNOWBALL) {
                Snowball snowball = (Snowball) entity;
                if (snowball.getOwner() instanceof LivingEntity livingEntity) {
                    if (EquipmentUtil.hasCurio(livingEntity, GenesisItems.DAGGERFROST_LOCKET.get())) {
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
         * @see com.aetherteam.aether_genesis.event.listeners.abilities.AccessoryAbilityListener#entityDie(LivingDeathEvent)
         */
        public static boolean saveFromDeath(LivingEntity entity) {
            if (entity instanceof Player player) {
                GenesisPlayerAttachment attachment = player.getData(GenesisDataAttachments.GENESIS_PLAYER);
                for (Entity companion : attachment.getCompanions()) {
                    if (companion instanceof NexSpirit nexSpirit) {
                        if (!nexSpirit.isBroken()) {
                            player.setHealth(player.getMaxHealth());
                            nexSpirit.setCooldown(100); //todo balance
                            return true;
                        }
                    }
                }
            }
            return false;
        }
    }

    public static class ToolHooks {
        public static final Map<Block, Block> STRIPPABLES = (new ImmutableMap.Builder<Block, Block>())
                .put(GenesisBlocks.SKYROOT_LOG_WALL.get(), GenesisBlocks.STRIPPED_SKYROOT_LOG_WALL.get())
                .put(GenesisBlocks.SKYROOT_WOOD_WALL.get(), GenesisBlocks.STRIPPED_SKYROOT_WOOD_WALL.get())
                .build();

        /**
         * @see com.aetherteam.aether_genesis.event.listeners.abilities.ToolAbilityListener#setupToolModifications(BlockEvent.BlockToolModificationEvent)
         */
        public static BlockState setupToolActions(BlockState old, ToolAction action) {
            Block oldBlock = old.getBlock();
            if (action == ToolActions.AXE_STRIP) {
                if (STRIPPABLES.containsKey(oldBlock)) {
                    return STRIPPABLES.get(oldBlock).withPropertiesOf(old);
                }
            }
            return old;
        }
    }

    public static class WeaponHooks {
        /**
         * @see com.aetherteam.aether_genesis.event.listeners.WeaponAbilityListener#onDartHurt(LivingHurtEvent)
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
