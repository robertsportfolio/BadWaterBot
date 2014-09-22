package com.badwater.bot.Game.gamecore.actions;

import com.badwater.bot.Game.gamecore.actors.AbstractActor;

/**
 * Created by Robert on 8/31/2014.
 */
public interface IAction {
	public void exec(AbstractActor source, AbstractActor target, int amount);

	public String getAlias();
}
