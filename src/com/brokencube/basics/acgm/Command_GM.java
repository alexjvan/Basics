package com.brokencube.basics.acgm;

import com.brokencube.api.API;
import com.brokencube.api.command.Command;
import com.brokencube.api.command.exceptions.ExecutingDefaultException;
import com.brokencube.api.user.User;

public class Command_GM extends Command {
	
	public Command_GM(API api) {
		//	  api  command     description                         perm use case alt  default
		super(api, "gamemode", "Commands to deal with gamemodes.", "gm", "/gm", "gm", "gm toggle");
	}
	
	@Override
	public void userExe(User u, String[] split) throws ExecutingDefaultException {
		if(split.length == 1 || split.length > 2 || split[1].equalsIgnoreCase("toggle"))
			throw new ExecutingDefaultException();
		
	}

}
