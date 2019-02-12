package tk.ramennoodles.instantnoodles.adderall;

import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import tk.ramennoodles.instantnoodles.Main;

public class SignPlaceListener implements Listener {
	
	
	public boolean hasEmptySlot(Chest c) {
		for (ItemStack stack : c.getInventory().getContents()) {
            if (stack == null) { return true; }
        }
		return false;
	}
	
	public void dumpItemsOfType(Player p, Chest chest, Material mat) {
		for (ItemStack i : p.getInventory().getContents()) {
			if ((i!=null) && (i.getType() == mat)) {
				if (!hasEmptySlot(chest)) { break; }
				
				chest.getInventory().addItem(i.clone());
				p.getInventory().removeItem(i);
			}
		}
	}
	
	public void dumpItemsOfGroup(Player p, Chest chest, String group) {
		List<String> materialNameList = Main.config.getStringList("adderall.groups."+group);
		
		for (ItemStack i : p.getInventory().getContents()) {
			if ((i!=null) && (materialNameList.contains(i.getType().toString()))) {
				if (!hasEmptySlot(chest)) { break; }
				
				chest.getInventory().addItem(i.clone());
				p.getInventory().removeItem(i);
			}
		}
	}
	
	
	
	@EventHandler
	public void signClickEvent(PlayerInteractEvent e) {
		if (e.getClickedBlock()==null) { return; }
		if (! (e.getClickedBlock().getState() instanceof Sign)) { return; }
		
		Sign sign = (Sign) e.getClickedBlock().getState();
		Player p = e.getPlayer();
		
		if (!sign.getLine(0).equalsIgnoreCase(ChatColor.BLUE+""+ChatColor.BOLD+"SORT")) { return; }
		
		org.bukkit.material.Sign s = (org.bukkit.material.Sign) e.getClickedBlock().getState().getData();
		Block attached = e.getClickedBlock().getRelative(s.getAttachedFace());
			
		if (attached.getType() == Material.CHEST || attached.getType() == Material.TRAPPED_CHEST) {
			Chest c = (Chest) attached.getState();
			
			for (int i = 1; i < 3; i++) {
				String line = sign.getLine(i);
				
				if (Material.getMaterial(line) != null) {
					dumpItemsOfType(p, c, Material.getMaterial(line));
				} else if (Main.config.contains("adderall.groups."+line)) {
					dumpItemsOfGroup(p, c, line);
				}
				
			}
		}
	}
	
	@EventHandler
	public void signPlaceEvent(SignChangeEvent e) {
		String header = e.getLine(0);
		Sign sign = (Sign) e.getBlock().getState();
		
		if (header.equalsIgnoreCase("#sort")) {
			e.setLine(0, ChatColor.BLUE+""+ChatColor.BOLD+"SORT");
			
			
			for (int i = 1; i < 3; i++) {
				boolean failedMaterial = false;
				boolean failedGroups = false;
				
				String line = e.getLine(i);
				
				if (Material.getMaterial(line) == null) {failedMaterial = true;}
				
				if (!Main.config.contains("adderall.groups."+line)) {failedGroups = true;}
				
				if (failedGroups && failedMaterial) {
					e.getPlayer().sendMessage(ChatColor.RED+"No group or item type "+line+" found!");
					e.setLine(0, ChatColor.RED+""+ChatColor.BOLD+"SORT");
					return;
				}
				
				e.getPlayer().sendMessage(ChatColor.BLUE+"Sorting chest created successfully!");
			}
		}
	}
}
