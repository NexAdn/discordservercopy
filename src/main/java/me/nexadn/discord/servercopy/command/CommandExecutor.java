package me.nexadn.discord.servercopy.command;

import java.util.List;

import me.nexadn.discord.servercopy.Bot;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;

/**
 * Basic interface for classes implementing command handling functionalities
 * 
 * @author NexAdn
 */
public interface CommandExecutor {
	/**
	 * Call a command with the given command line
	 * 
	 * @param bot
	 *            The calling {@link Bot} instance
	 * @param commandSender
	 *            The calling {@link CommandSender}
	 * @param event
	 *            The related {@link MessageReceivedEvent} or null if there is none
	 * @param cmdline
	 *            The command line
	 */
	void call (Bot bot, MessageReceivedEvent event, List<String> cmdline);

	/**
	 * Get a short command description
	 */
	String getDescription ();

	/**
	 * Get a short syntax help message
	 */
	String getHelp ();

	/**
	 * Check wheter the given command line matches this command
	 * 
	 * @param commandline
	 *            The command line to be checked
	 * @return true, if the given command line matches; false, if not
	 */
	boolean match (List<String> commandline);
}

/*
 * Copyright (C) 2018 Adrian Schollmeyer
 * 
 * This file is part of Servercopy.
 * 
 * Servercopy is free software: you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later
 * version.
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU General Public License along with
 * this program. If not, see <http://www.gnu.org/licenses/>.
 */
