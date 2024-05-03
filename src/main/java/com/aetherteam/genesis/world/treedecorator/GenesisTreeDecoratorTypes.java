package com.aetherteam.genesis.world.treedecorator;

import com.aetherteam.genesis.Genesis;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecoratorType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class GenesisTreeDecoratorTypes {
    public static final DeferredRegister<TreeDecoratorType<?>> TREE_DECORATORS = DeferredRegister.create(BuiltInRegistries.TREE_DECORATOR_TYPE, Genesis.MODID);

    public static final DeferredHolder<TreeDecoratorType<?>, TreeDecoratorType<TrunkDecorator>> TRUNK_DECORATOR = TREE_DECORATORS.register("trunk_decorator", () -> new TreeDecoratorType<>(TrunkDecorator.CODEC));
}
