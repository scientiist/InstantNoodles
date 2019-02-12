package tk.ramennoodles.instantnoodles.events;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class TablistJoinListener implements Listener {

	public void updateLeaderboard(Player p) {
		
		
		String header = ChatColor.RED+""+ChatColor.BOLD+"Ramen Noodles Amplified"+ChatColor.RESET+"\n"+
						ChatColor.AQUA+"ramennoodlesamplified.com"+"\n"+
						ChatColor.DARK_GRAY+"=============================================";
		
		
		String playerCount = ""+Bukkit.getOnlinePlayers().size() +"/"+ Bukkit.getMaxPlayers();
		
		String footer = ChatColor.GRAY+"Players: " +playerCount+"\n"+
						ChatColor.YELLOW+"Make sure to vote for the server!";
		
		p.setPlayerListHeader(header);
		p.setPlayerListFooter(footer);
	}
	
	@EventHandler
	public void playerJoins(PlayerJoinEvent e) {
		//Player p = e.getPlayer();
		
		for (Player p : Bukkit.getOnlinePlayers()) {
			updateLeaderboard(p);
		}
		
	}
	
	@EventHandler
	public void playerLeave(PlayerQuitEvent e) {
		for (Player p : Bukkit.getOnlinePlayers()) {
			updateLeaderboard(p);
		}
	}
}
