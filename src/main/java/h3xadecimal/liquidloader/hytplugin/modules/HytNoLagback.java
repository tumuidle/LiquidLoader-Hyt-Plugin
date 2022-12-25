package h3xadecimal.liquidloader.hytplugin.modules;

import net.ccbluex.liquidbounce.LiquidBounce;
import net.ccbluex.liquidbounce.api.minecraft.client.entity.IEntityPlayerSP;
import net.ccbluex.liquidbounce.event.EventTarget;
import net.ccbluex.liquidbounce.features.module.Module;
import net.ccbluex.liquidbounce.features.module.ModuleCategory;
import net.ccbluex.liquidbounce.features.module.ModuleInfo;
import net.ccbluex.liquidbounce.features.module.modules.combat.KillAura;
import net.ccbluex.liquidbounce.utils.MinecraftInstance;
import net.ccbluex.liquidbounce.value.ListValue;

@ModuleInfo(name="NoLagBack", description="Skid", category= ModuleCategory.EXPLOIT)
public class HytNoLagback extends Module {
    private final ListValue modeValue = new ListValue("Mode", new String[]{"AntiCheat", "AAC5"}, "AAC5");
    private int ticks;
    private int a;
    private int b;

    @Override
    public void onEnable() {
        this.ticks = 0;
    }

    @EventTarget
    public final void onUpdate() {
        String string = this.modeValue.get();
        int n = 0;
        String string2 = string;
        String string3 = string2.toLowerCase();
        switch (string3) {
            case "anticheat": {
                if (this.ticks > 1000) {
                    IEntityPlayerSP iEntityPlayerSP = MinecraftInstance.mc.getThePlayer();
                    if (iEntityPlayerSP.isOnLadder() && MinecraftInstance.mc.getGameSettings().getKeyBindJump().getPressed()) {
                        IEntityPlayerSP iEntityPlayerSP2 = MinecraftInstance.mc.getThePlayer();
                        iEntityPlayerSP2.setMotionY(0.11);
                    }
                }
                if (this.ticks > 2000) {
                    this.ticks = 0;
                    break;
                }
                n = this.ticks;
                this.ticks = n + 1;
                break;
            }
            case "aac5": {
                int n2;
                Module module = LiquidBounce.INSTANCE.getModuleManager().getModule(KillAura.class);
                KillAura Killaura = (KillAura)module;
                IEntityPlayerSP iEntityPlayerSP = MinecraftInstance.mc.getThePlayer();
                if (iEntityPlayerSP.getOnGround()) {
                    if (this.b == 0) {
                        n2 = this.b;
                        this.b = n2 + 1;
                    }
                } else {
                    this.b = 0;
                    if (this.a == 0) {
                        n2 = this.a;
                        this.a = n2 + 1;
                    }
                }
                if (this.ticks > 250) {
                    IEntityPlayerSP iEntityPlayerSP3 = MinecraftInstance.mc.getThePlayer();
                    if (iEntityPlayerSP3.isOnLadder() && MinecraftInstance.mc.getGameSettings().getKeyBindJump().getPressed()) {
                        IEntityPlayerSP iEntityPlayerSP4 = MinecraftInstance.mc.getThePlayer();
                        iEntityPlayerSP4.setMotionY(0.11);
                    }
                }
                if (this.ticks > 500) {
                    this.ticks = 0;
                    break;
                }
                n2 = this.ticks;
                this.ticks = n2 + 1;
            }
        }
    }

    @Override
    public String getTag() {
        return this.modeValue.get();
    }
}
