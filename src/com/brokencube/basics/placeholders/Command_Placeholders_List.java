package com.brokencube.basics.placeholders;

import java.util.ArrayList;
import java.util.List;

import com.brokencube.api.API;
import com.brokencube.api.Messages;
import com.brokencube.api.command.Command;
import com.brokencube.api.user.Console;
import com.brokencube.api.user.Executor;
import com.brokencube.api.user.User;

public class Command_Placeholders_List extends Command {

	public Command_Placeholders_List(API api) {
		super(api, "placeholders list", "Get a list of all the placeholder commands in use.", "placeholders.list", "/placeholders list");
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
		List<String> walked = walkCmds();
		if(walked.size() == 0) {
			e.sendMessage(Messages.success+"There are no more placeholder commands!");
			return;
		}
		for(int i = 0; i < walked.size(); i++) {
			e.sendMessage("&c"+walked.get(i));
		}
	}
	
	private List<String> walkCmds() {
		List<String> cmds = new ArrayList<String>();
		
		for(int i = 0; i < instance.getCR().getCommands().size(); i++)
			walkSingle(cmds, instance.getCR().getCommands().get(i));
		
		return cmds;
	}
	
	private void walkSingle(List<String> cmds, Command cur) {
		if(cur.getCommandString().equalsIgnoreCase(""))
			cmds.add(cur.getCommandString());
		
		for(int i = 0; i < cur.getChildren().size(); i++)
			walkSingle(cmds, cur.getChildren().get(i));
	}
	
}
