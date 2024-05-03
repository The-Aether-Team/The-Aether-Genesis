package com.aetherteam.genesis.item.accessories.companion;

import com.aetherteam.genesis.entity.GenesisEntityTypes;
import com.aetherteam.genesis.entity.companion.NexSpirit;

public class DeathSealItem extends CompanionItem<NexSpirit> { //todo tooltip
    public DeathSealItem(Properties properties) {
        super(GenesisEntityTypes.NEX_SPIRIT, properties);
    }

//    @Override
//    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> components, TooltipFlag flag) {
//        Player player = Minecraft.getInstance().player;
//        if (player != null) {
//            GenesisPlayerAttachment attachment = player.getData(GenesisDataAttachments.GENESIS_PLAYER);
//            for (Entity entity : attachment.getCompanions()) { //it doesnt know this on the client.
//                if (entity instanceof NexSpirit nexSpirit) {
//                    ItemStack summonItem = nexSpirit.getItem();
//                    if (ItemStack.isSameItemSameTags(stack, summonItem)) {
//                        components.add(Component.translatable("aether_genesis.death_seal.desc", 100 - nexSpirit.getCooldown()));
//                    }
//                }
//            }
//        }
//        super.appendHoverText(stack, level, components, flag);
//    }
}
