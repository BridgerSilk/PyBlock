---

# 📑 variables.md — Variables

Variables persist data across server restarts.

### ✅ Usage

```python
counter = load_var("joins") or 0
counter += 1
save_var("joins", counter)
```

---

## 📚 Table of Contents

* [Saving](#saving)
* [Loading](#loading)
* [Example](#example)

---

## 💾 Saving

```python
save_var(entry_name: str, entry_value: Any)
```

Saves or overwrites an entry in `variables.csv`.

---

## 📂 Loading

```python
load_var(entry_name: str)
```

Loads an entry from `variables.csv`. Returns `None` if missing.

---

## ✅ Example

```python
def event_player_join(event):
    counter = load_var("joins") or 0
    counter += 1
    save_var("joins", counter)
    broadcast(f"Total joins so far: {counter}")
```

---