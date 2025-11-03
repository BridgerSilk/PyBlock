from typing import Any, List, Optional
import uuid

# STORAGE ##########################################################################################################################
def save_var(entry_name: str, entry_value: Any) -> None:
    """
    Save a variable to the variables.csv file.
    (variables reset after stopping the server without saving them)
    """
    ...

def load_var(entry_name: str) -> None:
    """
    Load a variable from the variables.csv file.
    """
    ...

# EFFECTS ##########################################################################################################################
def broadcast(msg: str) -> None:
    """
    Broadcasts a message to all players on the server and to the server console.
    """
    ...

def give_item(player_uuid: uuid.UUID, item: ItemStack) -> None:
    """
    Gives an item to a player.
    """
    ...
    
def cancel_event(event: str) -> None:
    """
    Cancels a Bukkit event (e.g., BlockBreakEvent).
    """
    ...

# EXPRESSIONS ##########################################################################################################################
def get_active_item(livingentity_uuid: uuid.UUID) -> ItemStack:
    """
    Gets the item currently in use by a LivingEntity.
    """
    ...

def get_health(livingentity_uuid: uuid.UUID) -> float:
    """
    Gets the health of a LivingEntity.
    """
    ...

def get_location(entity_uuid: uuid.UUID) -> dict:
    """
    Gets the location of an entity.
    """
    ...

def get_uuid(player_name: str) -> uuid.UUID:
    """
    Gets the UUID of a player.
    """
    ...

# Utils #####################################################################################################################################

class new_item:
    """
    Create a new ItemStack instance.
    Use Material.* for the item type.
    """

    def __init__(self, material: str | Material, amount: int = 1, plugin: Any = None): ...
    def name(self, name: str) -> "new_item":
        """
        Set the name of an item. (colors supported)
        """
        ...
    def amount(self, amount: int) -> "new_item":
        """
        Set the amount of an item.
        """
        ...
    def add_lore(self, line: str) -> "new_item":
        """
        Set the lore lines of an item. (colors supported)
        """
        ...
    def set_lore(self, lore: List[str]) -> "new_item":
        """
        Add a lore line to an item. (colors supported)
        """
        ...
    def add_enchantment(self, enchant: Enchantment, level: int) -> "new_item":
        """
        Add an enchantment to an item. (Enchantment.*)
        """
        ...
    def unbreakable(self, unbreakable: bool) -> "new_item":
        """
        Set wheter an item should drain durability.
        """
        ...
    def add_item_flags(self, *flags: ItemFlag) -> "new_item":
        """
        Add a custom item flag to an item. (ItemFlag.*)
        """
        ...
    def custom_model_data(self, data: Optional[int]) -> "new_item":
        """
        Add custom model data to an item.
        """
        ...
    def persistent_data(self, key: str, value: str) -> "new_item":
        """
        Add persistent data to an item.
        """
        ...
    def build(self) -> ItemStack:
        """
        Return the custom ItemStack instance.
        """
        ...

# Types ##################################################################################################################################
class ItemStack:
    """Represents a Bukkit ItemStack (result of new_item.build())."""
    ...

# Bukkit Enums ###########################################################################################################################
class Enchantment:
    """Represents a Bukkit Enchantment constant."""
    AQUA_AFFINITY: "Enchantment"
    BANE_OF_ARTHROPODS: "Enchantment"
    BINDING_CURSE: "Enchantment"
    BLAST_PROTECTION: "Enchantment"
    BREACH: "Enchantment"
    CHANNELING: "Enchantment"
    DENSITY: "Enchantment"
    DEPTH_STRIDER: "Enchantment"
    EFFICIENCY: "Enchantment"
    FEATHER_FALLING: "Enchantment"
    FIRE_ASPECT: "Enchantment"
    FIRE_PROTECTION: "Enchantment"
    FLAME: "Enchantment"
    FORTUNE: "Enchantment"
    FROST_WALKER: "Enchantment"
    IMPALING: "Enchantment"
    INFINITY: "Enchantment"
    KNOCKBACK: "Enchantment"
    LOOTING: "Enchantment"
    LOYALTY: "Enchantment"
    LUCK_OF_THE_SEA: "Enchantment"
    LURE: "Enchantment"
    MENDING: "Enchantment"
    MULTISHOT: "Enchantment"
    PIERCING: "Enchantment"
    POWER: "Enchantment"
    PROJECTILE_PROTECTION: "Enchantment"
    PROTECTION: "Enchantment"
    PUNCH: "Enchantment"
    QUICK_CHARGE: "Enchantment"
    RESPIRATION: "Enchantment"
    RIPTIDE: "Enchantment"
    SHARPNESS: "Enchantment"
    SILK_TOUCH: "Enchantment"
    SMITE: "Enchantment"
    SOUL_SPEED: "Enchantment"
    SWEEPING_EDGE: "Enchantment"
    SWIFT_SNEAK: "Enchantment"
    THORNS: "Enchantment"
    UNBREAKING: "Enchantment"
    VANISHING_CURSE: "Enchantment"
    WIND_BURST: "Enchantment"
    ...

class ItemFlag:
    """Represents a Bukkit ItemFlag constant."""
    HIDE_ADDITIONAL_TOOLTIP: "ItemFlag"
    HIDE_ARMOR_TRIM: "ItemFlag"
    HIDE_ATTRIBUTE_MODIFIERS: "ItemFlag"
    HIDE_ATTRIBUTES: "ItemFlag"
    HIDE_AXOLOTL_VARIANT: "ItemFlag"
    HIDE_BANNER_PATTERNS: "ItemFlag"
    HIDE_BASE_COLOR: "ItemFlag"
    HIDE_BEES: "ItemFlag"
    HIDE_BLOCK_ENTITY_DATA: "ItemFlag"
    HIDE_BLOCK_STATE: "ItemFlag"
    HIDE_BLOCKS_ATTACKS: "ItemFlag"
    HIDE_BREAK_SOUND: "ItemFlag"
    HIDE_BUCKET_ENTITY_DATA: "ItemFlag"
    HIDE_BUNDLE_CONTENTS: "ItemFlag"
    HIDE_CAN_BREAK: "ItemFlag"
    HIDE_CAN_PLACE_ON: "ItemFlag"
    HIDE_CAT_COLLAR: "ItemFlag"
    HIDE_CAT_VARIANT: "ItemFlag"
    HIDE_CHARGED_PROJECTILES: "ItemFlag"
    HIDE_CHICKEN_VARIANT: "ItemFlag"
    HIDE_CONSUMABLE: "ItemFlag"
    HIDE_CONTAINER: "ItemFlag"
    HIDE_CONTAINER_LOOT: "ItemFlag"
    HIDE_COW_VARIANT: "ItemFlag"
    HIDE_CREATIVE_SLOT_LOCK: "ItemFlag"
    HIDE_CUSTOM_DATA: "ItemFlag"
    HIDE_CUSTOM_MODEL_DATA: "ItemFlag"
    HIDE_CUSTOM_NAME: "ItemFlag"
    HIDE_DAMAGE: "ItemFlag"
    HIDE_DAMAGE_RESISTANT: "ItemFlag"
    HIDE_DEATH_PROTECTION: "ItemFlag"
    HIDE_DEBUG_STICK_STATE: "ItemFlag"
    HIDE_DESTROYS: "ItemFlag"
    HIDE_DYE: "ItemFlag"
    HIDE_DYED_COLOR: "ItemFlag"
    HIDE_ENCHANTABLE: "ItemFlag"
    HIDE_ENCHANTMENT_GLINT_OVERRIDE: "ItemFlag"
    HIDE_ENCHANTMENTS: "ItemFlag"
    HIDE_ENCHANTS: "ItemFlag"
    HIDE_ENTITY_DATA: "ItemFlag"
    HIDE_EQUIPPABLE: "ItemFlag"
    HIDE_FIREWORK_EXPLOSION: "ItemFlag"
    HIDE_FIREWORKS: "ItemFlag"
    HIDE_FOOD: "ItemFlag"
    HIDE_FOX_VARIANT: "ItemFlag"
    HIDE_FROG_VARIANT: "ItemFlag"
    HIDE_GLIDER: "ItemFlag"
    HIDE_HORSE_VARIANT: "ItemFlag"
    HIDE_INSTRUMENT: "ItemFlag"
    HIDE_INTANGIBLE_PROJECTILE: "ItemFlag"
    HIDE_ITEM_MODEL: "ItemFlag"
    HIDE_ITEM_NAME: "ItemFlag"
    HIDE_JUKEBOX_PLAYABLE: "ItemFlag"
    HIDE_LLAMA_VARIANT: "ItemFlag"
    HIDE_LOCK: "ItemFlag"
    HIDE_LODESTONE_TRACKER: "ItemFlag"
    HIDE_LORE: "ItemFlag"
    HIDE_MAP_COLOR: "ItemFlag"
    HIDE_MAP_DECORATIONS: "ItemFlag"
    HIDE_MAP_ID: "ItemFlag"
    HIDE_MAP_POST_PROCESSING: "ItemFlag"
    HIDE_MAX_DAMAGE: "ItemFlag"
    HIDE_MAX_STACK_SIZE: "ItemFlag"
    HIDE_MOOSHROOM_VARIANT: "ItemFlag"
    HIDE_NOTE_BLOCK_SOUND: "ItemFlag"
    HIDE_OMINOUS_BOTTLE_AMPLIFIER: "ItemFlag"
    HIDE_PAINTING_VARIANT: "ItemFlag"
    HIDE_PARROT_VARIANT: "ItemFlag"
    HIDE_PIG_VARIANT: "ItemFlag"
    HIDE_PLACED_ON: "ItemFlag"
    HIDE_POT_DECORATIONS: "ItemFlag"
    HIDE_POTION_CONTENTS: "ItemFlag"
    HIDE_POTION_DURATION_SCALE: "ItemFlag"
    HIDE_PROFILE: "ItemFlag"
    HIDE_PROVIDES_BANNER_PATTERNS: "ItemFlag"
    HIDE_PROVIDES_TRIM_MATERIAL: "ItemFlag"
    HIDE_RABBIT_VARIANT: "ItemFlag"
    HIDE_RARITY: "ItemFlag"
    HIDE_RECIPES: "ItemFlag"
    HIDE_REPAIR_COST: "ItemFlag"
    HIDE_REPAIRABLE: "ItemFlag"
    HIDE_SALMON_SIZE: "ItemFlag"
    HIDE_SHEEP_COLOR: "ItemFlag"
    HIDE_SHULKER_COLOR: "ItemFlag"
    HIDE_STORED_ENCHANTMENTS: "ItemFlag"
    HIDE_SUSPICIOUS_STEW_EFFECTS: "ItemFlag"
    HIDE_TOOL: "ItemFlag"
    HIDE_TOOLTIP_DISPLAY: "ItemFlag"
    HIDE_TOOLTIP_STYLE: "ItemFlag"
    HIDE_TRIM: "ItemFlag"
    HIDE_TROPICAL_FISH_BASE_COLOR: "ItemFlag"
    HIDE_TROPICAL_FISH_PATTERN: "ItemFlag"
    HIDE_TROPICAL_FISH_PATTERN_COLOR: "ItemFlag"
    HIDE_UNBREAKABLE: "ItemFlag"
    HIDE_USE_COOLDOWN: "ItemFlag"
    HIDE_USE_REMAINDER: "ItemFlag"
    HIDE_VILLAGER_VARIANT: "ItemFlag"
    HIDE_WEAPON: "ItemFlag"
    HIDE_WOLF_COLLAR: "ItemFlag"
    HIDE_WOLF_SOUND_VARIANT: "ItemFlag"
    HIDE_WOLF_VARIANT: "ItemFlag"
    HIDE_WRITABLE_BOOK_CONTENT: "ItemFlag"
    HIDE_WRITTEN_BOOK_CONTENT: "ItemFlag"
    ...