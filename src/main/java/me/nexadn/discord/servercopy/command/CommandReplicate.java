package me.nexadn.discord.servercopy.command;

import java.util.List;

import me.nexadn.discord.servercopy.Bot;
import me.nexadn.discord.servercopy.data.GuildData;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;
import sx.blah.discord.handle.obj.IGuild;
import sx.blah.discord.handle.obj.Permissions;

public class CommandReplicate implements CommandExecutor {

	public void call (Bot bot, MessageReceivedEvent event, List<String> cmdline)
	{
		String g1 = cmdline.get(1);
		String g2 = cmdline.get(2);
		IGuild ig1, ig2;
		try
		{
			ig1 = bot.getDiscordClient().getGuildByID(Long.parseLong(g1));
			ig2 = bot.getDiscordClient().getGuildByID(Long.parseLong(g2));
		} catch (NumberFormatException nfe)
		{
			bot.replyPrivate(event, "You have to provide two numeric guild IDs to start the replication process!");
			return;
		}
		if (!bot.getDiscordClient().getOurUser().getPermissionsForGuild(ig1).contains(Permissions.ADMINISTRATOR)
				|| !bot.getDiscordClient().getOurUser().getPermissionsForGuild(ig2).contains(Permissions.ADMINISTRATOR))
		{
			bot.replyPrivate(event, "This bot needs administrator permissions on both guilds for replication.");
			return;
		}
		bot.replyPrivate(event, "Starting replication process. This may take some time.");
		GuildData gd = new GuildData(ig1);
		gd.recreateOnGuild(ig2);
		bot.replyPrivate(event, "Replication process finished.");
	}

	public String getDescription ()
	{
		return null;
	}

	public String getHelp ()
	{
		return null;
	}

	public boolean match (List<String> commandline)
	{
		if (commandline.size() == 3 && commandline.get(0).equalsIgnoreCase("!replicate"))
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
