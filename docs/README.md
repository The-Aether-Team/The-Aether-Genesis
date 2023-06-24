# The Aether: Genesis

*Work in progress...*

## :heart: Support The Aether Team

[![Patreon](https://img.shields.io/endpoint.svg?url=https%3A%2F%2Fshieldsio-patreon.vercel.app%2Fapi%3Fusername%3DTheAetherTeam%26type%3Dpatrons&style=flat-square&logoColor=white)](https://patreon.com/TheAetherTeam)
[![Discord](https://img.shields.io/discord/118816101936267265.svg?label=discord&logoColor=FFFFFF&logo=discord&color=7289DA&style=flat-square)](https://discord.gg/aethermod)
[![Twitter](https://img.shields.io/badge/twitter-@DevAether-lightgrey?style=flat-square&logo=twitter&color=1DA1F2&logoColor=white)](https://twitter.com/DevAether)
[![Mastodon](https://img.shields.io/mastodon/follow/110581810287361848?domain=https%3A%2F%2Fmastodon.gamedev.place%2F&style=flat-square&logo=mastodon&logoColor=white&label=mastodon&color=858AFA
)](https://mastodon.gamedev.place/@DevAether)
[![Facebook](https://img.shields.io/badge/facebook-AetherMod-blue?logo=facebook&style=flat-square&color=1877F2&logoColor=white)](https://www.facebook.com/AetherMod)
[![YouTube](https://img.shields.io/badge/youtube-@DevAether-blue?color=FF0000&label=youtube&logo=youtube&style=flat-square)](https://www.youtube.com/@DevAether)
[![Twitch](https://img.shields.io/twitch/status/theaetherteam?logo=twitch&style=flat-square&logoColor=white)](https://www.twitch.tv/theaetherteam)
[![Reddit](https://img.shields.io/reddit/subreddit-subscribers/TheAether?color=FF4500&label=reddit&logo=reddit&style=flat-square&logoColor=white)](https://www.reddit.com/r/TheAether/)

If you enjoy our work, [please consider making a pledge](https://patreon.com/TheAetherTeam) today to help fund development. Every pledge goes directly into our development process and services, enabling us to continue making the Minecraft mods you know and love.

You can also support The Aether Team's projects by telling your friends, joining our Discord server, and sharing our progress and announcements on social media. Every bit helps!

If you're interested in sponsoring The Aether: Genesis or The Aether Team, please [contact Oz#1986 on the Aether Community Discord](https://discord.gg/aethermod).

## :package: Download the latest releases
[![Modloader: Forge](https://img.shields.io/badge/mod%20loader-forge-CC974D?style=flat-square)](https://files.minecraftforge.net/net/minecraftforge/forge/)
[![CircleCI](https://circleci.com/gh/The-Aether-Team/The-Aether-Genesis/tree/1.19.4-develop.svg?style=shield)](https://app.circleci.com/pipelines/github/The-Aether-Team/The-Aether-Genesis?branch=1.19.4-develop)
### Release builds
The Aether: Genesis has no stable release builds for the latest version of Minecraft just yet, but this mod is in active development so keep an eye out for its eventual release.

### Bleeding edge builds
If you’re feeling a bit more adventurous or wish to help test the in-development versions, we provide **bleeding edge builds** which are produced on [CircleCI](https://app.circleci.com/pipelines/github/The-Aether-Team/The-Aether-Genesis). These builds are created for every new commit and contain the latest available code. We do not recommend users treat these builds as releases, as they are unfinished and may contain serious issues. If you wish to download these builds, check out [this guide](https://github.com/The-Aether-Team/The-Aether/wiki/CircleCI-Guide).

### Packages
To install this mod through GitHub Packages in Gradle for development, you will need the [Gradle Github Packages Plugin](https://github.com/0ffz/gpr-for-gradle). To use it, make sure you have access to the Gradle plugins maven and the plugin as a buildscript dependency:

<details>
<summary> Buildscript Code</summary>

```
buildscript {
  repositories {
    ...
    maven {
        name 'Gradle'
        url "https://plugins.gradle.org/m2/"
    }
  }
  dependencies {
    ...
    classpath group: 'io.github.0ffz', name: 'gpr-for-gradle', version: '1.+', changing: true
  }
}
...
apply plugin: 'io.github.0ffz.github-packages'
```

</details>

Then you need to specify the package you want to use in your repository:

<details>
<summary> Repositories Code</summary>

```
repositories {
  ...
  maven githubPackage.invoke("The-Aether-Team/The-Aether-Genesis")
}
```

</details>

Then load it through your dependencies, with `project.genesis_version` specified in the `gradle.properties`:

<details>
<summary> Dependencies Code</summary>

```
dependencies {
  ...
  compileOnly "com.aetherteam.genesis:aether_genesis:${project.genesis_version}"
  runtimeOnly fg.deobf("com.aetherteam.genesis:aether_genesis:${project.genesis_version}")
  ...
}
```

</details>

## :bug: Report bugs or other issues
If you're running into bugs or other problems, feel free to open an issue on our [issue tracker](https://github.com/The-Aether-Team/The-Aether-Genesis/issues). When doing so, make sure to use one of the provided templates and fill out all the requested information. Make sure to keep your issue's description clear and concise. Your issue's title should also be easy to digest, giving our developers and reporters a good idea of what's wrong without including too many details. Failure to follow any of the above may result in your issue being closed.

## :wrench: Contribute to the project
Looking to contribute to the project? We ask that you read over our [Contributor's Guide](https://github.com/The-Aether-Team/The-Aether-Genesis/blob/1.19.4-develop/docs/CONTRIBUTING.md) for more details as well as our [Contributor License Agreement (CLA)](https://github.com/The-Aether-Team/The-Aether-Genesis/blob/1.19.4-develop/docs/AGREEMENT.md) before getting started.

Not sure what to help with? Take a look at our issue tracker for some ideas! [Here's a quick link](https://github.com/The-Aether-Team/The-Aether-Genesis/labels/status%2Fhelp-wanted) which shows all the currently open issues that we'd love some help on.

## :scroll: License information
[![Asset license (Unlicensed)](https://img.shields.io/badge/assets%20license-All%20Rights%20Reserved-red.svg?style=flat-square)](https://en.wikipedia.org/wiki/All_rights_reserved)
[![Code license (LGPL v3.0)](https://img.shields.io/badge/code%20license-LGPL%20v3.0-green.svg?style=flat-square)](https://github.com/The-Aether-Team/The-Aether-Genesis/blob/1.19.4-develop/LICENSE.txt)

If you're wanting to create a gameplay video/review, extension or addon, parody, or any other fan work of your own for The Aether: Genesis, go for it! We love seeing the content our community creates, and we hope to make it as welcoming as possible for everyone. We ask however that you please don't advertise using our brand (our specific logo assets, team name, official social media posts).

If you are thinking about using The Aether: Genesis' project code or assets, please note our licensing. **All assets of The Aether: Genesis are unlicensed and all rights are reserved to them by The Aether Team and their respective authors.** The source code of The Aether: Genesis mod is under the LGPL v3.0 license.

If you have a reason that you wish to use our brand or any unlicensed material, please [get in contact with Oz#1986 on the Aether Community Discord](https://discord.gg/aethermod) for details.

## :star2: Special mentions
### :speech_balloon: Translations

*Work in progress...*

### :hammer: Contributions
All of our code contribution credits can be found [here](https://github.com/The-Aether-Team/The-Aether-Genesis/blob/1.19.4-develop/docs/CREDITS.txt). If you contributed to the project and do not see your name, please contact us.
