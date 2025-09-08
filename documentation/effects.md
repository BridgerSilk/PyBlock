---

# 📑 effects.md — Effects

Effects let you perform actions on the server from your scripts.

### ✅ Usage

```python
broadcast("Hello, world!")
```

---

## 📚 Table of Contents

* [Messaging](#messaging)
* [Event Control](#event-control)
* [Debugging](#debugging)

---

## 📢 Messaging

<details>
<summary>Click to expand</summary>

### `broadcast(msg: str)`

Broadcasts a message to all players and the console.

</details>

---

## ⛔ Event Control

<details>
<summary>Click to expand</summary>

### `cancel_event(event: str)`

Cancels a cancellable event.
Requires the raw event object (e.g. `event["event"]`).

</details>

---

## 🛠 Debugging

<details>
<summary>Click to expand</summary>

### `print(msg: str)`

Prints a message to the server console.

</details>

---