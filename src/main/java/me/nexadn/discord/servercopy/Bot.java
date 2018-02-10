package me.nexadn.discord.servercopy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import me.nexadn.discord.servercopy.command.CommandExecutor;
import me.nexadn.discord.servercopy.command.CommandGetId;
import me.nexadn.discord.servercopy.command.CommandReplicate;
import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.api.events.EventSubscriber;
import sx.blah.discord.handle.impl.events.ReadyEvent;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;
import sx.blah.discord.handle.impl.events.guild.member.UserJoinEvent;
import sx.blah.discord.handle.impl.obj.User;
import sx.blah.discord.handle.obj.IChannel;
import sx.blah.discord.handle.obj.IUser;
import sx.blah.discord.util.RequestBuffer;

public class Bot {

	protected IDiscordClient				client;
	protected Logger						logger;
	protected Collection<CommandExecutor>	commands;

	/**
	 * Constructor for Test cases, do nothing
	 */
	protected Bot()
	{
	}

	/**
	 * Create and initialize a Bot instance registering listeners and commands
	 * 
	 * @param logger
	 *            The parent logger to use
	 * @param client
	 */
	public Bot(Logger logger, IDiscordClient client)
	{
		this.logger = Logger.getLogger("Servercopy");
		if (logger != null)
			this.logger.setParent(logger);

		this.client = client;
		this.commands = new ArrayList<CommandExecutor>();

		this.log(Level.INFO, "Registering event listeners.");
		this.client.getDispatcher().registerListener(this);
		this.registerCommands();
	}

	/**
	 * Call the entered command
	 * 
	 * @param args
	 *            The command line split up by " "
	 * @param event
	 *            The fired event
	 */
	private void fetchCommands (String args, MessageReceivedEvent event)
	{
		List<String> cmdline = Arrays.asList(args.split(" "));
		for (CommandExecutor executor : this.commands)
		{
			if (executor.match(cmdline))
			{
				this.log(Level.INFO, event.getAuthor().getName() + " issued command: " + args);
				executor.call(this, event, cmdline);
			}
		}
	}

	public IDiscordClient getDiscordClient ()
	{
		return this.client;
	}

	/**
	 * Get a user by its snowflake ID
	 * 
	 * @param id
	 *            The snowflake ID of the desired user
	 * @return {@link IUser}, normally a {@link User} instance, null if no user
	 *         could be found
	 */
	public IUser getUser (long id)
	{
		return this.client.getUserByID(id);
	}

	/**
	 * Log messages to console
	 * 
	 * @param level
	 * @param message
	 */
	public void log (Level level, String message)
	{
		this.logger.log(level, message);
	}

	/**
	 * Fired when a message is received
	 * 
	 * @param event
	 */
	@EventSubscriber
	public void onMessage (MessageReceivedEvent event)
	{
		this.fetchCommands(event.getMessage().getContent(), event);
	}

	/**
	 * Fired when the Bot is ready
	 * 
	 * @param event
	 */
	@EventSubscriber
	public void onReady (ReadyEvent event)
	{
		this.log(Level.INFO, "Bot is ready.");
	}

	@EventSubscriber
	public void onUserJoin (UserJoinEvent event)
	{
		this.log(Level.INFO, "User " + event.getUser().getDisplayName(event.getGuild()) + " joined the guild "
				+ event.getGuild().getStringID() + ".");
		this.sendPrivateMessage(event.getUser(), "Willkommen auf dem Server " + event.getUser().getName() + "!");
	}

	/**
	 * Register all {@link CommandExecutor}s
	 * 
	 * <p>
	 * TODO: Add all to this.commands
	 */
	protected void registerCommands ()
	{
		this.commands.clear();
		this.commands.add(new CommandGetId());
		this.commands.add(new CommandReplicate());

	}

	/**
	 * Reply to a message received by a MessageReceivedEvent in the channel
	 * 
	 * @param event
	 *            The event
	 * @param answer
	 *            The answer
	 */
	public void reply (MessageReceivedEvent event, String answer)
	{
		RequestBuffer.request( () -> {
			event.getChannel().sendMessage(event.getAuthor() + " – " + answer);
		});
	}

	/**
	 * Reply to a message received by a MessageReceivedEvent in a private channel
	 * 
	 * @param event
	 *            The event
	 * @param answer
	 *            The answer
	 */
	public void replyPrivate (MessageReceivedEvent event, String answer)
	{
		RequestBuffer.request( () -> {
			event.getAuthor().getOrCreatePMChannel().sendMessage(answer);
		});
	}

	/**
	 * Send a message to a channel
	 * 
	 * @param channel
	 *            The {@link IChannel} to send the message t
	 * @param message
	 *            The message
	 */
	public void sendMessageToChannel (IChannel channel, String message)
	{
		RequestBuffer.request( () -> {
			channel.sendMessage(message);
		});
	}

	/**
	 * Send a message in a private channel to a user
	 * 
	 * @param target
	 *            The target {@link IUser}
	 * @param message
	 *            The message
	 */
	public void sendPrivateMessage (IUser target, String message)
	{
		RequestBuffer.request( () -> {
			target.getOrCreatePMChannel().sendMessage(message);
		});
	}

	/**
	 * Start this Bot by logging in
	 */
	public void start ()
	{
		this.log(Level.INFO, "Logging in.");
		this.client.login();
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
