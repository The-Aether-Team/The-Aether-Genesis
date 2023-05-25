package com.aetherteam.aether_genesis.capability.player;

import com.aetherteam.aether_genesis.capability.GenesisCapabilities;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;

import javax.annotation.Nonnull;

public class GenesisPlayerProvider implements ICapabilitySerializable<CompoundTag> {
	private final GenesisPlayer genesisPlayer;

	public GenesisPlayerProvider(GenesisPlayer genesisPlayer) {
		this.genesisPlayer = genesisPlayer;
	}

	@Override
	public CompoundTag serializeNBT() {
		return this.genesisPlayer.serializeNBT();
	}

	@Override
	public void deserializeNBT(CompoundTag tag) {
		this.genesisPlayer.deserializeNBT(tag);
	}

	@SuppressWarnings("unchecked")
	@Nonnull
	@Override
	public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, Direction side) {
		if (cap == GenesisCapabilities.GENESIS_PLAYER_CAPABILITY) {
			return LazyOptional.of(() -> (T) this.genesisPlayer);
		}
		return LazyOptional.empty();
	}
}