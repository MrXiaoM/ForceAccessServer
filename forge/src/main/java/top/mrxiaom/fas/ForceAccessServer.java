package top.mrxiaom.fas;

import net.minecraftforge.fml.common.Mod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod("fas")
public class ForceAccessServer {
    public static final Logger LOGGER = LogManager.getLogger("FAS");
    public ForceAccessServer() {
        LOGGER.info("ForceAccessServer");
        LOGGER.info("解决离线模式无法进入多人游戏、无法聊天问题。");
    }
}
