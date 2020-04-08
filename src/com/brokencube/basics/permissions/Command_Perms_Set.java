package com.brokencube.basics.permissions;

import com.brokencube.api.API;
import com.brokencube.api.Messages;
import com.brokencube.api.Utils;
import com.brokencube.api.command.Command;
import com.brokencube.api.permissions.Permission;
import com.brokencube.api.ranks.SimpleRank;
import com.brokencube.api.user.Console;
import com.brokencube.api.user.Executor;
import com.brokencube.api.user.User;

public class Command_Perms_Set extends Command {
	
	public Command_Perms_Set(API api) {
		super(api, "permissions set", "Set a permission to a rank.", "perms.set", "/perms set <perm> <rank>");
	}

	@Override
	public void userExe(User u, String[] split) {
		execute(u, split);
	}
	
	@Override
	public void consoleExe(Console c, String[] split) {
		execute(c, split);
	}
	
	private void execute(Executor e, String[] split) {
		if(split.length != 4) {
			e.sendMessage(Messages.wrongArgs);
			return;
		}
		Permission perm = instance.getPR().getPerm(split[2]);
		String rank = split[3];
		SimpleRank sr;
		if(perm == null) {
			instance.getPR().registerPermission(split[2]);
			perm = instance.getPR().getPerm(split[2]);
		}
		
		if(Utils.isInt(rank)) {
			int i = Integer.parseInt(rank); 
			sr = SimpleRank.getFromNum(i);
		} else {
			sr = SimpleRank.getFromTitle(rank);
		}
		if(sr == null) {
			e.sendMessage(Messages.error+"Rank given (&b"+rank+Messages.error+") is not a valid rank.");
			return;
		}
		// need to insert into db
		if(perm.rank.name().equalsIgnoreCase(SimpleRank.Undefined.name())) {
			instance.getDB().insertQuery("INSERT INTO permissions VALUES ('"+split[2]+"', "+sr.rankNum()+")");
		}
		// need to update db
		else {
			instance.getDB().updateQuery("UPDATE permissions SET rankid="+sr.rankNum()+" WHERE permission=\""+split[2]+"\"");
		}
		perm.rank = sr;
	}
	
}
