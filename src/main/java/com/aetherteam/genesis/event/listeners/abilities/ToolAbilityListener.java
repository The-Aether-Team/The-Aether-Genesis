package com.aetherteam.genesis.event.listeners.abilities;

import com.aetherteam.genesis.Genesis;
import com.aetherteam.genesis.event.hooks.AbilityHooks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.ToolAction;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Genesis.MODID)
public class ToolAbilityListener {
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
