# Granite
[![JitPack](https://jitpack.io/v/BricoloDuDimanche/Granite.svg)](https://jitpack.io/#BricoloDuDimanche/Granite)
[![Discord](https://img.shields.io/badge/chat-on%20Discord%20(%23granite)-7289DA.svg?style=flat-square)](https://discord.gg/2CkzJzM)
[![License](https://img.shields.io/github/license/BricoloDuDimanche/Granite.svg?style=flat-square)](https://github.com/BricoloDuDimanche/Granite/blob/master/LICENSE)
[![Donate](https://img.shields.io/badge/donate-Patreon-F96854.svg?style=flat-square)](https://www.patreon.com/Bowser65)

A powerful, intuitive and easy to use client for [Andesite](https://github.com/natanbc/andesite-node) & JDA 4

## Installation

### Gradle

Add the JitPack repository
```gradle
repositories {
    ...
    maven { url 'https://jitpack.io' }
}
```

And add the dependency
```gradle
dependencies {
    implementation 'com.github.BricoloDuDimanche:Granite:...'
}
```

### Maven

Add the JitPack repository
```xml
<repositories>
 	<repository>
 	  <id>jitpack.io</id>
 	  <url>https://jitpack.io</url>
 	</repository>
 </repositories>
```

And add the dependency
```xml
<dependency>
  <groupId>com.github.BricoloDuDimanche</groupId>
	<artifactId>Granite</artifactId>
	<version>...</version>
</dependency>
```

## Todos

 - [x] Make the client actually able to communicate with Andesite
 - [ ] Implement all functions provided by Andesite
 - [ ] Queue management through Granite itself
 - [ ] Load balancing (based on nodes load)
 - [ ] Add region factor to load balancing (EU traffic to EU nodes, US traffic to US nodes)
 - [ ] Automatic failover if a node is available (preferably in region)
 - [ ] Automatically switch node when region is updated (if a better one is available)
 - [ ] SSL Support
