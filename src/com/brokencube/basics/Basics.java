package com.brokencube.basics;

import java.util.concurrent.TimeUnit;

import org.bukkit.plugin.java.JavaPlugin;

import com.brokencube.api.API;
import com.brokencube.basics.acgm.Command_GM;
import com.brokencube.basics.acgm.Command_GM_Toggle;
import com.brokencube.basics.permissions.Command_Perms;
import com.brokencube.basics.permissions.Command_Perms_Set;
import com.brokencube.basics.permissions.Command_Perms_Unregistered;
import com.brokencube.basics.placeholders.Command_Placeholders;
import com.brokencube.basics.placeholders.Command_Placeholders_List;
import com.brokencube.basics.ranks.Command_Ranks;
import com.brokencube.basics.ranks.Command_Ranks_View;
import com.brokencube.basics.server.Command_Server;
import com.brokencube.basics.server.Command_Server_Stop;

public class Basics extends JavaPlugin {
	private API api;
	
	@Override
	public void onEnable() {
		api = API.instance;
		while(api == null) {
			api = API.instance;
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) { }
		}
		api.getPlug().registerPlugin(this, "Basics");
		

		api.getCR().registerCommand(new Command_GM(api));
		api.getCR().registerCommand(new Command_GM_Toggle(api));
		
		api.getCR().registerCommand(new Command_Perms(api));
		api.getCR().registerCommand(new Command_Perms_Set(api));
		api.getCR().registerCommand(new Command_Perms_Unregistered(api));

		api.getCR().registerCommand(new Command_Placeholders(api));
		api.getCR().registerCommand(new Command_Placeholders_List(api));
		
		api.getCR().registerCommand(new Command_Ranks(api));
		api.getCR().registerCommand(new Command_Ranks_View(api));

		api.getCR().registerCommand(new Command_Server(api));
		api.getCR().registerCommand(new Command_Server_Stop(api));
	}
	
	@Override
	public void onDisable() {
		
	}
}
