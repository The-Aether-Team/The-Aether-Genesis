package com.aetherteam.aether_genesis.data.generators;

import com.aetherteam.aether_genesis.Genesis;
import com.aetherteam.aether_genesis.client.GenesisSoundEvents;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.common.data.SoundDefinitionsProvider;

public class GenesisSoundData extends SoundDefinitionsProvider {
    public GenesisSoundData(PackOutput output, ExistingFileHelper helper) {
        super(output,   Genesis.MODID, helper);
    }

    @Override
    public void registerSounds() {
        this.add(GenesisSoundEvents.MUSIC_AETHER_NIGHT,
                definition().with(
                        sound("aether_genesis:music/aether_night1").stream(),
                        sound("aether_genesis:music/aether_night2").stream()
                )
        );
        this.add(GenesisSoundEvents.BLUE_AERCLOUD_BOUNCE,
                definition().with(sound("aether_genesis:block/aercloud/blue_aercloud_bounce"))
                        .subtitle("subtitles.aether_genesis.block.aercloud.blue_aercloud_bounce")
        );
    }
}