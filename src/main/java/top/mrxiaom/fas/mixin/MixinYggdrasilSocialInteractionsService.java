package top.mrxiaom.fas.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Pseudo;

/**
 * 适用于 1.17 或以下的解决方案
 */
@Pseudo
@Mixin(targets = { "com.mojang.authlib.yggdrasil.YggdrasilSocialInteractionsService" })
public abstract class MixinYggdrasilSocialInteractionsService {

    /**
     * @author MrXiaoM
     * @reason modify
     */
    @Overwrite(remap = false)
    public boolean serversAllowed() {
        return true;
    }

    /**
     * @author MrXiaoM
     * @reason modify
     */
    @Overwrite(remap = false)
    public boolean realmsAllowed() {
        return true;
    }

    /**
     * @author MrXiaoM
     * @reason modify
     */
    @Overwrite(remap = false)
    public boolean chatAllowed() {
        return true;
    }
}
