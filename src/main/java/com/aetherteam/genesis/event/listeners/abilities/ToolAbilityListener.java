package com.aetherteam.genesis.event.listeners.abilities;

import com.aetherteam.genesis.event.hooks.AbilityHooks;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.common.ItemAbility;
import net.neoforged.neoforge.event.level.BlockEvent;

public class ToolAbilityListener {

    public static void listen(IEventBus bus) {
        bus.addListener(ToolAbilityListener::setupToolModifications);
    }

    /**
     * @see AbilityHooks.ToolHooks#setupToolActions(BlockState, ItemAbility)
     */
    @SubscribeEvent
    public static void setupToolModifications(BlockEvent.BlockToolModificationEvent event) {
        BlockState oldState = event.getState();
        ItemAbility toolAction = event.getItemAbility();
        BlockState newState = AbilityHooks.ToolHooks.setupToolActions(oldState, toolAction);
        if (newState != oldState && !event.isSimulated() && !event.isCanceled()) {
            event.setFinalState(newState);
        }
    }
}
