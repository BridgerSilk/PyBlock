package tk.bridgersilk.pyblock.scripts;

import java.io.File;
import java.util.Arrays;
import java.util.UUID;

import org.bukkit.event.Event;
import org.python.core.Py;
import org.python.core.PyObject;
import org.python.util.PythonInterpreter;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.Bukkit;
import org.bukkit.Material;

import tk.bridgersilk.pyblock.effects.BroadcastEffect;
import tk.bridgersilk.pyblock.effects.CancelEventEffect;
import tk.bridgersilk.pyblock.effects.GiveItemEffect;
import tk.bridgersilk.pyblock.expressions.entity.expActiveItem;
import tk.bridgersilk.pyblock.expressions.entity.expHealth;
import tk.bridgersilk.pyblock.expressions.global.expLocation;
import tk.bridgersilk.pyblock.expressions.player.expUUID;
import tk.bridgersilk.pyblock.storage.VarStorage;
import tk.bridgersilk.pyblock.utils.ItemBuilder;

public class PyInterpreter {

	private PythonInterpreter interpreter;

	public boolean load(File file) {
        try {
            interpreter = new PythonInterpreter();

            // inject runtime callables
            interpreter.set("broadcast", makeCallable(msg -> BroadcastEffect.broadcast(msg)));
            interpreter.set("cancel_event", makeCallableWithEvent((eventObj) -> {
                if (eventObj instanceof Event event) {
                    CancelEventEffect.cancelEvent(event);
                } else {
                    System.out.println("cancel_event called with non-event: " + eventObj);
                }
            }));
            interpreter.set("give_item", makeCallableWithArgs(args -> {
                Object raw = args[0].__tojava__(Object.class);
                UUID playeruuid = (raw instanceof UUID uuid) ? uuid : UUID.fromString(raw.toString());

                ItemStack item = (ItemStack) args[1].__tojava__(ItemStack.class);

                GiveItemEffect.giveItem(playeruuid, item);
            }));

            // inject expressions
            interpreter.set("get_health", makeFunction(uuid -> expHealth.getHealth(uuid)));
            interpreter.set("get_location", makeFunction(uuid -> expLocation.getLocation(uuid)));
            interpreter.set("get_uuid", makeStringFunction(name -> expUUID.getUUID(name)));
            interpreter.set("get_active_item", makeFunction(uuid -> expActiveItem.getActiveItem(uuid)));

            // inject utils
            interpreter.set("Enchantment", Py.java2py(Enchantment.class));
            interpreter.set("ItemFlag", Py.java2py(ItemFlag.class));
            interpreter.set("Material", Py.java2py(Material.class));
            interpreter.set("new_item", new PyObject() {
                @Override
                public PyObject __call__(PyObject materialObj) {
                    try {
                        String matName = materialObj.toString().toUpperCase();
                        Material material = Material.matchMaterial(matName);
                        if (material == null) {
                            System.out.println("Invalid material: " + matName);
                            return Py.None;
                        }
                        ItemBuilder builder = new ItemBuilder(material);
                        return Py.java2py(builder);
                    } catch (Exception e) {
                        e.printStackTrace();
                        return Py.None;
                    }
                }
            });

            // inject storage functions
            interpreter.set("save_var", new PyObject() {
                @Override
                public PyObject __call__(PyObject name, PyObject value) {
                    String varName = name.toString();
                    Object javaValue = value.__tojava__(Object.class);
                    VarStorage.saveVar(varName, javaValue);
                    return Py.None;
                }
            });
            interpreter.set("load_var", new PyObject() {
                @Override
                public PyObject __call__(PyObject name) {
                    String varName = name.toString();
                    Object loaded = VarStorage.loadVar(varName);
                    return Py.java2py(loaded);
                }
            });

            // preproccess script: ignore client-side pyblock imports (only needed for pylance)
            StringBuilder cleanedSource = new StringBuilder();
            try (java.io.BufferedReader reader = new java.io.BufferedReader(new java.io.FileReader(file))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    if (line.trim().startsWith("import pyblock") || line.trim().startsWith("from pyblock")) {
                        continue;
                    }
                    cleanedSource.append(line).append("\n");
                }
            }

            interpreter.exec(cleanedSource.toString());

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

	public void callFunction(String functionName, Object... args) {
		try {
			PyObject func = interpreter.get(functionName);
			if (func != null && func.isCallable()) {
				try {
					func.__call__();
				} catch (Exception ignored) {
					func.__call__(Arrays.stream(args).map(Py::java2py).toArray(PyObject[]::new));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

    // no arg callable helper
	private PyObject makeCallable(Runnable runnable) {
		return new PyObject() {
			@Override
			public PyObject __call__() {
				runnable.run();
				return Py.None;
			}
		};
	}

    // any arg callable helper
    private PyObject makeCallableWithArgs(java.util.function.Consumer<PyObject[]> consumer) {
        return new PyObject() {
            @Override
            public PyObject __call__(PyObject[] args, String[] keywords) {
                consumer.accept(args);
                return Py.None;
            }
        };
    }

	// single string arg callable helper
	private PyObject makeCallable(java.util.function.Consumer<String> consumer) {
		return new PyObject() {
			@Override
			public PyObject __call__(PyObject arg) {
				consumer.accept(arg.toString());
				return Py.None;
			}
		};
	}

    // function helper for returning data (primarily expressions)
    private PyObject makeFunction(java.util.function.Function<UUID, ?> function) {
        return new PyObject() {
            @Override
            public PyObject __call__(PyObject arg) {
                try {
                    UUID uuid = UUID.fromString(arg.toString());
                    Object result = function.apply(uuid);
                    return Py.java2py(result);
                } catch (IllegalArgumentException e) {
                    System.out.println("Invalid UUID passed to function: " + arg);
                    return Py.None;
                }
            }
        };
    }

    private PyObject makeStringFunction(java.util.function.Function<String, ?> function) {
        return new PyObject() {
            @Override
            public PyObject __call__(PyObject arg) {
                try {
                    String str = arg.toString();
                    Object result = function.apply(str);
                    return Py.java2py(result);
                } catch (Exception e) {
                    System.out.println("Error while calling function with argument: " + arg);
                    e.printStackTrace();
                    return Py.None;
                }
            }
        };
    }

    private PyObject makeCallableWithEvent(java.util.function.Consumer<Object> consumer) {
        return new PyObject() {
            @Override
            public PyObject __call__(PyObject arg) {
                consumer.accept(arg.__tojava__(Object.class));
                return Py.None;
            }
        };
    }
}
