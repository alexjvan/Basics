package com.brokencube.basics.ranks;

import com.brokencube.api.API;
import com.brokencube.api.command.Command;

public class Command_Ranks extends Command {
	
	public Command_Ranks(API api) {
		//	  api  command  description                     perm      use case alt           default
		super(api, "ranks", "Commands to deal with ranks.", "ranks", "/ranks", new String(), "ranks view");
	}

}
