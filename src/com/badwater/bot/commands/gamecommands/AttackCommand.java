package com.badwater.bot.commands.gamecommands;

import com.badwater.bot.Game.GameHandler;
import com.badwater.bot.Game.gamecore.actors.AbstractActor;
import com.badwater.bot.commands.Command;
import com.badwater.bot.core.BadwaterBot;
import com.badwater.bot.helpers.helperFuncs;
import org.pircbotx.hooks.events.MessageEvent;

import java.util.ArrayList;

/**
 * Created by Robert on 8/31/2014.
 */
public class AttackCommand implements Command<MessageEvent> {

	@Override
	public void exec(MessageEvent event) throws Exception {
		BadwaterBot b = (BadwaterBot) event.getBot();
		GameHandler gh = b.getGameHandler();
		String[] parseMsg = helperFuncs.toArgs(event.getMessage().substring(7).trim());
		ArrayList<AbstractActor> possibleTargets = gh.getGame().getRoom().getActors();
		//a little hack to make sure that something is in parse message
		if (parseMsg.length <= 1) {
			if (parseMsg[0].equals("")) {
				event.respond("You must select a target to attack.  Available Targets are: ");
				int targetIndex = 0;
				String response = "";
				for (AbstractActor a : possibleTargets) {
					response += "(" + targetIndex + ") :" + a.getName() + " ";
					targetIndex++;
					if ((targetIndex % 5 == 0) || targetIndex == possibleTargets.size()) {
						response = response.replace("\n", " ");
						event.respond(response);
						response = "";
					}
				}
			}
			else {

				AbstractActor target = possibleTargets.get(Integer.parseInt(parseMsg[0]));
				target.takeDamage(gh.getPlayer(), 5);
				gh.getGame().update();
			}

		}
	}

	@Override
	public String getAlias() {
		return "Attack";
	}

	@Override
	public ArrayList<String> getHelpList() {
		return null;
	}

	@Override
	public ArrayList<String> getNoteList() {
		return null;
	}

	@Override
	public String getTopicString() {
		return null;
	}

	@Override
	public boolean requiresAuthentication() {
		return false;
	}

	public ArrayList<String> getHelpString() {
		return null;
	}

	public void addHelpStrings() {

	}
}
