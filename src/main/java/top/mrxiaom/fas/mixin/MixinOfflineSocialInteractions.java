package top.mrxiaom.fas.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Pseudo;

@Pseudo
@Mixin(targets = { "com.mojang.authlib.minecraft.OfflineSocialInteractions" })
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
