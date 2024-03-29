# ForceAccessServer

解决多人游戏被禁用、无法发送聊天消息问题，单 Mod 支持 Forge & Fabric，理论全版本通用。

## authlib 2

近日，Minecraft 1.16.5 似乎出现了离线模式玩家多人游戏按钮被禁用、服务器内无法发送聊天消息的问题。  
通过阅读代码，大概可猜测出问题，首先我们来看看 Minecraft 是怎么工作的：

* Minecraft 在启动时会使用启动参数提供的 accessToken 请求服务器拉取配置 (YggdrasilSocialInteractionsService)
* 这个配置可以包含 `多人游戏是否可用`、`是否可以聊天`、`领域是否可用`、`被你屏蔽的玩家的UUID` 等
* 拉取成功就使用服务器提供的配置，拉取失败时会使用离线配置 (OfflineSocialInteractions)
* 离线模式下，以上提到的前三个配置项全部启用，没有被屏蔽的玩家。

(以上提到的类均来自 `com.mojang:authlib:2.1.28`)

根据最近玩家口口相传的经验，断网可以避免多人游戏按钮被禁用。

据此，我们可以推测，Mojang 的服务器推送了一个**三个功能全部禁用**的配置，让离线模式的玩家无法使用这些功能。

这就是这个mod诞生的原因。因为以上提到的这两个类都不是混淆的，理论上只要 authlib 版本正确就可以使用，即`理论全版本`通用。

~~由于新版 Forge 的 Mixin 范围有亿点点窄，故 Forge 版并不修改 authlib，而是修改 Minecraft 中获取配置的部分。~~

Forge 版 mod 不依赖 Mixin，作为 FML CoreMod 使用 [ASM Transformer](src/main/resources/transformer.fas.js) 修改 `net.minecraft.client.Minecraft` 的字节码，`理论1.13+`通用。

## authlib 3.3

从 1.18 开始使用 authlib 3.3，结构变了，未雨绸缪，所以我决定更新支持 authlib 3

分析的依赖是 `com.mojang:authlib:3.3.39`，  
这个依赖使用 java 17 编译，本 Mod 为了向下兼容难以引用该依赖，只能分模块编译最后再杂交，尽可能做到最大兼容。

跟 authlib 2 差不多的，在 `com.mojang.authlib.minecraft.UserApiService` 的 `properties`，可用的配置有
* SERVERS_ALLOWED
* REALMS_ALLOWED
* CHAT_ALLOWED
* TELEMETRY_ENABLED
* PROFANITY_FILTER_ENABLED

返回什么就支持什么。这个接口有一个匿名类实现的实例赋值到了 `UserApiService.OFFLINE`，有一个 `YggdrasilUserApiService` 实现。  
Fabric 版的方案是禁止 `YggdrasilUserApiService` 拉取配置，并将预定的配置 `UnlimitedSocialInteractions3.ALLOW` 注入进去避免 mojang 改 authlib 导致 Mod 失效。  
Forge 版的方案跟 authlib 2 的方案一致。

## authlib 3.18

从 1.19 开始使用 authlib 3.18，新增举报功能，  
就 `com.mojang.authlib.minecraft.UserApiService` 多了一点方法，其它基本和 authlib 3.3 一致。

## authlib 4

从 1.20 开始使用 authlib 4，~~等我有空再看。~~  
跟 authlib 3.18 一样。

# 编译

需使用 java 17 编译，编译后的类文件目标为 java 8
```shell
./gradlew build
```
