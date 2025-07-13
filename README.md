<h1 align="center">🌍 PyBlock</h1>
<p align="center">
  PyBlock is a powerful plugin for Minecraft servers that lets you write server-side logic using <b>Python</b> instead of Java. Built with <a href="https://www.jython.org/">Jython</a>, it provides a <b>flexible and scriptable</b> way to control in-game behavior with ease.
</p>

<p align="center">
  <img src="https://badgen.now.sh/badge/minecraft%20version/1.21.x/blue" alt="Minecraft Version">
  <img src="https://badgen.now.sh/badge/server%20type/paper" alt="Region Format">
  <img src="https://badgen.now.sh/badge/performance/optimized%20for%20WorldHost/green" alt="Performance">
</p>

---

## 💡 About

PyBlock brings Python scripting to Minecraft servers. Want to cancel a block break? Broadcast a message when a player joins? You can do it all in just a few lines of Python, without ever touching Java.

PyBlock is ideal for server owners and developers who want the flexibility of plugins, without compiling Java code or restarting the server for every change.

---

## ✨ Key Features

- ✅ Write Minecraft Bukkit API logic in Python
- 📦 Reload scripts with a single command while the server is running
- 🔧 Built-in utility functions like `broadcast()` and `cancelEvent()`
- 🧠 Thread-local event context handling for safe execution
- 📁 Simple file-based script loading system

---

## 📜 Example Scripts

Here's how easy it is to use PyBlock:

```python
# prevent blocks from being broken

def event_block_break(event, player, block):
    broadcast(player + " tried braking a " + block)
    cancelEvent(event)  # Prevent the block from breaking
````

```python
# greet players on server join

def event_player_join(event, player):
    broadcast("Welcome to the server, " + player + "!")
```

These Python functions get automatically called when the respective events (e.g. `BlockBreakEvent`, `PlayerJoinEvent`) are fired.

---

## 📚 Documentation

Full documentation is available [here](https://github.com/BridgerSilk/PyBlock)

---

## 📂 Scripts Folder

Place your Python `.py` scripts inside the `plugins/PyBlock/scripts/` folder. PyBlock will load and run them automatically on startup or when reloaded.

---

## 📌 Requirements

* Minecraft Server (Spigot, Paper or a fork of these)
* Java 17+

---

## 🛠️ Installation

1. Download the latest PyBlock release from the [Releases](https://github.com/BridgerSilk/PyBlock/releases) tab
2. Drop it into your `plugins` folder
3. Start your server once to generate folders
4. Add your `.py` scripts into the `scripts/` directory
5. Run `/pyblock reload` (if applicable) or restart the server to apply changes

---

## 🙌 Created by [BridgerSilk](https://github.com/BridgerSilk)

Have fun scripting! 🐍
