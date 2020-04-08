package com.brokencube.basics.permissions;

import java.util.ArrayList;
import java.util.List;

import com.brokencube.api.API;
import com.brokencube.api.Messages;
import com.brokencube.api.command.Command;
import com.brokencube.api.permissions.Permission;
import com.brokencube.api.ranks.SimpleRank;
import com.brokencube.api.user.Console;
import com.brokencube.api.user.Executor;
import com.brokencube.api.user.User;

public class Command_Perms_Unregistered extends Command {
	
	public Command_Perms_Unregistered(API api) {
		super(api, "permissions unregistered", "Get a list of all of the unregistered permisisons in the system.", "perms.unregistered", "/perms unregistered");
	}

	@Override
	public void userExe(User u, String[] split) {
		exe(u, split);
	}
	
	@Override
	public void consoleExe(Console c, String[] split) {
		exe(c, split);
	}
	
	private void exe(Executor e, String[] split) {
		if(split.length > 2) {
			e.sendMessage(Messages.wrongArgs);
			return;
		}
		
		List<String> walked = walkPerms();
		if(walked.size() == 0) {
			e.sendMessage(Messages.success+"There are no more unregistered permissions!");
			return;
		}
		for(int i = 0; i < walked.size(); i++) {
			e.sendMessage("&c"+walked.get(i));
		}
	}
	
	private List<String> walkPerms() {
		List<String> bad = new ArrayList<String>();
		
		List<Permission> perms = instance.getPR().grabAll();
		
		for(int i = 0; i < perms.size(); i++)
			walkSingle(bad, perms.get(i));
		
		return bad;
	}
	
	private void walkSingle(List<String> bad, Permission cur) {
		if(cur.rank == SimpleRank.Undefined)
			bad.add(cur.fullPerm);
		
		for(int i = 0; i < cur.children.size(); i++)
			walkSingle(bad, cur.children.get(i));
	}
	
}
