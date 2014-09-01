package com.badwater.bot.core.gamecore.actions;

import com.badwater.bot.core.gamecore.actors.AbstractActor;

/**
 * Created by Robert on 8/31/2014.
 */
public class LookAction implements IAction {
	@Override
	public void exec(AbstractActor source, AbstractActor target, int amount) {
		String[] info;
		if (!target.equals("")) {
			info = target.getInfo();
		}
		else if (target.equals("")) {
			info = source.getCurrentRoom().getInfo();
		}
	}

	@Override
	public String getAlias() {
		return "Look";
	}
}
