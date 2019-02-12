package tk.ramennoodles.instantnoodles;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Commands implements CommandExecutor {
	
	
	

	@Override
	public boolean onCommand(CommandSender cs, Command cmd, String alias, String[] args) {
		String command = cmd.getLabel().toLowerCase();
		
		if (command.equals("discord")) {
			cs.sendMessage(ChatColor.BLUE+"Discord Link: "+ChatColor.YELLOW+"https://discord.gg/nDXpX3G");
			return true;
		}
		
		if (command.equals("local")) {
			if (!(cs instanceof Player)) { return false; }
			
			Player sender = (Player) cs;
			String message = Utility.argsToString(args);
			
			for (Player p : Bukkit.getOnlinePlayers()) {
				if (sender.getLocation().distance(p.getLocation()) < 128) {
					p.sendMessage("(Local) "+sender.getDisplayName()+ ChatColor.DARK_GRAY + "» " + ChatColor.GRAY + message);
				}
			}
		}
		
		if (command.equals("apply")) {
			if (cs instanceof Player) {
				Player p = (Player) cs;
				
				
				p.kickPlayer("Your staff application has been processed. Thank you.");
			}
			return true;
		}
		
		if (command.equals("vote")) {
			cs.sendMessage("");
			cs.sendMessage(ChatColor.YELLOW+"Links to vote: ");
			
			
			List<String> list = Main.config.getStringList("voting.votelinks");
			
			for (String s : list) {
				cs.sendMessage(ChatColor.BLUE+ " | "+s);
			}
			
			cs.sendMessage("");
			cs.sendMessage(ChatColor.GRAY+"Voting gives you rewards and helps the server!");
			return true;
		}
		
		if (command.equals("clearchat")) {
			if (cs.hasPermission("instantnoodles.moderator")) {
				for (int i = 0; i < 100; i++) {
					Bukkit.getServer().broadcastMessage(" ");
				}
				Bukkit.getServer().broadcastMessage(ChatColor.GRAY+"A Staff member has cleared chat.");
			}
			return true;
		}
		
		if (command.equals("server")) {
			
			CommandSender target = cs;
			if (args.length > 0 && args[0] != null && cs.hasPermission("instantnoodles.moderator")) { 
				
				Player p = Bukkit.getPlayer(args[0]);
				
				if (p == null) {
					cs.sendMessage(ChatColor.RED+"Player does not exist!");
					return true;
				}
				
				target = (CommandSender) p;
			}
			
			target.sendMessage(ChatColor.GREEN+"Hello there! Our server is a small community of laid-back players.");
			target.sendMessage(ChatColor.GREEN+"The gameplay is survival, plain and simple. PVP and raiding are allowed.");
			target.sendMessage(ChatColor.GREEN+"You can protect your builds using a gold or diamond block.");
			target.sendMessage(ChatColor.GREEN+"To get started, fly away from spawn using the Elytra you have been given.");
			target.sendMessage(ChatColor.GREEN+"Stop by the server shop and player markets in /warps.");
			return true;
		}
		
		return false;
	}

}
