package tk.bridgersilk.pyblock.effects;

import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;

public class CancelEventEffect {
    public static void cancelEvent(Event event) {
        if (event instanceof Cancellable cancellable) {
            cancellable.setCancelled(true);
        }
    }
}

