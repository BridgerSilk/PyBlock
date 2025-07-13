package tk.bridgersilk.pyblock.scripts;

import java.io.File;
import java.util.Arrays;

import org.bukkit.event.Event;
import org.python.core.Py;
import org.python.core.PyObject;
import org.python.util.PythonInterpreter;

import tk.bridgersilk.pyblock.effects.BroadcastEffect;
import tk.bridgersilk.pyblock.effects.CancelEventEffect;

public class PyInterpreter {

	private PythonInterpreter interpreter;

	public boolean load(File file) {
		try {
			interpreter = new PythonInterpreter();

			interpreter.set("broadcast", makeCallable(msg -> BroadcastEffect.broadcast(msg)));
            interpreter.set("cancelEvent", makeCallableWithEvent((eventObj) -> {
                if (eventObj instanceof Event event) {
                    CancelEventEffect.cancelEvent(event);
                } else {
                    System.out.println("cancelEvent called with non-event: " + eventObj);
                }
            }));

			interpreter.execfile(file.getAbsolutePath());
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
					func.__call__(); // Try calling with no arguments
				} catch (Exception ignored) {
					// Fallback to calling with provided arguments
					func.__call__(Arrays.stream(args).map(Py::java2py).toArray(PyObject[]::new));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Helper for no-arg callables
	private PyObject makeCallable(Runnable runnable) {
		return new PyObject() {
			@Override
			public PyObject __call__() {
				runnable.run();
				return Py.None;
			}
		};
	}

	// Helper for single-string-arg callables
	private PyObject makeCallable(java.util.function.Consumer<String> consumer) {
		return new PyObject() {
			@Override
			public PyObject __call__(PyObject arg) {
				consumer.accept(arg.toString());
				return Py.None;
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
