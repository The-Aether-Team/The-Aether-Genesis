package com.aetherteam.aether_genesis.mixin.mixins.common;


import com.aetherteam.aether.entity.monster.dungeon.boss.slider.Slider;
import com.aetherteam.aether_genesis.GenesisConfig;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Blocks;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(Slider.class)
public class SliderMixin {

    @Redirect(method = "hurt", at = @At(value = "INVOKE", target = "Lcom/aetherteam/aether/entity/monster/dungeon/boss/slider/Slider;displayInvalidToolMessage(Lnet/minecraft/world/entity/player/Player;)V"))
    public void displayInvalidToolMessage(Slider instance, Player player)
    {
        if (GenesisConfig.COMMON.improved_slider_message.get()) {
            ItemStack stack = player.getMainHandItem();
            if (stack.getItem() != Blocks.AIR.asItem()) {
                player.sendSystemMessage(Component.translatable("gui.aether_genesis.slider.message.attack.invalid_item", stack.getHoverName()));
            } else {
                player.sendSystemMessage(Component.translatable("gui.aether_genesis.slider.message.attack.invalid_fist"));
            }
        } else { instance.displayInvalidToolMessage(player); }
    }




}
