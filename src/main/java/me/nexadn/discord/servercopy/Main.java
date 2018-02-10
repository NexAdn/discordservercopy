package me.nexadn.discord.servercopy;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import sx.blah.discord.api.ClientBuilder;
import sx.blah.discord.api.IDiscordClient;

/**
 * 
 * @author NexAdn
 *
 */
public class Main {
	/**
	 * Create a new {@link IDiscordClient}
	 * 
	 * @param token
	 *            Access token
	 * @param login
	 *            Whether to log the client in
	 * @return an instance of a Discord client
	 */
	public static IDiscordClient createClient (String token, boolean login)
	{
		ClientBuilder clientBuilder = new ClientBuilder();
		clientBuilder.withToken(token);
		try
		{
			if (login)
			{
				return clientBuilder.login();
			} else
			{
				return clientBuilder.build();
			}
		} catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}

	public static void main (String[] args)
	{
		String token = "";
		String loglevel = "";

		ClassLoader classLoader = ClassLoader.getSystemClassLoader();
		try
		{
			InputStream inputStream = classLoader.getResourceAsStream("discord.properties");
			Properties properties = new Properties();
			properties.load(inputStream);
			token = properties.getProperty("token");
			loglevel = properties.getProperty("loglevel");
		} catch (FileNotFoundException e)
		{
			e.printStackTrace();
			if (token == "")
				token = "00000000000000000000000000000000000000000000000000000000000";
			if (loglevel == "")
				loglevel = Level.INFO.toString();
		} catch (IOException e)
		{
			e.printStackTrace();
			if (token == "")
				token = "00000000000000000000000000000000000000000000000000000000000";
			if (loglevel == "")
				loglevel = Level.INFO.toString();
		}

		Logger.getGlobal().setLevel(Level.parse(loglevel));

		IDiscordClient discordClient = createClient(token, false);
		Bot bot = new Bot(Logger.getGlobal(), discordClient);
		bot.start();
		/*
		 * Thread botThread = new Thread( () -> { bot.start(); });
		 * 
		 * try { botThread.join(); } catch (InterruptedException e) {
		 * botThread.interrupt(); e.printStackTrace(); }
		 */
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
