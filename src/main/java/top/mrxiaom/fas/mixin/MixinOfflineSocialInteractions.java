package top.mrxiaom.fas.mixin;

import com.mojang.authlib.minecraft.OfflineSocialInteractions;
import com.mojang.authlib.yggdrasil.YggdrasilSocialInteractionsService;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Pseudo;

@Pseudo
@Mixin(OfflineSocialInteractions.class)
public abstract class MixinOfflineSocialInteractions {

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
