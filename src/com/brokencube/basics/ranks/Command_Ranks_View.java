package com.brokencube.basics.ranks;

import com.brokencube.api.API;
import com.brokencube.api.Messages;
import com.brokencube.api.command.Command;
import com.brokencube.api.user.User;

public class Command_Ranks_View extends Command {
	
	public Command_Ranks_View(API api) {
		//	  api   command       description                     perm           use case
		super(api, "ranks view", "Commands to deal with ranks.", "ranks.view", "/ranks view [user]");
	}
	
	@Override
	public void userExe(User u, String[] split) {
		if(split.length > 3) {
			u.sendMessage(Messages.wrongArgs);
			return;
		}
		
		if(split.length == 2) {
			u.sendMessage(Messages.success+"You have the rank of "+u.rank.prefix+Messages.success+".");
		} else {
			User target = (User)instance.getUR().getExecutorFromUsername(split[2]);
			if(target == null) {
				u.sendMessage(Messages.error+"That player (&b"+split[2]+Messages.error+") does not exist or is not online!");
				return;
			}
			u.sendMessage("&b"+split[2]+Messages.success+" has the rank of "+target.rank.prefix);
		}
		
	}
}
