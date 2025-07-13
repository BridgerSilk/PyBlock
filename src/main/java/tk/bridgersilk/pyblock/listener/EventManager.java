package tk.bridgersilk.pyblock.listener;

import org.bukkit.plugin.PluginManager;

import tk.bridgersilk.pyblock.PyBlock;

public class EventManager {

    public static void registerEvents(PyBlock plugin) {
        PluginManager pm = plugin.getServer().getPluginManager();
        pm.registerEvents(new PlayerJoinListener(), plugin);
        pm.registerEvents(new BlockBreakListener(), plugin);
        // register more listeners here
    }
}
