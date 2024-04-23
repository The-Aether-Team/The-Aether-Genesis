package com.aetherteam.aether_genesis.event.listeners.abilities;

import com.aetherteam.aether_genesis.Genesis;
import com.aetherteam.aether_genesis.event.hooks.AbilityHooks;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.ToolAction;
import net.neoforged.neoforge.event.level.BlockEvent;

@Mod.EventBusSubscriber(modid = Genesis.MODID)
public class ToolAbilityListener {
    /**
     * @see AbilityHooks.ToolHooks#setupToolActions(BlockState, ToolAction)
     */
    @SubscribeEvent
    public static void setupToolModifications(BlockEvent.BlockToolModificationEvent event) {
        BlockState oldState = event.getState();
        ToolAction toolAction = event.getToolAction();
        BlockState newState = AbilityHooks.ToolHooks.setupToolActions(oldState, toolAction);
        if (newState != oldState && !event.isSimulated() && !event.isCanceled()) {
            event.setFinalState(newState);
        }
    }
}
