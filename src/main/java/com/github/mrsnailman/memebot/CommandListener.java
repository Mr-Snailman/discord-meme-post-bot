package com.github.mrsnailman.memebot;

import com.github.mrsnailman.memebot.api.DiscordCommandHandler;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import net.dv8tion.jda.core.entities.MessageChannel;
import org.springframework.util.Assert;

/**
 * Handles calling the command factory to resolve
 * the command to use
 */
public class CommandListener extends ListenerAdapter {

  private Pattern commandPattern;
  private Map<String, DiscordCommandHandler> handlers;

  public CommandListener(String commandRegex, 
      List<DiscordCommandHandler> handlers) {
    Assert.notNull(commandPrefix);
    Assert.notNull(handlers);
    this.handlers = new HashMap();
    handlers.stream().collect(
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
 Matcher m = p.matcher("aaaaab");
 boolean b = m.matches();

    if (content.equals("!ping")) {
      MessageChannel channel = event.getChannel();
      channel.sendMessage("Pong!").queue(); // Important to call .queue() on the RestAction returned by sendMessage(...)
    }
  }
}
