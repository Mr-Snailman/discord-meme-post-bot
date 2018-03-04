package com.github.mrsnailman.memebot.config;
import com.github.mrsnailman.memebot.MyListener;

import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.AccountType;
import net.dean.jraw.RedditClient;
import net.dean.jraw.http.UserAgent;
import net.dean.jraw.http.NetworkAdapter;
import net.dean.jraw.http.OkHttpNetworkAdapter;
import net.dean.jraw.oauth.Credentials;
import net.dean.jraw.oauth.OAuthHelper;

import org.springframework.context.annotation.Bean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {


  @Bean
  public RedditClient redditClient(@Value("${memebot.reddit.user}") String username,
      @Value("${memebot.reddit.password}") String password,
      @Value("${memebot.reddit.api.id}") String clientId,
      @Value("${memebot.reddit.api.secret}") String clientSecret) {
      UserAgent ua = new UserAgent("bot", "com.github.mrsnailman.membot", "v0.1", "/u/" + username);
      Credentials credentials = Credentials.script(username, password, clientId, clientSecret);
      NetworkAdapter adapter = new OkHttpNetworkAdapter(ua);
      return OAuthHelper.automatic(adapter, credentials);

  }

  @Bean
  public JDA jda(@Value("${memebot.discord.token}") String token) throws Throwable {
    return new JDABuilder(AccountType.BOT).setToken(token).addEventListener(new MyListener()).buildBlocking();
  }
}
