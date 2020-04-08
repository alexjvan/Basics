package com.brokencube.basics.permissions;

import com.brokencube.api.API;
import com.brokencube.api.command.Command;

public class Command_Perms extends Command {
	
	public Command_Perms(API api) {
		//	  api  command         description                           perm     use case alt      default
		super(api, "permissions", "Commands to deal with permissions.", "perms", "/perms", "perms", "perms unregistered");
	}
	
}
