package com.brokencube.basics.server;

import com.brokencube.api.API;
import com.brokencube.api.command.Command;
import com.brokencube.api.user.Console;
import com.brokencube.api.user.Executor;
import com.brokencube.api.user.User;

public class Command_Server_Stop extends Command {
	
	public Command_Server_Stop(API api) {
		//	  api  command         description         perm             use case
		super(api, "server stop", "Stop the server.", "server.stop", "/server stop");
	}
	
	public void userExe(User u, String[] args) {
		exe(u, args);
	}
	
	public void consoleExe(Console c, String[] args) {
		exe(c, args);
	}
	
	private void exe(Executor e, String[] args) {
		instance.getServer().shutdown();
	}

}
