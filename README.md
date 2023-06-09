# ForceAccessServer

近日，Minecraft 1.16.5 似乎出现了离线模式玩家多人游戏按钮被禁用、服务器内无法发送聊天消息的问题。  
通过阅读代码，大概可猜测出问题，首先我们来看看 Minecraft 是怎么工作的：

* Minecraft 在启动时会使用启动参数提供的 accessToken 请求服务器拉取配置 (YggdrasilSocialInteractionsService)
* 这个配置可以包含 `多人游戏是否可用`、`是否可以聊天`、`领域是否可用`、`被你屏蔽的玩家的UUID` 等
* 拉取成功就使用服务器提供的配置，拉取失败时会使用离线配置 (OfflineSocialInteractions)
* 离线模式下，以上提到的前三个配置项全部启用，没有被屏蔽的玩家。

(以上提到的类均来自 `com.mojang:authlib:2.1.28`)

根据最近玩家口口相传的经验，断网可以避免多人游戏按钮被禁用。

据此，我们可以推测，Mojang 的服务器推送了一个**三个功能全部禁用**的配置，让离线模式的玩家无法使用这些功能。

这就是这个mod诞生的原因。因为以上提到的这两个类都不是混淆的，理论上只要 authlib 版本正确就可以使用，即理论全版本通用。

由于新版 Forge 的 Mixin 范围有亿点点窄，故 Forge 版并不修改 authlib，而是修改 Minecraft 中获取配置的部分。

# 编译

```shell
./gradlew build
```
