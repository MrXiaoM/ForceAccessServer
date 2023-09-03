package top.mrxiaom.fas;

import com.mojang.authlib.minecraft.SocialInteractionsService;

import java.util.UUID;

public class UnlimitedSocialInteractions implements SocialInteractionsService {
    @Override
    public boolean serversAllowed() {
        return true;
    }

    @Override
    public boolean realmsAllowed() {
        return true;
    }

    @Override
    public boolean chatAllowed() {
        return true;
    }

    @Override
    public boolean isBlockedPlayer(UUID playerID) {
        return false;
    }
}
