package me.nexadn.discord.servercopy.data;

import sx.blah.discord.handle.obj.IGuild;

/**
 * Interface for all classes that recreate a specific object on another guild
 * 
 * @author NexAdn
 *
 */
public interface DataRecreator {
	/**
	 * Recreate the stored data
	 * 
	 * @param guild
	 *            The {@link IGuild} where to recreate the data on.
	 */
	public void recreateOnGuild (IGuild guild);
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