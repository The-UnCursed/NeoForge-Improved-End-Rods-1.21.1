package net.uncursed.improved_end_rods.client;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.gui.ConfigurationScreen;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;
import net.uncursed.improved_end_rods.ImprovedEndRods;

// This class will not load on dedicated servers. Accessing client side code from here is safe.
@Mod(value = ImprovedEndRods.MOD_ID, dist = Dist.CLIENT)
public class ClientSetup {
    public ClientSetup(ModContainer container) {
        // Allows NeoForge to create a config screen for this mod's configs.
        // The config screen is accessed by going to the Mods screen > clicking on your mod > clicking on config.
        // Do not forget to add translations for your config options to the en_us.json file.
        container.registerExtensionPoint(IConfigScreenFactory.class, ConfigurationScreen::new);
    }
}