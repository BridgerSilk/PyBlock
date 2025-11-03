package tk.bridgersilk.pyblock.utils;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.NamespacedKey;

import java.util.*;

public class ItemBuilder {
	private final ItemStack item;
	private final ItemMeta meta;
	private final JavaPlugin plugin;

	public ItemBuilder(Material material) {
		this(material, 1, null);
	}

	public ItemBuilder(Material material, int amount, JavaPlugin plugin) {
		this.item = new ItemStack(material, amount);
		this.meta = item.getItemMeta();
		this.plugin = plugin;
	}

	public ItemBuilder name(String name) {
		if (meta != null && name != null) meta.setDisplayName(name);
		return this;
	}

    public ItemBuilder amount(int amount) {
        if (amount > 0) {
            item.setAmount(amount);
        }
        return this;
    }

	public ItemBuilder add_lore(String line) {
		if (meta == null || line == null) return this;
		List<String> lore = meta.hasLore() ? meta.getLore() : new ArrayList<>();
		lore.add(line);
		meta.setLore(lore);
		return this;
	}

	public ItemBuilder set_lore(List<String> lore) {
		if (meta != null && lore != null) meta.setLore(new ArrayList<>(lore));
		return this;
	}

	public ItemBuilder add_enchantment(Enchantment enchant, int level) {
		if (meta != null && enchant != null) meta.addEnchant(enchant, level, true);
		return this;
	}

	public ItemBuilder unbreakable(boolean unbreakable) {
		if (meta != null) meta.setUnbreakable(unbreakable);
		return this;
	}

	public ItemBuilder add_item_flags(ItemFlag... flags) {
		if (meta != null) meta.addItemFlags(flags);
		return this;
	}

	public ItemBuilder custom_model_data(Integer data) {
		if (meta != null && data != null) meta.setCustomModelData(data);
		return this;
	}

	public ItemBuilder persistent_data(String key, String value) {
		if (plugin != null && meta != null) {
			NamespacedKey nkey = new NamespacedKey(plugin, key);
			meta.getPersistentDataContainer().set(nkey, PersistentDataType.STRING, value);
		}
		return this;
	}

	public ItemStack build() {
		if (meta != null) item.setItemMeta(meta);
		return item;
	}
}
