package tk.bridgersilk.pyblock.config;

import java.io.File;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.Plugin;

public class ConfigManager {

    public static void init(Plugin plugin) {
        File scriptsFolder = new File(plugin.getDataFolder(), "scripts");
        if (!scriptsFolder.exists()) scriptsFolder.mkdirs();

        File configFile = new File(plugin.getDataFolder(), "config.yml");
        if (!configFile.exists()) {
            plugin.saveDefaultConfig();
        }

        plugin.reloadConfig();
    }

    public static FileConfiguration getConfig(Plugin plugin) {
        return plugin.getConfig();
    }
}
