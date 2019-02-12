package tk.ramennoodles.instantnoodles.events;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Chest;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.vexsoftware.votifier.model.Vote;
import com.vexsoftware.votifier.model.VotifierEvent;


public class VoteListener implements Listener {
	
	Random rand = new Random();

	public ItemStack getRandomReward() {
		
		int percent = rand.nextInt(100);
			
		/* Rewards:
		 * 1 Diamond 40%
		 * 8 Iron 40%
		 * 5 Diamonds 5%
		 * 1 Shulker Box 5%
		 * Chest of Building Blocks 5%
		 * 3 Shulker Boxes 4%
		 * Bedrock Protection Stone 1%
		 */
		if (percent >= 100) {
			
			ItemStack bedrock = new ItemStack(Material.BEDROCK, 1);
			ItemMeta meta = bedrock.getItemMeta();
			meta.setDisplayName(ChatColor.BLUE+"Bedrock Protection");
			bedrock.setItemMeta(meta);
			return bedrock;
			
		} else if (percent >= 95) {
			return new ItemStack(Material.SHULKER_BOX, 3);
		} else if (percent >= 90) {
			
			return new ItemStack(Material.FIREWORK_ROCKET, 64);
			
		} else if (percent >= 85) {
			return new ItemStack(Material.SHULKER_BOX, 1);
		} else if (percent >= 80) {
			return new ItemStack(Material.DIAMOND, 5);
		} else if (percent >= 40) {
			return new ItemStack(Material.IRON_INGOT, 8);
		} else {
			return new ItemStack(Material.DIAMOND, 1);
		}
	}
	
	@EventHandler
	public void onPlayerVote(VotifierEvent e) {
		Vote v = e.getVote();
		
		Player p = Bukkit.getServer().getPlayer(v.getUsername());
		
		if (p == null) { return; }
		
		
		ItemStack reward = getRandomReward();
		
		
		String name = reward.getItemMeta().getDisplayName();
		
		if (name.isEmpty()) {
			name = reward.getType().toString().toLowerCase();
		}
		
		name = Integer.toString(reward.getAmount()) + " "+name;
		
		Bukkit.getServer().broadcastMessage(p.getDisplayName()+ChatColor.WHITE+" voted and recieved "+ChatColor.GREEN+name);
		
		p.getInventory().addItem(reward);
	}
	
	
}
