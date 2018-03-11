package com.github.mrsnailman.memebot.api;

import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public interface DiscordCommandHandler {

  String getName();
  
  void process(MessageReceivedEvent event);

  String getHelp();

}
