package tk.ramennoodles.instantnoodles.events;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerBedEnterEvent;

public class SleepListener implements Listener {

	
	@EventHandler
	public void onSleepEvent(PlayerBedEnterEvent e) {
		
		Player sleeper = e.getPlayer();
		
		
		int numPlayers = Bukkit.getOnlinePlayers().size();
		int sleeping = 0;
				
		for (Player p : Bukkit.getOnlinePlayers()) {
			if (p.isSleeping()) {
				sleeping +=1;
			}
		}
		
		int fraction = numPlayers/4;
		
		if (sleeping >= fraction) {
			sleeper.getWorld().setTime(0);
		}
	}
}
