package top.mrxiaom.fas.mixin;

import com.mojang.authlib.minecraft.SocialInteractionsService;
import com.mojang.authlib.yggdrasil.YggdrasilAuthenticationService;
import net.minecraft.client.Minecraft;
import net.minecraft.client.main.GameConfig;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import top.mrxiaom.fas.UnlimitedSocialInteractions;

@Mixin(Minecraft.class)
public class MixinMinecraft {
    /**
     * @author MrXiaoM
     * @reason modify
     */
    @Overwrite
    private SocialInteractionsService createSocialInteractions(YggdrasilAuthenticationService yggdrasilAuthenticationService, GameConfig gameConfig) {
        return new UnlimitedSocialInteractions();
    }
}
