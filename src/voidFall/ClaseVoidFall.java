package voidFall;


import org.bukkit.plugin.java.JavaPlugin;

public class ClaseVoidFall extends JavaPlugin{

	
	
    @Override
    public void onEnable() {
    	this.saveDefaultConfig();
    	getServer().getPluginManager().registerEvents(new Listen(this), this);
    	    	
    }
    @Override
    public void onDisable() {

    }

}
