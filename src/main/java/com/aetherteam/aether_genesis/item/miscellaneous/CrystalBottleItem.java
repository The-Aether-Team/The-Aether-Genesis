package com.aetherteam.aether_genesis.item.miscellaneous;

import com.aetherteam.aether.item.accessories.AccessoryItem;
import net.minecraft.Util;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import top.theillusivec4.curios.api.SlotContext;

import javax.annotation.Nullable;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.List;
import java.util.Locale;

import static com.aetherteam.aether.item.AetherItems.AETHER_LOOT;

public class CrystalBottleItem extends AccessoryItem {
    private static final DecimalFormat EXPERIENCE_FORMAT = Util.make(new DecimalFormat("#.#"), (format) -> format.setDecimalFormatSymbols(DecimalFormatSymbols.getInstance(Locale.ROOT)));

    public CrystalBottleItem() {
        super(new Item.Properties().stacksTo(1).rarity(AETHER_LOOT));
    }

    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand)  {
        ItemStack heldItem = player.getItemInHand(hand);
        float exp = heldItem.getOrCreateTag().getFloat("Experience");
        if (player.isShiftKeyDown()) {
            if (player.experienceProgress > 0.0F) {
                heldItem.getOrCreateTag().putFloat("Experience", exp + 0.1F);
                player.experienceProgress -= 0.1F;
                return InteractionResultHolder.success(heldItem);
            } else if (player.experienceLevel > 0) {
                heldItem.getOrCreateTag().putFloat("Experience", exp + 1.0F);
                player.experienceLevel--;
                player.experienceProgress = 1;
                return InteractionResultHolder.success(heldItem);
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
            return InteractionResultHolder.success(heldItem);
        }

        return InteractionResultHolder.pass(heldItem);
    }

    public boolean canEquipFromUse(SlotContext slotContext, ItemStack stack) {
        return false;
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> components, TooltipFlag flag) {
        if (stack.getTag() != null) {
            components.add(Component.literal("Experience" + (stack.hasTag() ? (" (" + EXPERIENCE_FORMAT.format(stack.getTag().getFloat("Experience")) + " inside)") : ""))); //todo translatable
        }
        super.appendHoverText(stack, level, components, flag);
    }
}
