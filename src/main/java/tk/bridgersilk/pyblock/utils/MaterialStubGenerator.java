package tk.bridgersilk.pyblock.utils;

import org.bukkit.Material;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class MaterialStubGenerator {

	public static void ensureMaterialStub(JavaPlugin plugin) {
		File stubFile = new File(plugin.getDataFolder(), "scripts/pyblock/__init__.pyi");

		try {
			stubFile.getParentFile().mkdirs();

			if (!stubFile.exists()) {
				stubFile.createNewFile();
			}

			String content = new String(java.nio.file.Files.readAllBytes(stubFile.toPath()), StandardCharsets.UTF_8);

			if (content.contains("class Material:")) {
				plugin.getLogger().info("[PyBlock] Material stub already exists, skipping generation.");
				return;
			}

			plugin.getLogger().info("[PyBlock] Generating Material enum stub for __init__.pyi...");

			StringBuilder stub = new StringBuilder();
			stub.append("\n\nclass Material:\n");
			stub.append("    \"\"\"Represents a Bukkit Material constant (item/block type).\"\"\"\n");

			for (Material mat : Material.values()) {
				stub.append("    ").append(mat.name()).append(": \"Material\"\n");
			}

			stub.append("    ...\n");

			try (FileWriter writer = new FileWriter(stubFile, true)) {
				writer.write(stub.toString());
			}

			plugin.getLogger().info("[PyBlock] Material enum stub successfully generated.");

		} catch (IOException e) {
			e.printStackTrace();
			plugin.getLogger().severe("[PyBlock] Failed to generate Material stub: " + e.getMessage());
		}
	}
}
