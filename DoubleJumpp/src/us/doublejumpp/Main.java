package us.doublejumpp;

import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.event.player.PlayerToggleFlightEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener {
	
	public void onEnable() {
		Bukkit.getServer().getPluginManager().registerEvents(this, this);
		System.out.println("Enabled");
	}
	
	@EventHandler
	public void onPlayerFly(PlayerToggleFlightEvent e) {
		Player p = e.getPlayer();
		if (p.getGameMode() != GameMode.CREATIVE) {
			e.setCancelled(true);
			Location loc = p.getLocation();
			p.setAllowFlight(false);
			p.setFlying(false);
			p.setVelocity(p.getLocation().getDirection().multiply(2.5D).setY(1.0D));
			p.spigot().playEffect(loc, Effect.MOBSPAWNER_FLAMES, 1, 1, 1, 1, 1, 1, 1, 1);
			p.playSound(p.getLocation(), Sound.EXPLODE, 1, 1);
		}
	}

	@EventHandler
	public void onPlayerMove(PlayerMoveEvent e) {
		Player p = e.getPlayer();
		if ((e.getPlayer().getGameMode() != GameMode.CREATIVE)
				&& (p.getLocation().getBlock().getRelative(BlockFace.DOWN).getType() != Material.AIR)) {
			p.setAllowFlight(true);
		}
	}
	@EventHandler
	public void onPlayerDrop(PlayerDropItemEvent e) {
		e.setCancelled(true);
		
	}
	
	@EventHandler
	public void onBreak(BlockBreakEvent e) {
		if (!e.getPlayer().hasPermission("hub.build")) {
			e.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onPlace(BlockPlaceEvent e) {
		if (!e.getPlayer().hasPermission("hub.build")) {
			e.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onPickup(PlayerPickupItemEvent e) {
		e.setCancelled(true);
	}
	
	@EventHandler
	public void onFoodChange(FoodLevelChangeEvent e) {
		e.setCancelled(true);
	}
	
	@EventHandler
	public void onDamagge(EntityDamageEvent e) {
		e.setCancelled(true);
	}
	
	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		e.setJoinMessage(null);
	}

}