package com.github.mrsnailman.memebot;

import com.github.mrsnailman.memebot.api.DiscordCommandHandler;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.stream.Collectors;

import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import net.dv8tion.jda.core.entities.MessageChannel;
import org.springframework.util.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Handles calling the command factory to resolve
 * the command to use
 */
public class CommandListener extends ListenerAdapter {

  private static final Logger LOG = LoggerFactory.getLogger(CommandListener.class);
  private Pattern commandPattern;
  private Map<String, DiscordCommandHandler> handlers;

  public CommandListener(String commandRegex, 
      List<DiscordCommandHandler> handlers) {
    Assert.notNull(commandRegex);
    Assert.notNull(handlers);
    this.handlers = handlers.stream().collect(
        Collectors.toMap(DiscordCommandHandler::getName, Function.identity()));
    try {
      this.commandPattern = Pattern.compile(commandRegex);
    } catch (Throwable t) {
      throw new RuntimeException("Unable to compile provided regex " + commandRegex) ;
    }
  }

  @Override
  public void onMessageReceived(MessageReceivedEvent event) {
    String content = event.getMessage().getContentRaw();
    if (this.commandPattern.matcher(content).find()) {
      String command = this.commandPattern.matcher(content).replaceFirst("");
      if (this.handlers.containsKey(command)) {
        this.handlers.get(command).process(event);
      } 
    }
  }
}
