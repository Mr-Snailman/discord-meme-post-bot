package com.github.mrsnailman.memebot.commands;

import com.github.mrsnailman.memebot.api.DiscordCommandHandler;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PingPong implements DiscordCommandHandler {
  
  private static final Logger LOG = LoggerFactory.getLogger(PingPong.class);

  public String getName() {
    return "ping";
  }

  public void process(MessageReceivedEvent event) {
    event.getChannel().sendMessage("Pong!").queue();
  }

  public String getHelp() {
    return "Test your connection to the bot";
  }
}
