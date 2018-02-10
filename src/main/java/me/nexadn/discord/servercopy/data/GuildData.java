package me.nexadn.discord.servercopy.data;

import java.util.List;

import sx.blah.discord.handle.obj.ICategory;
import sx.blah.discord.handle.obj.IChannel;
import sx.blah.discord.handle.obj.IGuild;
import sx.blah.discord.handle.obj.IRole;
import sx.blah.discord.handle.obj.IUser;
import sx.blah.discord.handle.obj.IVoiceChannel;
import sx.blah.discord.util.Ban;
import sx.blah.discord.util.RequestBuffer;

/**
 * DTO for {@link IGuild}
 * 
 * @author NexAdn
 *
 */
public class GuildData implements DataRecreator {

	private List<Ban>			bans;
	private List<ICategory>		categories;
	private List<IChannel>		channels;		// Text only
	private List<IRole>			roles;
	private List<IUser>			users;			// TODO
	private List<IVoiceChannel>	voiceChannels;

	public GuildData(IGuild guild)
	{
		this.bans = guild.getBans();
		this.categories = guild.getCategories();
		this.channels = guild.getChannels();
		this.roles = guild.getRoles();
		this.users = guild.getUsers();
		this.voiceChannels = guild.getVoiceChannels();
	}

	public void recreateOnGuild (IGuild guild)
	{
		for (Ban ban : this.bans)
		{
			RequestBuffer.request( () -> {
				guild.banUser(ban.getUser());
			});
		}

		for (IRole role : this.roles)
		{
			RequestBuffer.request( () -> {
				new RoleData(role).recreateOnGuild(guild);
			});
		}

		for (ICategory category : this.categories)
		{
			RequestBuffer.request( () -> {
				guild.createCategory(category.getName());
			});
		}

		// Prevent channels from getting created before the categories have been
		// replicated
		while (RequestBuffer.getIncompleteRequestCount() > 0)
		{
			try
			{
				Thread.sleep(1000);
			} catch (InterruptedException e)
			{
				continue;
			}
		}

		for (IChannel channel : this.channels)
		{
			RequestBuffer.request( () -> {
				new ChannelData(channel).recreateOnGuild(guild);
			});
		}

		for (IVoiceChannel channel : this.voiceChannels)
		{
			RequestBuffer.request( () -> {
				new VoiceChannelData(channel).recreateOnGuild(guild);
			});
		}
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
