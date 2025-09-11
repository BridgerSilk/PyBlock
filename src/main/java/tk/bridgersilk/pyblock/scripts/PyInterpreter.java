package tk.bridgersilk.pyblock.scripts;

import java.io.File;
import java.util.Arrays;
import java.util.UUID;

import org.bukkit.event.Event;
import org.python.core.Py;
import org.python.core.PyObject;
import org.python.util.PythonInterpreter;

import tk.bridgersilk.pyblock.effects.BroadcastEffect;
import tk.bridgersilk.pyblock.effects.CancelEventEffect;
import tk.bridgersilk.pyblock.expressions.entity.expHealth;
import tk.bridgersilk.pyblock.storage.VarStorage;

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

            // inject expressions
            interpreter.set("get_health", makeFunction(uuid -> expHealth.getHealth(uuid)));

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
