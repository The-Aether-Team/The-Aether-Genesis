package com.aetherteam.aether_genesis.item.accessories;

import com.aetherteam.aether.capability.player.AetherPlayer;
import com.aetherteam.aether.item.accessories.AccessoryItem;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import top.theillusivec4.curios.api.SlotContext;

import javax.annotation.Nullable;
import java.util.List;

public class CrystalBottleItem extends AccessoryItem {
    public CrystalBottleItem(Properties properties) {
        super(properties);
    }

    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand)  {
        ItemStack heldItem = player.getItemInHand(hand);
        AetherPlayer.get(player).ifPresent(aetherPlayer -> {
                float exp = heldItem.getOrCreateTag().getFloat("Experience");
                if (player.isShiftKeyDown()) {
                    if (player.experienceProgress > 0.0F) {
                        heldItem.getOrCreateTag().putFloat("Experience", exp + 0.1F);
                        player.experienceProgress -= 0.1F;
                    } else if (player.experienceLevel > 0) {
                        heldItem.getOrCreateTag().putFloat("Experience", exp + 1.0F);
                        player.experienceLevel--;
                        player.experienceProgress = 1;
                    }
                } else if (exp > 0.0F) {
                    if (player.experienceProgress < 1.0F) {
                        heldItem.getOrCreateTag().putFloat("Experience", exp - 0.1F);
                        player.experienceProgress += 0.1F;
                    } else {
                        heldItem.getOrCreateTag().putFloat("Experience", exp - 1.0F);
                        player.experienceLevel = (int) (player.experienceLevel + 1.0F);
                        player.experienceProgress = 0;
                    }
                }
        });
        return InteractionResultHolder.success(heldItem);
    }

    public boolean canEquipFromUse(SlotContext slotContext, ItemStack stack) {
        return false;
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> components, TooltipFlag flag) {
        components.add(Component.literal("Experience" + (stack.hasTag() ? (" (" + (int)stack.getTag().getFloat("Experience") + " inside)") : "")));
        super.appendHoverText(stack, level, components, flag);
    }
}
