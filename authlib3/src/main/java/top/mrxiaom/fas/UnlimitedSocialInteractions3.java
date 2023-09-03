package top.mrxiaom.fas;

import com.google.common.collect.Sets;
import com.mojang.authlib.minecraft.TelemetrySession;
import com.mojang.authlib.minecraft.UserApiService;

import java.util.UUID;
import java.util.concurrent.Executor;

public class UnlimitedSocialInteractions3 implements UserApiService {
    private static final UserProperties ALLOW = new UserProperties(Sets.newHashSet(
            UserFlag.SERVERS_ALLOWED,
            UserFlag.REALMS_ALLOWED,
            UserFlag.CHAT_ALLOWED
    ));
    @Override
    public UserProperties properties() {
        return ALLOW;
    }

    @Override
    public boolean isBlockedPlayer(UUID playerID) {
        return false;
    }

    @Override
    public void refreshBlockList() {
    }

    @Override
    public TelemetrySession newTelemetrySession(Executor executor) {
        return TelemetrySession.DISABLED;
    }
}
