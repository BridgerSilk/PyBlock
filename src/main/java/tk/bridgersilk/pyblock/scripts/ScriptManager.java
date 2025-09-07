package tk.bridgersilk.pyblock.scripts;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.bukkit.event.Event;

import tk.bridgersilk.pyblock.PyBlock;

public class ScriptManager {

    private static final Map<String, PyInterpreter> loadedScripts = new HashMap<>();

    public static void loadAllScripts() {
        loadedScripts.clear();
        File scriptsFolder = new File(PyBlock.getInstance().getDataFolder(), "scripts");
        File[] files = scriptsFolder.listFiles((dir, name) -> name.endsWith(".py"));

        if (files == null) return;

        for (File file : files) {
            loadScript(file);
        }
    }

    public static void loadScript(File file) {
        PyInterpreter interpreter = new PyInterpreter();
        if (interpreter.load(file)) {
            loadedScripts.put(file.getName(), interpreter);
        }
    }

    public static boolean reloadScript(String fileName) {
        File file = new File(PyBlock.getInstance().getDataFolder(), "scripts/" + fileName);
        if (!file.exists()) return false;

        loadedScripts.remove(fileName);
        loadScript(file);
        return true;
    }

    public static void callEventFunction(String functionName, Object... args) {
        for (PyInterpreter interpreter : loadedScripts.values()) {
            try {
                if (args.length > 0 && args[0] instanceof Map<?, ?> map) {
                    Object rawEvent = map.get("event");
                    if (rawEvent instanceof Event event) {
                        EventContext.setCurrentEvent(event);
                    }
                }

                interpreter.callFunction(functionName, args);

            } finally {
                EventContext.clear();
            }
        }
    }
}
