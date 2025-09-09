package tk.bridgersilk.pyblock.listener;

import org.bukkit.plugin.PluginManager;

import tk.bridgersilk.pyblock.PyBlock;

public class EventManager {

    public static void registerEvents(PyBlock plugin) {
        PluginManager pm = plugin.getServer().getPluginManager();
        pm.registerEvents(new PlayerJoinListener(), plugin);
        pm.registerEvents(new BlockBreakListener(), plugin);
        pm.registerEvents(new BlockPlaceListener(), plugin);
        pm.registerEvents(new PlayerQuitListener(), plugin);
        pm.registerEvents(new PlayerChatListener(), plugin);
        pm.registerEvents(new PlayerCommandPreprocessListener(), plugin);
        pm.registerEvents(new PlayerMoveListener(), plugin);
        pm.registerEvents(new PlayerKickListener(), plugin);
    }
}
