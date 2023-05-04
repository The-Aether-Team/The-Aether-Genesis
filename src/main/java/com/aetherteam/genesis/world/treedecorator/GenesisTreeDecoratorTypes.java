package com.aetherteam.genesis.world.treedecorator;

import com.aetherteam.genesis.Genesis;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecoratorType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class GenesisTreeDecoratorTypes {
    public static final DeferredRegister<TreeDecoratorType<?>> TREE_DECORATORS = DeferredRegister.create(ForgeRegistries.TREE_DECORATOR_TYPES, Genesis.MODID);

    public static final RegistryObject<TreeDecoratorType<TrunkDecorator>> TRUNK_DECORATOR = TREE_DECORATORS.register("trunk_decorator", () -> new TreeDecoratorType<>(TrunkDecorator.CODEC));
}
