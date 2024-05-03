package com.aetherteam.genesis.item.materials;

import com.aetherteam.aether.item.miscellaneous.ConsumableItem;
import com.aetherteam.genesis.advancement.GenesisAdvancementTriggers;
import com.aetherteam.genesis.loot.GenesisLoot;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;

import java.util.ArrayList;
import java.util.List;

public class ContinuumOrbItem extends Item implements ConsumableItem {
    public ContinuumOrbItem(Properties properties) {
        super(properties);
    }

    /**
     * Creates continuum loot with {@link ContinuumOrbItem#createLoot(Player)}.
     *
     * @param level  The {@link Level} of the user.
     * @param player The {@link Player} using this item.
     * @param hand   The {@link InteractionHand} in which the item is being used.
     * @return The {@link InteractionResultHolder}.
     */
    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack heldStack = player.getItemInHand(hand);
        if (!level.isClientSide()) {
            this.consume(this, heldStack, player);
            List<ItemStack> lootItems = this.createLoot(player);
            if (player instanceof ServerPlayer serverPlayer) {
                GenesisAdvancementTriggers.CONTINUUM_ORB.get().trigger(serverPlayer, lootItems);
            }
            return InteractionResultHolder.consume(heldStack);
        } else {
            return InteractionResultHolder.success(heldStack);
        }
    }

    /**
     * Creates loot from the {@link GenesisLoot#CONTINUUM_ORB} table and adds it to the player's inventory.
     * @param player The {@link Player} source of the loot.
     * @return A {@link List} of {@link ItemStack} loot.
     */
    protected List<ItemStack> createLoot(Player player) {
        List<ItemStack> lootItems = new ArrayList<>();
        LootParams parameters = new LootParams.Builder((ServerLevel) player.level()).withParameter(LootContextParams.ORIGIN, player.position()).withParameter(LootContextParams.THIS_ENTITY, player).create(LootContextParamSets.SELECTOR);
        LootTable lootTable = ((ServerLevel) player.level()).getServer().getLootData().getLootTable(GenesisLoot.CONTINUUM_ORB);
        List<ItemStack> list = lootTable.getRandomItems(parameters);
        for (ItemStack itemStack : list) {
            if (!player.addItem(itemStack)) {
                player.drop(itemStack, false);
            }
            lootItems.add(itemStack);
        }
        return lootItems;
    }
}
