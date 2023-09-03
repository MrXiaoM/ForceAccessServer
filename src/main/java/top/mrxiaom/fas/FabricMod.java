package top.mrxiaom.fas;

import net.fabricmc.api.ModInitializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FabricMod implements ModInitializer {
    public static final Logger LOGGER = LogManager.getLogger("FAS");
    @Override
    public void onInitialize() {
        LOGGER.info("ForceAccessServer");
        LOGGER.info("解决离线模式无法进入多人游戏、无法聊天问题。");
    }
}
