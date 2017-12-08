import aiohttp
import asyncio
import discord
from discord.ext.commands import Bot
from discord.ext import commands
import getopt
import json
import os
import platform
import praw
import random
import sys
import websockets

class RedditCommander:
    def __init__(self, client, reddit):
        self.client = client
        self.reddit = reddit

    async def post(self, subredditStr='me_irl+dankmemes', numPosts=1, comment='This bot is made of copy pasted garbage code... Or was it?'):
        target = random.randint(0, numPosts)
        for i,submission in enumerate(self.reddit.subreddit(subredditStr).hot(limit=numPosts)):
            if i == target:
                await self.client.say(submission.url)
                await asyncio.sleep(3)
                await self.client.say(comment)
                break

class ConfigService:
    def __init__(self):
        f = open(os.path.abspath(os.path.join(os.path.dirname(__file__), "config", "config.json")), "r")
        self.config = json.loads(f.read())

client = Bot(description="A shitty bot with shitty shitposts", command_prefix="!", pm_help = True)
config = ConfigService().config
reddit = praw.Reddit(
        client_id=config["client_id"],
        client_secret=config["client_secret"],
        password=config["password"],
        user_agent=config["user_agent"], 
        username=config["username"]
        )

shitPostBot = RedditCommander(client, reddit)
@client.event
async def on_ready():
    print('Logged into Discord as '+client.user.name+' (ID:'+client.user.id+') | Connected to '+str(len(client.servers))+' servers | Connected to '+str(len(set(client.get_all_members())))+' users')
    print('--------')
    print('Current Discord.py Version: {} | Current Python Version: {}'.format(discord.__version__, platform.python_version()))
    print('--------')
    print('Use this link to invite {}:'.format(client.user.name))
    print('https://discordapp.com/oauth2/authorize?client_id={}&scope=bot&permissions=8'.format(client.user.id))
    print('--------')

@client.command()
async def badpost(*args):
    shitPostBot.post()

@client.command()
async def post(*args):
    shitPostBot.post('me_irl+prequelmemes+surrealmemes+dankmemes+deepfriedmemes+bonehurtingjuice+youdontsurf+wheredidthesodago', 100, 'This bot is made of hot noods I found on the floor')

@client.command()
async def shitpost(*args):
    shitPostBot.post('shittyrainbow6', 50, 'This bot is made of broken hitboxes')

@client.command()
async def pullup(*args):
    shitPostBot.post('wackytictacs+bertstrips+hmmm', 25, 'This bot is made of discarded dreams. Drink them, for they are yours.')

@client.command()
async def porn(*args):
    shitPostBot.post('cablemanagement+mechanical_keyboards+serverporn', 50, 'This bot is made of pure human nut')

@client.command()
async def saveme(*args):
    shitPostBot.post('wholesomememes+rarepuppers+awww', 50, "This bot is made of love")

@client.command()
async def zalgo(*args):
    shitPostBot.post('creepy', 25, 'Guardian, when you stare at the void... does it stare back?')
            

@client.command()
async def hello(*args):
    await client.say('https://i.redd.it/we18qzi65owz.jpg')

@client.command()
async def goodbye(*args):
    await client.say('https://i.imgur.com/QYKEqzn.png')

@client.command()
async def welcome(*args):
    await client.say('https://media.giphy.com/media/Qld1cd6a6QlWw/giphy.gif')

@client.command()
async def whatthejediwonttellyou(*args):
    await client.say("Did you ever hear the tragedy of Darth Plagueis The Wise? I thought not. It’s not a story the Jedi would tell you. It’s a Sith legend. Darth Plagueis was a Dark Lord of the Sith, so powerful and so wise he could use the Force to influence the midichlorians to create life… He had such a knowledge of the dark side that he could even keep the ones he cared about from dying. The dark side of the Force is a pathway to many abilities some consider to be unnatural. He became so powerful… the only thing he was afraid of was losing his power, which eventually, of course, he did. Unfortunately, he taught his apprentice everything he knew, then his apprentice killed him in his sleep. Ironic. He could save others from death, but not himself.")



#@client.command()
#async def help(*args):
#    await client.say("Fuck you :eggplant: :fork_and_knife: :yum: :sweat_drops:")


client.run(config['discord_token'])

