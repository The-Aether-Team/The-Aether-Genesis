package com.aetherteam.genesis.client;

import com.aetherteam.aether.AetherConfig;
import com.aetherteam.aether.AetherTags;
import com.aetherteam.aether.client.AetherMenuUtil;
import com.aetherteam.aether.client.WorldDisplayHelper;
import com.aetherteam.aether.client.event.hooks.GuiHooks;
import com.aetherteam.aether.client.sound.MusicSoundInstance;
import com.aetherteam.aether.entity.AetherBossMob;
import com.aetherteam.aether.mixin.mixins.client.accessor.BossHealthOverlayAccessor;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.LerpingBossEvent;
import net.minecraft.client.gui.screens.WinScreen;
import net.minecraft.client.resources.sounds.SimpleSoundInstance;
import net.minecraft.client.resources.sounds.SoundInstance;
import net.minecraft.client.sounds.MusicManager;
import net.minecraft.client.sounds.SoundManager;
import net.minecraft.core.Holder;
import net.minecraft.sounds.Music;
import net.minecraft.sounds.Musics;
import net.minecraft.tags.BiomeTags;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;

import javax.annotation.Nullable;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * [CODE COPY] - {@link com.aetherteam.aether.client.AetherMusicManager}.<br><br>
 * Modified to handle nighttime music.
 */
public class GenesisMusicManager {
    public static final Music AETHER_NIGHT = Musics.createGameMusic(GenesisSoundEvents.MUSIC_AETHER_NIGHT);

    private static final int FADE_LIMIT = 50;
    private static final RandomSource random = RandomSource.create();
    private static final Minecraft minecraft = Minecraft.getInstance();
    private static final MusicManager musicManager = Minecraft.getInstance().getMusicManager();
    @Nullable
    private static SoundInstance currentMusic;
    private static int nextSongDelay = 100;
    private static Integer fade = null;

    public static void tick() {
        Music music = getSituationalMusic();
        Music musicOpposite = getSituationalOppositeDaytimeMusic();

        if (currentMusic instanceof MusicSoundInstance musicSoundInstance) {
            if (musicSoundInstance.isBossMusic()) {
                if (music == null || !isAetherBossMusic(music)) {
                    if (fade == null) {
                        fade = 0;
                    }
                    musicSoundInstance.setVolume((float) Math.exp(-(fade / (FADE_LIMIT / 3.0))));
                    fade++;
                    if (fade >= FADE_LIMIT) {
                        fade = null;
                        minecraft.getSoundManager().stop(currentMusic);
                        currentMusic = null;
                        nextSongDelay = Math.min(Integer.MAX_VALUE, Mth.nextInt(random, AetherConfig.CLIENT.music_backup_min_delay.get(), AetherConfig.CLIENT.music_backup_max_delay.get() / 2)); // End boss music
                    }
                }
            } else {
                if (music != null && isAetherBossMusic(music)) {
                    if (fade == null) {
                        fade = 0;
                    }
                    musicSoundInstance.setVolume((float) Math.exp(-(fade / (FADE_LIMIT / 3.0))));
                    fade++;
                    if (fade >= FADE_LIMIT) {
                        fade = null;
                        minecraft.getSoundManager().stop(currentMusic);
                        currentMusic = null;
                        nextSongDelay = FADE_LIMIT / 3; // Start boss music
                    }
                }
            }
        }

        if (music != null && musicOpposite != null) {
            if (currentMusic != null) {
                if (!music.getEvent().value().getLocation().equals(currentMusic.getLocation()) && !musicOpposite.getEvent().value().getLocation().equals(currentMusic.getLocation()) && music.replaceCurrentMusic()) {
                    minecraft.getSoundManager().stop(currentMusic);
                    nextSongDelay = Mth.nextInt(random, 0, music.getMinDelay() / 2);
                }

                if (!minecraft.getSoundManager().isActive(currentMusic)) {
                    currentMusic = null;
                    nextSongDelay = Math.min(nextSongDelay, Mth.nextInt(random, music.getMinDelay(), music.getMaxDelay()));
                }
            }

            nextSongDelay = Math.min(nextSongDelay, music.getMaxDelay());
            if (currentMusic == null && nextSongDelay-- <= 0) {
                startPlaying(music);
            }
        } else {
            currentMusic = null;
            if (nextSongDelay-- <= 0) {
                nextSongDelay = Math.min(Integer.MAX_VALUE, Mth.nextInt(random, AetherConfig.CLIENT.music_backup_min_delay.get(), AetherConfig.CLIENT.music_backup_max_delay.get()));
            }
        }
    }

    public static void startPlaying(Music music) {
        musicManager.stopPlaying();
        if (isAetherBossMusic(music)) {
            currentMusic = MusicSoundInstance.forBossMusic(music.getEvent().value());
        } else {
            currentMusic = MusicSoundInstance.forMusic(music.getEvent().value());
        }
        if (currentMusic.getSound() != SoundManager.EMPTY_SOUND) {
            minecraft.getSoundManager().play(currentMusic);
        }
        nextSongDelay = Integer.MAX_VALUE;
    }

    public static void stopPlaying() {
        if (currentMusic != null) {
            minecraft.getSoundManager().stop(currentMusic);
            currentMusic = null;
        }
        nextSongDelay += 100;
    }

    @Nullable
    public static SoundInstance getCurrentMusic() {
        return currentMusic;
    }

    public static boolean isAetherBossMusic(Music music) {
        return music.getEvent().is(AetherTags.SoundEvents.BOSS_MUSIC);
    }

    public static boolean isAetherBossMusicActive() {
        return !AetherConfig.CLIENT.disable_aether_boss_music.get() && !getAetherBossFights().isEmpty() && minecraft.gui.getBossOverlay().shouldPlayMusic();
    }

    public static Map<UUID, LerpingBossEvent> getAetherBossFights() {
        return ((BossHealthOverlayAccessor) minecraft.gui.getBossOverlay()).getEvents().entrySet().stream().filter((entry) -> GuiHooks.isAetherBossBar(entry.getKey())).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public static <T extends LivingEntity & AetherBossMob<?>> T getBossFromFight() {
        for (Map.Entry<UUID, LerpingBossEvent> event : getAetherBossFights().entrySet()) {
            UUID eventUUID = event.getKey();
            int entityId = GuiHooks.BOSS_EVENTS.get(eventUUID);
            Entity entity = minecraft.player.level().getEntity(entityId);
            if (entity instanceof LivingEntity && entity instanceof AetherBossMob<?>) {
                return (T) entity;
            }
        }
        return null;
    }

    /**
     * @return Whether the current world preview display is on the Aether menu, as a {@link Boolean}.
     */
    public static boolean isAetherWorldPreviewEnabled() {
        return AetherMenuUtil.isAetherMenu() && isWorldPreviewEnabled() && !AetherConfig.CLIENT.disable_aether_world_preview_menu_music.get();
    }

    /**
     * @return Whether the current world preview display is on a default menu, as a {@link Boolean}.
     */
    public static boolean isVanillaWorldPreviewEnabled() {
        return AetherMenuUtil.isMinecraftMenu() && isWorldPreviewEnabled() && !AetherConfig.CLIENT.disable_vanilla_world_preview_menu_music.get();
    }

    /**
     * @return Whether the world preview display is enabled and active, as a {@link Boolean}.
     */
    public static boolean isWorldPreviewEnabled() {
        return minecraft.player != null && WorldDisplayHelper.isActive();
    }

    /**
     * @return Regular {@link Music}, or nighttime {@link Music}, when it is nighttime.
     */
    public static <T extends LivingEntity & AetherBossMob<?>> Music getSituationalMusic() {
        if (!(minecraft.screen instanceof WinScreen)) {
            if (!isVanillaWorldPreviewEnabled() && !isAetherWorldPreviewEnabled() && minecraft.player != null) {
                Holder<Biome> holder = minecraft.player.level().getBiome(minecraft.player.blockPosition());
                long time = minecraft.player.clientLevel.getLevelData().getDayTime() % 72000L;
                boolean night = time >= 39000 && time < 69000;

                T boss = getBossFromFight();
                if (boss != null && boss.getHealth() > 0) {
                    Music bossMusic = boss.getBossMusic();
                    if (bossMusic != null) {
                        return boss.getBossMusic();
                    }
                } else if (night) {
                    return AETHER_NIGHT;
                } else if (isCreative(holder, minecraft.player)) {
                    return holder.value().getBackgroundMusic().orElse(Musics.GAME);
                }
            }
        }
        return null;
    }

    /**
     * @return Regular {@link Music}, or nighttime {@link Music}, when it is not nighttime.
     */
    public static Music getSituationalOppositeDaytimeMusic() {
        if (!(minecraft.screen instanceof WinScreen)) {
            if (!isVanillaWorldPreviewEnabled() && !isAetherWorldPreviewEnabled() && minecraft.player != null) {
                Holder<Biome> holder = minecraft.player.level().getBiome(minecraft.player.blockPosition());
                long time = minecraft.player.clientLevel.getLevelData().getDayTime() % 72000L;
                boolean night = time >= 39000 && time < 69000;

                if (!night) {
                    return AETHER_NIGHT;
                } else if (isCreative(holder, minecraft.player)) {
                    return holder.value().getBackgroundMusic().orElse(Musics.GAME);
                }
            }
        }
        return null;
    }

    public static boolean isCreative(Holder<Biome> holder, Player player) {
        return player.level().dimension() != Level.END && player.level().dimension() != Level.NETHER && holder.is(AetherTags.Biomes.AETHER_MUSIC)
                && !musicManager.isPlayingMusic(Musics.UNDER_WATER) && (!player.isUnderWater() || !holder.is(BiomeTags.PLAYS_UNDERWATER_MUSIC))
                && player.getAbilities().instabuild && player.getAbilities().mayfly;
    }
}
