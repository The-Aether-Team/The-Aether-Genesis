package com.aetherteam.aether_genesis.mixin.mixins.common;


import com.aetherteam.aether.entity.monster.dungeon.boss.Slider;
import com.aetherteam.aether_genesis.GenesisConfig;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.HoverEvent;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Blocks;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(Slider.class)
public class SliderMixin {
    /**
     * Changes the display message when trying to damage the Slider with an incorrect item.
     */
    @WrapOperation(method = "sendInvalidToolMessage(Lnet/minecraft/world/entity/LivingEntity;)Ljava/util/Optional;", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/player/Player;sendSystemMessage(Lnet/minecraft/network/chat/Component;)V"))
    public void sendInvalidToolMessage(Player player, Component component, Operation<Void> original) {
        if (GenesisConfig.COMMON.improved_slider_message.get()) {
            ItemStack stack = player.getMainHandItem();
            if (stack.getItem() != Blocks.AIR.asItem()) {
                MutableComponent mutablecomponent = Component.empty().append(stack.getHoverName());
                mutablecomponent.withStyle(stack.getRarity().getStyleModifier()).withStyle((style) -> style.withHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_ITEM, new HoverEvent.ItemStackInfo(stack))));
                player.sendSystemMessage(Component.translatable("gui.aether_genesis.slider.message.attack.invalid_item", mutablecomponent));
            } else {
                player.sendSystemMessage(Component.translatable("gui.aether_genesis.slider.message.attack.invalid_fist"));
            }
        } else {
            original.call(player, component);
        }
    }
}
