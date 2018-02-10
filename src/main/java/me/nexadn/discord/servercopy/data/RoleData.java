package me.nexadn.discord.servercopy.data;

import java.awt.Color;
import java.util.EnumSet;

import sx.blah.discord.handle.obj.IGuild;
import sx.blah.discord.handle.obj.IRole;
import sx.blah.discord.handle.obj.Permissions;

/**
 * DTO for {@link IRole}
 * 
 * @author NexAdn
 *
 */
public class RoleData implements DataRecreator {

	private Color					color;
	private String					name;
	private EnumSet<Permissions>	permissions;

	public RoleData(IRole role)
	{
		this.color = role.getColor();
		this.name = role.getName();
		this.permissions = role.getPermissions();
	}

	public void recreateOnGuild (IGuild guild)
	{
		IRole role = guild.createRole();
		role.changeColor(this.color);
		role.changeName(this.name);
		role.changePermissions(this.permissions);
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