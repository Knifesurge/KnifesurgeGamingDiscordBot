package com.knifesurge.knifesurgegaming;

import javax.security.auth.login.LoginException;

import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.entities.Game;
import net.dv8tion.jda.core.entities.Game.GameType;
import net.dv8tion.jda.core.managers.Presence;

public class KnifesurgeGamingDiscordBot
{

	private static JDA jda;
	private static String BOT_TOKEN;
	
	public static void main(String[] args)
	{
		try
		{
			BOT_TOKEN = args[0];
			jda = new JDABuilder(AccountType.BOT).setToken(BOT_TOKEN).buildBlocking();
			jda.addEventListener(new KnifesurgeGamingListener());
			Presence game = jda.getPresence();
			game.setGame(Game.of(GameType.DEFAULT, "!!help"));
		} catch(InterruptedException | LoginException except)
		{
			except.printStackTrace();
		}
	}

	public static JDA getJDA()
	{
		return jda;
	}
	
}
