# Granite
[![Build Status](https://img.shields.io/travis/BricoloDuDimanche/Granite.svg?branch=master&style=flat-square&logo=travis)](https://travis-ci.org/BricoloDuDimanche/Granite)
[![Javadoc](https://img.shields.io/badge/Javadoc-555.svg?style=flat-square&logo=java)](https://granite.bricolo.me)
[![Latest GitHub tag](https://img.shields.io/github/tag-date/BricoloDuDimanche/Granite.svg?style=flat-square)](https://github.com/BricoloDuDimanche/Granite/releases)
[![License](https://img.shields.io/github/license/BricoloDuDimanche/Granite.svg?style=flat-square)](https://github.com/BricoloDuDimanche/Granite/blob/master/LICENSE)
[![Discord](https://img.shields.io/badge/chat-on%20Discord%20(%23granite)-7289DA.svg?style=flat-square)](https://discord.gg/V82UXC5)
[![Donate](https://img.shields.io/badge/donate-Patreon-F96854.svg?style=flat-square)](https://www.patreon.com/Bowser65)

A powerful, intuitive and easy to use client for [Andesite](https://github.com/natanbc/andesite-node) compatible with
[JDA 4](https://github.com/DV8FromTheWorld/JDA) and [Catnip](https://github.com/mewna/catnip)

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
 - [x] Implement all functions provided by Andesite
 - [ ] Queue management through Granite itself
 - [ ] Load balancing (based on nodes load)
 - [ ] Add region factor to load balancing (EU traffic to EU nodes, US traffic to US nodes)
 - [ ] Automatic failover if a node is available (preferably in region)
 - [ ] Automatically switch node when region is updated (if a better one is available)
 - [ ] SSL Support
