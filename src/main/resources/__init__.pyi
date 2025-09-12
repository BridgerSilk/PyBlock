from typing import Any
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
    
def cancel_event(event: str) -> None:
    """
    Cancels a Bukkit event (e.g., BlockBreakEvent).
    """
    ...

# EXPRESSIONS ##########################################################################################################################
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