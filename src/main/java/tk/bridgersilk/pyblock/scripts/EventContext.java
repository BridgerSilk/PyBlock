package tk.bridgersilk.pyblock.scripts;

import org.bukkit.event.Event;

public class EventContext {
    private static final ThreadLocal<Event> currentEvent = new ThreadLocal<>();

    public static void setCurrentEvent(Event event) {
        currentEvent.set(event);
    }

    public static Event getCurrentEvent() {
        return currentEvent.get();
    }

    public static void clear() {
        currentEvent.remove();
    }
}

