package com.aetherteam.aether_genesis.mixin.mixins.common.accessor;

import net.minecraft.core.RegistryAccess;
import net.minecraft.core.RegistrySetBuilder;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.gen.Invoker;

import java.util.List;

@Mixin(RegistrySetBuilder.class)
public interface RegistrySetBuilderAccessor {
    @Accessor("entries")
    List<RegistrySetBuilder.RegistryStub<?>> aether_genesis$getEntries();

    @Invoker
    RegistrySetBuilder.BuildState callCreateState(RegistryAccess registryAccess);
}
