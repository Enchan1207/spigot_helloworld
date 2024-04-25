//
// Spigot plugin example
//
package me.enchan.hello_world;

import org.bukkit.plugin.java.JavaPlugin;

public class App extends JavaPlugin {

    @Override
    public void onEnable() {
        this.getLogger().info("Plugin enabled");
    }

    @Override
    public void onDisable() {
        this.getLogger().info("Plugin disabled");
    }

}
