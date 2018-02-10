package me.nexadn.discord.servercopy.data;

import java.util.HashMap;

import sx.blah.discord.handle.obj.ICategory;
import sx.blah.discord.handle.obj.IChannel;
import sx.blah.discord.handle.obj.IGuild;
import sx.blah.discord.handle.obj.IRole;
import sx.blah.discord.handle.obj.IUser;
import sx.blah.discord.handle.obj.PermissionOverride;
import sx.blah.discord.util.RequestBuffer;
import sx.blah.discord.util.cache.LongMap;

/**
 * DTO for {@link IChannel}
 * 
 * @author NexAdn
 *
 */
public class ChannelData implements DataRecreator {

	private ICategory							category;
	private String								name;
	private HashMap<IRole, PermissionOverride>	roleOverrides;
	private String								topic;
	private HashMap<IUser, PermissionOverride>	userOverrides;

	public ChannelData(IChannel channel)
	{
		this.category = channel.getCategory();
		this.name = channel.getName();
		this.topic = channel.getTopic();

		// Store role and user with the corresponding roles/users
		this.roleOverrides = new HashMap<>();
		this.userOverrides = new HashMap<>();
		LongMap<PermissionOverride> roleO = channel.getRoleOverrides();
		LongMap<PermissionOverride> userO = channel.getUserOverrides();
		roleO.forEach( (long l, PermissionOverride o) -> {
			this.roleOverrides.put(channel.getGuild().getRoleByID(l), o);
		});
		userO.forEach( (long l, PermissionOverride o) -> {
			this.userOverrides.put(channel.getGuild().getUserByID(l), o);
		});
	}

	public void recreateOnGuild (IGuild guild)
	{
		IChannel channel = guild.createChannel(this.name);
		if (guild.getCategoriesByName(this.category.getName()).size() > 0)
			channel.changeCategory(guild.getCategoriesByName(this.category.getName()).get(0));

		channel.changeTopic(this.topic);

		// Role overrides
		for (IRole role : this.roleOverrides.keySet())
		{
			if (guild.getRolesByName(role.getName()).size() > 0)
			{
				RequestBuffer.request( () -> {
					channel.overrideRolePermissions(guild.getRolesByName(role.getName()).get(0),
							this.roleOverrides.get(role).allow(), this.roleOverrides.get(role).deny());
				});
			}
		}
		// User overrides
		for (IUser user : this.userOverrides.keySet())
		{
			channel.overrideUserPermissions(user, this.userOverrides.get(user).allow(),
					this.userOverrides.get(user).deny());
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
