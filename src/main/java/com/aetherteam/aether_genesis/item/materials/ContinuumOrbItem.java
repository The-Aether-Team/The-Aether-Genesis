package com.aetherteam.aether_genesis.item.materials;

import com.aetherteam.aether.item.miscellaneous.ConsumableItem;
import com.aetherteam.aether_genesis.block.advancement.ContinuumOrbLootTrigger;
import com.aetherteam.aether_genesis.loot.GenesisLoot;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;

import java.util.ArrayList;
import java.util.List;

public class ContinuumOrbItem extends Item implements ConsumableItem {
    public ContinuumOrbItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack heldStack = player.getItemInHand(hand);
        if (!level.isClientSide()) {
            this.consume(this, heldStack, player);
            List<ItemStack> lootItems = this.createLoot(player);
            if (player instanceof ServerPlayer serverPlayer) {
                ContinuumOrbLootTrigger.INSTANCE.trigger(serverPlayer, lootItems);
            }
            return InteractionResultHolder.consume(heldStack);
        } else {
            return InteractionResultHolder.success(heldStack);
        }
    }

    protected List<ItemStack> createLoot(Player player) {
        List<ItemStack> lootItems = new ArrayList<>();
        LootContext.Builder builder = new LootContext.Builder((ServerLevel) player.getLevel())
                .withParameter(LootContextParams.ORIGIN, player.position())
                .withParameter(LootContextParams.THIS_ENTITY, player);
        LootTable lootTable = player.getLevel().getServer().getLootTables().get(GenesisLoot.CONTINUUM_ORB);
        List<ItemStack> list = lootTable.getRandomItems(builder.create(LootContextParamSets.SELECTOR));
        for (ItemStack itemStack : list) {
            if (!player.addItem(itemStack)) {
                player.drop(itemStack, false);
            }
            lootItems.add(itemStack);
        }
        return lootItems;
    }
}
