package com.github.mrsnailman.memebot;

import com.github.mrsnailman.memebot.config.AppConfig;

import javax.annotation.Resource;
import net.dean.jraw.RedditClient;
import net.dv8tion.jda.core.JDA;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.CommandLineRunner; 
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Import;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SpringBootConfiguration
@EnableAutoConfiguration
@Import(AppConfig.class)
public class CliRunner implements CommandLineRunner{

  @Resource
  private JDA jda;  
  @Resource
  private RedditClient redditClient;

  private static final Logger LOG = LoggerFactory.getLogger(CliRunner.class);

  public void run(String... args) {
    LOG.info(this.redditClient.subreddit("dankmemes").about().getName());
  }

  public static void main(String[] args) {
    SpringApplication.run(CliRunner.class, args);
  }

}
