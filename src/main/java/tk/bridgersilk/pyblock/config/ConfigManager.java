package tk.bridgersilk.pyblock.config;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.Plugin;

public class ConfigManager {

    public static void init(Plugin plugin) {
        File scriptsFolder = new File(plugin.getDataFolder(), "scripts");
        if (!scriptsFolder.exists()) scriptsFolder.mkdirs();

        // ensure pyblock/ exists inside scripts/
        File pyblockStubFolder = new File(scriptsFolder, "pyblock");
        if (!pyblockStubFolder.exists()) pyblockStubFolder.mkdirs();

        // copy __init__.pyi from resources if not already present
        File stubFile = new File(pyblockStubFolder, "__init__.pyi");
        if (!stubFile.exists()) {
            try (InputStream in = plugin.getResource("__init__.pyi")) {
                if (in != null) {
                    Files.copy(in, stubFile.toPath());
                    plugin.getLogger().info("Created PyBlock stub file: " + stubFile.getPath());
                } else {
                    plugin.getLogger().warning("Stub file __init__.pyi not found in resources!");
                }
            } catch (IOException e) {
                plugin.getLogger().severe("Failed to copy PyBlock stub file: " + e.getMessage());
            }
        }

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
