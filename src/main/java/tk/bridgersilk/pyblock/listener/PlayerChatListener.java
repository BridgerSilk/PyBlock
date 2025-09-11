package tk.bridgersilk.pyblock.listener;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import tk.bridgersilk.pyblock.scripts.ScriptManager;

public class PlayerChatListener implements Listener {
    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event) {
        String playerName = event.getPlayer().getName();
        UUID playerUUID = event.getPlayer().getUniqueId();
        String eventWorld = event.getPlayer().getWorld().getName();
        String message = event.getMessage();
        Set<String> recipients = new HashSet<>();
        event.getRecipients().forEach(player -> {
            recipients.add(player.getName());
        });

        Map<String, Object> context = new HashMap<>();
        context.put("event", event);
        context.put("player", playerName);
        context.put("world", eventWorld);
        context.put("message", message);
        context.put("recipients", recipients);
        context.put("player_uuid", playerUUID);

        ScriptManager.callEventFunction("event_chat", context);
    }
}