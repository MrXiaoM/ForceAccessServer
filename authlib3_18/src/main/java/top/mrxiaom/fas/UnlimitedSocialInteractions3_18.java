package top.mrxiaom.fas;

import com.google.common.collect.Sets;
import com.mojang.authlib.minecraft.TelemetrySession;
import com.mojang.authlib.minecraft.UserApiService;
import com.mojang.authlib.minecraft.report.AbuseReportLimits;
import com.mojang.authlib.yggdrasil.request.AbuseReportRequest;
import com.mojang.authlib.yggdrasil.response.KeyPairResponse;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.Executor;

public class UnlimitedSocialInteractions3_18 implements UserApiService {
    private static final UserProperties ALLOW = new UserProperties(Sets.newHashSet(
            UserFlag.SERVERS_ALLOWED,
            UserFlag.REALMS_ALLOWED,
            UserFlag.CHAT_ALLOWED
    ), Map.of());
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

    @Override
    public KeyPairResponse getKeyPair() {
        return null;
    }

    @Override
    public void reportAbuse(AbuseReportRequest request) {
    }

    @Override
    public boolean canSendReports() {
        return false;
    }

    @Override
    public AbuseReportLimits getAbuseReportLimits() {
        return AbuseReportLimits.DEFAULTS;
    }
}
