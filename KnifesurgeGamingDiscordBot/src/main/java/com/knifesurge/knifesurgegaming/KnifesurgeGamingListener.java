package com.knifesurge.knifesurgegaming;

import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.events.Event;
import net.dv8tion.jda.core.events.guild.GuildJoinEvent;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.EventListener;

public class KnifesurgeGamingListener implements EventListener
{

	public static final String PRECURSOR = "!!";
	
	private static Message msg;
	private static Guild currentGuild;
	
	private static String prevMsgID = "";
	private static String currMsgID;
	
	public static void sendMessage(MessageReceivedEvent e, String msg)
	{
		e.getChannel().sendMessage(msg).queue();
	}
	
	@Override
	public void onEvent(Event event)
	{
		if (event instanceof GuildJoinEvent)
		{
			GuildJoinEvent gje = (GuildJoinEvent) event;
			System.out.println("GuildJoinEvent fired!");
		}
		if (event instanceof MessageReceivedEvent)
		{
			MessageReceivedEvent e = (MessageReceivedEvent) event;
			if(e.getAuthor().isBot()) return;	// Don't let other bots trigger commands
			
			try
			{
				msg = e.getMessage();
				currMsgID = e.getMessageId();
				if (!prevMsgID.equals(currMsgID))	// If the messages are not the same messages
				{
					String rawMsg = e.getMessage().getContentRaw();
					System.out.println(e.getAuthor().getName() + ": " + rawMsg);
					if(rawMsg.startsWith(PRECURSOR + "hello"))
					{
						sendMessage(e, e.getAuthor().getAsMention() + " Why hello there!");
					}
				}
			} catch (Exception exception)
			{
				System.out.println("Exception caught!");
				System.err.println(exception.getMessage());
				exception.printStackTrace();
			}
		}
	}
	
}
