package tk.bridgersilk.pyblock.listener;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import tk.bridgersilk.pyblock.scripts.ScriptManager;

public class PlayerCommandPreprocessListener implements Listener {
    @EventHandler
    public void onPlayerCommandPreprocess(PlayerCommandPreprocessEvent event) {
        String playerName = event.getPlayer().getName();
        String eventWorld = event.getPlayer().getWorld().getName();
        String command = event.getMessage();

        Map<String, Object> context = new HashMap<>();
        context.put("event", event);
        context.put("player", playerName);
        context.put("world", eventWorld);
        context.put("command", command);

        ScriptManager.callEventFunction("event_command", context);
    }
}
