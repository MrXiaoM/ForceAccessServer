package top.mrxiaom.fas.mixin;

import com.mojang.authlib.exceptions.AuthenticationException;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.Shadow;
import top.mrxiaom.fas.FabricMod;

import java.lang.reflect.Field;

@Pseudo
@Mixin(targets = { "com.mojang.authlib.yggdrasil.YggdrasilUserApiService" })
public class MixinYggdrasilUserApiService {
    /**
     * @reason not allow to fetch properties
     * @author MrXiaoM
     * @throws AuthenticationException
     */
    @Overwrite
    private void fetchProperties() throws AuthenticationException {
        FabricMod.LOGGER.info("已阻止拉取配置");
        // 懒得导包，而且用java17编译包也导不进来，直接反射
        try {
            Object allow = Class.forName("top.mrxiaom.fas.UnlimitedSocialInteractions3").getDeclaredField("ALLOW").get(null);
            Field properties = this.getClass().getDeclaredField("properties");
            properties.setAccessible(true);
            properties.set(this, allow);
            FabricMod.LOGGER.info("成功更改配置");
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }
}
