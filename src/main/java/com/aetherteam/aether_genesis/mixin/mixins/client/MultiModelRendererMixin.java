package com.aetherteam.aether_genesis.mixin.mixins.client;

import com.aetherteam.aether.AetherConfig;
import com.aetherteam.aether.client.renderer.entity.MultiModelRenderer;
import com.aetherteam.aether.entity.AetherEntityTypes;
import com.aetherteam.aether.entity.monster.Zephyr;
import com.aetherteam.aether_genesis.Genesis;
import com.aetherteam.aether_genesis.capability.zephyr.ZephyrColor;
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
    private static final ResourceLocation ZEPHYR_TAN_TEXTURE = new ResourceLocation(Genesis.MODID, "textures/entity/mobs/zephyr/zephyr_tan.png");

    @Inject(at = @At(value = "HEAD"), method = "getTextureLocation(Lnet/minecraft/world/entity/Entity;)Lnet/minecraft/resources/ResourceLocation;", cancellable = true)
    private void getDefaultTexture(Entity entity, CallbackInfoReturnable<ResourceLocation> cir) {
        if (entity.getType() == AetherEntityTypes.ZEPHYR.get() && entity instanceof Zephyr zephyr && !AetherConfig.CLIENT.legacy_models.get()) {
            ZephyrColor.get(zephyr).ifPresent((zephyrColor) -> {
                if (zephyrColor.isTan()) {
                    cir.setReturnValue(ZEPHYR_TAN_TEXTURE);
                }
            });
        }
    }
}
