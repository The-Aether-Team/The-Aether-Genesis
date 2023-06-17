package com.aetherteam.aether_genesis.event.listeners.abilities;

import com.aetherteam.aether.util.EquipmentUtil;
import com.aetherteam.aether_genesis.Genesis;
import com.aetherteam.aether_genesis.entity.DaggerfrostSnowball;
import com.aetherteam.aether_genesis.entity.GenesisEntityTypes;
import com.aetherteam.aether_genesis.item.GenesisItems;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.Snowball;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Genesis.MODID)
public class AccessoryAbilityListener {
    @SubscribeEvent
    public static void entityJoinLevel(EntityJoinLevelEvent event) {
        Entity entity = event.getEntity();
        Level level = event.getLevel();
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
                    }
                    event.setCanceled(true);
                }
            }
        }
    }
}
