package tk.ramennoodles.instantnoodles.events;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatListener implements Listener {

		@EventHandler
		public void playerDies(PlayerDeathEvent e) {
			Bukkit.broadcastMessage("Press F to pay respects.");
		}
	
		@EventHandler
		public void playerChat(AsyncPlayerChatEvent e) {
			Player p = e.getPlayer();
			
			String s = e.getMessage()
						.replace(">", ChatColor.DARK_GREEN+">")
						.replace(":b:", ChatColor.DARK_RED+""+ChatColor.BOLD+"B"+ChatColor.RESET);
			
			
			for (Player plr : Bukkit.getOnlinePlayers()) {
				String username = "@"+plr.getName();
				
				if (s.contains(username) ) {
					s = s.replace("@"+plr.getName(), ChatColor.UNDERLINE+""+ChatColor.YELLOW+plr.getName()+ChatColor.RESET);
					
					plr.playSound(plr.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 2, 1);
				}
			}
			
			
			e.setMessage(s);
			
			e.setFormat(p.getDisplayName() + ChatColor.DARK_GRAY + "» " + ChatColor.WHITE + s);
		}
}
