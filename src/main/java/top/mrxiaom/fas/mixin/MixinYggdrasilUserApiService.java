package top.mrxiaom.fas.mixin;

import com.mojang.authlib.exceptions.AuthenticationException;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import top.mrxiaom.fas.FabricMod;

@Mixin(targets = { "com.mojang.authlib.yggdrasil.YggdrasilUserApiService" })
public class MixinYggdrasilUserApiService {
    /**
     * @reason not allow to fetch properties
     * @author MrXiaoM
     * @throws AuthenticationException
     */
    @Overwrite
    private void fetchProperties() throws AuthenticationException {
        FabricMod.LOGGER.info("已阻止拉取配置，默认使用离线配置");
    }
}
