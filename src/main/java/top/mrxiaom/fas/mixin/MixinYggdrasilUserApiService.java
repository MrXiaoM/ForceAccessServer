package top.mrxiaom.fas.mixin;

import com.mojang.authlib.exceptions.AuthenticationException;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.Shadow;
import top.mrxiaom.fas.FabricMod;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * 适用于 1.18+ 的解决方案
 */
@Pseudo
@Mixin(targets = { "com.mojang.authlib.yggdrasil.YggdrasilUserApiService" })
public class MixinYggdrasilUserApiService {
    /**
     * @reason not allow to fetch properties
     * @author MrXiaoM
     * @throws AuthenticationException
     */
    @Overwrite(remap = false)
    private void fetchProperties() throws AuthenticationException {
        FabricMod.LOGGER.info("已阻止拉取配置");
        // 懒得导包，而且用java17编译包也导不进来，直接反射
        try {
            Field properties = this.getClass().getDeclaredField("properties");
            properties.setAccessible(true);
            boolean authlib318 = false;
            for (Method method : properties.get(this).getClass().getDeclaredMethods()) {
                if (method.getName().equals("reportAbuse")) {
                    authlib318 = true;
                    break;
                }
            }
            Object allow = Class.forName("top.mrxiaom.fas.UnlimitedSocialInteractions3_" + (authlib318 ? "18" : "3")).getDeclaredField("ALLOW").get(null);
            properties.set(this, allow);
            FabricMod.LOGGER.info("成功更改配置");
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }
}
