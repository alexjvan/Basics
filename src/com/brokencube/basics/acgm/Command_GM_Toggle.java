package com.brokencube.basics.acgm;

import org.bukkit.GameMode;

import com.brokencube.api.API;
import com.brokencube.api.Messages;
import com.brokencube.api.command.Command;
import com.brokencube.api.user.User;

public class Command_GM_Toggle extends Command {
	
	public Command_GM_Toggle(API api) {
		super(api, "gamemode toggle", "Quick toggle your gamemode.", "gm.toggle", "/gm toggle [user]");
		instance.getPR().registerPermission("gm.toggle.others");
		instance.getPR().registerPermission("gm.toggle.others.hidden");
	}
	
	@Override
	public void userExe(User u, String[] args) {
		if(args.length > 3) {
			u.sendMessage(Messages.wrongArgs);
			return;
		}
		if(args.length == 3) {
			if(u.hasPermission("gm.toggle.others")) {
				User target;
				if(args[2].equalsIgnoreCase("self")) {
					target = u;
				} else {
					target = (User)instance.getUR().getExecutorFromUsername(args[2]);
				}
				
				if(target == null) {
					u.sendMessage(Messages.error+"Target player (&b"+args[2]+Messages.error+") was not found!");
					return;
				}
				
				if(target.getPlayer().getGameMode() == GameMode.ADVENTURE) {
					target.getPlayer().setGameMode(GameMode.CREATIVE);
					if(u.hasPermission("gm.toggle.others.hidden")) {
						target.sendMessage("&aYour gamemode has been toggled to &6&lCREATIVE&a mode.");
					} else {
						target.sendMessage("&aYour gamemode has been toggled to &6&lCREATIVE&a mode by &b"+u.getCustomName()+"&a.");
					}
					u.sendMessage("&aYou toggled &b" +target.getCustomName()+ "&a's gamemode to &6&lCREATIVE &amode.");
				} else if(target.getPlayer().getGameMode() == GameMode.CREATIVE || u.getPlayer().getGameMode() == GameMode.SPECTATOR || u.getPlayer().getGameMode() == GameMode.SURVIVAL) {
					target.getPlayer().setGameMode(GameMode.ADVENTURE);
					if(u.hasPermission("gm.toggle.others.hidden")) {
						target.sendMessage("&aYour gamemode has been toggled to &6&lADVENTURE&a mode.");
					} else {
						target.sendMessage("&aYour gamemode has been toggled to &6&lADVENTURE&a mode by &b"+u.getCustomName()+"&a.");
					}
					u.sendMessage("&aYou toggled &b" +target.getCustomName()+ "&a's gamemode to &6&lADVENTURE &amode.");
				}
			} else {
				u.sendMessage(Messages.noperms);
			}
		} else {
			if(u.getPlayer().getGameMode() == GameMode.ADVENTURE) {
				u.getPlayer().setGameMode(GameMode.CREATIVE);
				u.sendMessage("&aYour gamemode has been toggled to &6&lCREATIVE&a mode.");
			} else if(u.getPlayer().getGameMode() == GameMode.CREATIVE || u.getPlayer().getGameMode() == GameMode.SPECTATOR || u.getPlayer().getGameMode() == GameMode.SURVIVAL) {
				u.getPlayer().setGameMode(GameMode.ADVENTURE);
				u.sendMessage("&aYour gamemode has been toggled to &6&lADVENTURE&a mode.");
			}
		}
	}

}
