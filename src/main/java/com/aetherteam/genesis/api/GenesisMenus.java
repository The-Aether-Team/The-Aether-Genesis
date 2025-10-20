package com.aetherteam.genesis.api;

import com.aetherteam.aether.client.gui.screen.menu.AetherTitleScreen;
import com.aetherteam.cumulus.api.CumulusEntrypoint;
import com.aetherteam.cumulus.api.Menu;
import com.aetherteam.cumulus.api.MenuInitializer;
import com.aetherteam.cumulus.api.MenuRegisterCallback;
import com.aetherteam.genesis.AetherGenesis;
import com.aetherteam.genesis.client.gui.screen.menu.GenesisTitleScreen;
import net.minecraft.client.renderer.CubeMap;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

@CumulusEntrypoint
public class GenesisMenus implements MenuInitializer {

    private static final ResourceLocation GENESIS_ICON = ResourceLocation.fromNamespaceAndPath(AetherGenesis.MODID,"textures/gui/menu_api/menu_icon_aether_genesis.png");

    private static final Component GENESIS_NAME = Component.translatable("aether_genesis.menu_title.aether_genesis");


    public static final Menu GENESIS = new Menu(GENESIS_ICON, GENESIS_NAME, new GenesisTitleScreen(), new Menu.Properties().music(AetherTitleScreen.MENU).panorama(new CubeMap(ResourceLocation.fromNamespaceAndPath(AetherGenesis.MODID, "textures/gui/title/panorama/panorama"))));

    public void registerMenus(MenuRegisterCallback menuRegisterCallback) {
        menuRegisterCallback.registerMenu(ResourceLocation.fromNamespaceAndPath(AetherGenesis.MODID, "aether_genesis"), GENESIS);
    }
}
