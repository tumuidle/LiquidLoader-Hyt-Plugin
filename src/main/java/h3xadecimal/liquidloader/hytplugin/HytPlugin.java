package h3xadecimal.liquidloader.hytplugin;

import cn.afternode.liquidloader.Plugin;
import h3xadecimal.liquidloader.hytplugin.modules.*;
import net.ccbluex.liquidbounce.features.module.ModuleManager;

public class HytPlugin extends Plugin {
    @Override
    public void registerModules(ModuleManager mm) {
        mm.registerModules(HytVelocity.class, HytNoLagback.class, PitAutoLeave.class);
    }
}
