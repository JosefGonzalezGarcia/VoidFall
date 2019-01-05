package voidFall;


import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.connorlinfoot.titleapi.TitleAPI;


public class Listen implements Listener{

	ClaseVoidFall plugin;
	Map<UUID, Location> localizaciones;
	Integer tiempo;
	String mundo, mensajeCaida;
	
	
	
	public Integer getTiempo() {
		if(tiempo!=null) 
			return tiempo;
		else
			return 0;
	}


	public String getMundo() {
		if(mundo!=null)
			return mundo;
		else
			return "";
	}

	public String getMensajeCaida() {
		if(mensajeCaida!=null) 
			return mensajeCaida;
		else 
			return "";
		
	}


	public Listen(ClaseVoidFall plugin){
        this.plugin=plugin;
        this.localizaciones = new HashMap<UUID, Location>();
        this.tiempo = plugin.getConfig().getInt("tiempo-ceguera");
        this.mundo = plugin.getConfig().getString("mundo");
        this.mensajeCaida = plugin.getConfig().getString("mensaje-caida");
	}

    


	@EventHandler
    public void quitEvent(PlayerQuitEvent e){
		Player p = e.getPlayer();
		localizaciones.remove(p.getUniqueId());
	}
	
	
    @EventHandler
    public void voidHit(PlayerMoveEvent e){
        Player p = e.getPlayer();
        if(p.getWorld().getName().equals(getMundo()))
       	{
	        if(p.isOnGround()) {
	        	localizaciones.put(p.getUniqueId(), p.getLocation());
	        }
	        	
	        if(p.getLocation().getBlockY()<0) {
	        	p.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, getTiempo()*20, 1, true));
	        	TitleAPI.sendTitle(p, 30, getTiempo()*20, 10, "\u00A7l"+ChatColor.DARK_RED + getMensajeCaida());
	        	Location loc = localizaciones.get(p.getUniqueId());
	        	if(loc!=null) {
	        		p.teleport(loc);
	        	}else {
	        		p.getServer().dispatchCommand(p.getServer().getConsoleSender(), "spawn " + p.getName());
	        	}
	        }
       	}
    }
    
}
