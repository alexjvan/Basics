package com.brokencube.basics.server;

import com.brokencube.api.API;
import com.brokencube.api.command.Command;

public class Command_Server extends Command {
	
	public Command_Server(API api) {
		//	  api  command    description                           perm      use case
		super(api, "server", "Commands to deal with the server.", "server", "/server");
	}

}
