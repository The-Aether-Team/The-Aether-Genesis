package com.aetherteam.genesis.mixin.mixins.client;

import com.aetherteam.aether.AetherConfig;
import com.aetherteam.aether.client.renderer.entity.MultiModelRenderer;
import com.aetherteam.aether.entity.AetherEntityTypes;
import com.aetherteam.aether.entity.monster.Zephyr;
import com.aetherteam.genesis.AetherGenesis;
import com.aetherteam.genesis.attachment.GenesisDataAttachments;
import com.aetherteam.genesis.attachment.ZephyrColorAttachment;
import net.minecraft.client.model.EntityModel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.Mob;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(MultiModelRenderer.class)
public class MultiModelRendererMixin<T extends Mob, M extends EntityModel<T>, N extends M, O extends M> {
    @Unique
    private static final ResourceLocation ZEPHYR_TAN_TEXTURE = new ResourceLocation(AetherGenesis.MODID, "textures/entity/mobs/zephyr/zephyr_tan.png");

    /**
     * Sets the Zephyr texture to be tan according to the {@link ZephyrColorAttachment}.
     */
    @Inject(at = @At(value = "HEAD"), method = "getTextureLocation(Lnet/minecraft/world/entity/Entity;)Lnet/minecraft/resources/ResourceLocation;", cancellable = true)
    private void getTextureLocation(Entity entity, CallbackInfoReturnable<ResourceLocation> cir) {
        if (entity.getType() == AetherEntityTypes.ZEPHYR.get() && entity instanceof Zephyr zephyr && !AetherConfig.CLIENT.legacy_models.get()) {
            ZephyrColorAttachment attachment = zephyr.getData(GenesisDataAttachments.ZEPHYR_COLOR);
            if (attachment.isTan()) {
                cir.setReturnValue(ZEPHYR_TAN_TEXTURE);
            }
        }
    }
}
