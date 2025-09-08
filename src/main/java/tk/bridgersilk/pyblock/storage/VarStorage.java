package tk.bridgersilk.pyblock.storage;

import java.io.*;
import java.nio.file.*;
import java.util.*;
import org.json.*;

public class VarStorage {

	private static final String FILE_PATH = "pyblock/variables.csv";

	static {
		try {
			Files.createDirectories(Paths.get("pyblock"));
			File file = new File(FILE_PATH);
			if (!file.exists()) {
				file.createNewFile();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// save vars (overwrite if existing)
	public static void saveVar(String name, Object value) {
		Map<String, String[]> vars = loadAll();

		String type = (value == null) ? "None" : value.getClass().getSimpleName();
		String jsonValue;

		try {
			if (value == null) {
				jsonValue = "null";
			} else if (value instanceof Collection || value instanceof Map || value.getClass().isArray()) {
				jsonValue = new JSONObject().put("v", value).toString();
			} else {
				// json friendly fallback
				jsonValue = JSONObject.valueToString(value);
			}
		} catch (Exception e) {
			// string fallback
			jsonValue = "\"" + value.toString() + "\"";
		}

		vars.put(name, new String[]{type, jsonValue});
		writeAll(vars);
	}

	// load var by entry name
	public static Object loadVar(String name) {
		Map<String, String[]> vars = loadAll();
		if (!vars.containsKey(name)) return null;

		String[] entry = vars.get(name);
		String type = entry[0];
		String jsonValue = entry[1];

		try {
			switch (type) {
				case "Integer":
				case "int":
					return Integer.valueOf(jsonValue);
				case "Long":
				case "long":
					return Long.valueOf(jsonValue);
				case "Float":
					return Float.valueOf(jsonValue);
				case "Double":
				case "float":
					return Double.valueOf(jsonValue);
				case "Boolean":
					return Boolean.valueOf(jsonValue);
				case "Complex":
				case "complex":
					// store as string like "a+bj"
					return jsonValue;
				case "List":
				case "Tuple":
				case "Set":
				case "FrozenSet":
					return new JSONArray(jsonValue).toList();
				case "Dict":
				case "Map":
					return new JSONObject(jsonValue).toMap();
				case "String":
				case "Unicode":
					return jsonValue.substring(1, jsonValue.length()-1);
				case "None":
					return null;
				default:
					return jsonValue;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	// read all vars
	private static Map<String, String[]> loadAll() {
		Map<String, String[]> vars = new HashMap<>();
		try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
			String line;
			while ((line = reader.readLine()) != null) {
				String[] parts = line.split(",", 3);
				if (parts.length == 3) {
					vars.put(parts[0], new String[]{parts[1], parts[2]});
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return vars;
	}

	// write all vars
	private static void writeAll(Map<String, String[]> vars) {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
			for (Map.Entry<String, String[]> entry : vars.entrySet()) {
				writer.write(entry.getKey() + "," + entry.getValue()[0] + "," + entry.getValue()[1]);
				writer.newLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
