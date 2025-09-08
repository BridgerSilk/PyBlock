---

# 📑 events.md — Events

Events in PyBlock are Python functions that run when something happens on the server (like a block breaking or a player joining).

### ✅ Usage

```python
def event_block_break(event):
    block = event["block"]
    player = event["player"]
    broadcast(f"{player} broke {block}")
```

---

## 📚 Table of Contents

* [Block Events](#block-events)
* [Player Events](#player-events)
* [Chat & Command Events](#chat--command-events)
* [Server Events](#server-events)

---

## 🧱 Block Events

<details>
<summary>Click to expand</summary>

### `event_block_break`

Triggered when a player breaks a block.
**Context:** `player`, `block`, `event`

### `event_block_place`

Triggered when a player places a block.
**Context:** `player`, `block`, `event`

</details>

---

## 🧑 Player Events

<details>
<summary>Click to expand</summary>

### `event_player_join`

Triggered when a player joins.
**Context:** `player`, `event`

### `event_player_quit`

Triggered when a player leaves.
**Context:** `player`, `event`

</details>

---

## 💬 Chat & Command Events

<details>
<summary>Click to expand</summary>

### `event_chat`

Triggered when a player sends a chat message.
**Context:** `player`, `message`, `recipients`, `event`

### `event_command`

Triggered when a player runs a command.
**Context:** `player`, `command`, `event`

</details>

---

## 🌍 Server Events

<details>
<summary>Click to expand</summary>

*(More coming soon!)*

</details>

---