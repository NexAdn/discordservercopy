package me.nexadn.discord.servercopy.command;

import java.util.List;

import me.nexadn.discord.servercopy.Bot;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;

public class CommandGetId implements CommandExecutor {

	public void call (Bot bot, MessageReceivedEvent event, List<String> cmdline)
	{
		if (event.getGuild() != null)
		{
			bot.replyPrivate(event,
					"This guild's (" + event.getGuild().getName() + ") ID is:\n" + event.getGuild().getStringID());
		} else
		{
			bot.replyPrivate(event,
					"You have to send this message in a text channel of a Discord guild to retrieve an id.");
		}
	}

	public String getDescription ()
	{
		// TODO Auto-generated method stub
		return null;
	}

	public String getHelp ()
	{
		// TODO Auto-generated method stub
		return null;
	}

	public boolean match (List<String> commandline)
	{
		if (commandline.get(0).equalsIgnoreCase("!getID"))
			return true;
		return false;
	}

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
