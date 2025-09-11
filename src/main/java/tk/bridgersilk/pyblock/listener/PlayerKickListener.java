package tk.bridgersilk.pyblock.listener;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerKickEvent;

import tk.bridgersilk.pyblock.scripts.ScriptManager;

public class PlayerKickListener implements Listener {
    @EventHandler
    public void onPlayerKick(PlayerKickEvent event) {
        String playerName = event.getPlayer().getName();
        UUID playerUUID = event.getPlayer().getUniqueId();
        String eventWorld = event.getPlayer().getWorld().getName();
        String reason = event.getReason();

        Map<String, Object> context = new HashMap<>();
        context.put("event", event);
        context.put("player", playerName);
        context.put("world", eventWorld);
        context.put("reason", reason);
        context.put("player_uuid", playerUUID);

        ScriptManager.callEventFunction("event_player_kick", context);
    }
}
