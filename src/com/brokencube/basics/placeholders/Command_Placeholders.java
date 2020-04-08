package com.brokencube.basics.placeholders;

import com.brokencube.api.API;
import com.brokencube.api.command.Command;

public class Command_Placeholders extends Command {
	
	public Command_Placeholders(API api) {
		//	  api   command         description                            perm            use case         alt           default
		super(api, "placeholders", "Commands to deal with placeholders.", "placeholders", "/placeholders", new String(), "placeholders list");
	}

}
