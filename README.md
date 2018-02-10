# Servercopy – A lightweight Discord server replication bot
This tool is designed to replicate the structure of one Discord server on another server (e.g. for backup-servers).

## Installation
* Requirements: Java 8 or higher [1]
* Download the current JAR file (see Releases)
* Create a new Discord application [2]
* Grab the token of your new app and create a file named `discord.properties` with the following contents and replace YOURTOKEN with your app's token:
```
token=YOURTOKEN
level=INFO
```
* Open the downloaded JAR file with an archive manager (e.g. 7Zip) and insert your discord.properties file
* Start the bot by executing the JAR file with java

## Compiling
* Requirements:
	* Java 8 SDK [3]
	* Apache Maven [4]
	* Git [5]
* Clone the repository: `git clone git://github.com/nexadn/servercopy.git`
* cd into the repository
* Create a Discord app (see Installation)
* Create an appropriate discord.properties file in src/main/resources (see Installation)
* Run `mvn clean package`
* The compiled JAR file is located in the target/ folder (do not use the original-servercopy.jar as it does not have all dependencies included)

## How to copy a Discord server
* Invite the bot to both servers (see [2] on how to create an invite link for a bot).
* Grant the bot admin privileges on both servers
* Grab the server's IDs by typing `!getid` in a text channel on each server
* The bot will respond to you privately in a private channel with the numeric IDs of each server
* In this private channel, type `!replicate ID1 ID2` to start the replication process (replace ID1 with the ID of the template server and ID2 with the ID of the empty server)
* The bot will respond you when the replication process starts and when it finishes. The process can take quite much time especially when you copy a server with a complex/big structure.

## Licensing
Servercopy is free software: you can redistribute it and/or modify it under
the terms of the GNU General Public License as published by the Free Software
Foundation, either version 3 of the License, or (at your option) any later
version.

This program is distributed in the hope that it will be useful, but WITHOUT
ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
details.

You should have received a copy of the GNU General Public License along with
this program. If not, see <http://www.gnu.org/licenses/>.

# Footnotes
* [1] Oracle Java: http://www.oracle.com/technetwork/java/javase/downloads/jre9-downloads-3848532.html
* [2] Creating a new Discord application: https://github.com/reactiflux/discord-irc/wiki/Creating-a-discord-bot-&-getting-a-token
* [3] Java 8 SDK: http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html
* [4] Apache Maven: https://maven.apache.org/
* [5] Git: https://git-scm.com/
