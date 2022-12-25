package h3xadecimal.liquidloader.hytplugin.modules;

import net.ccbluex.liquidbounce.LiquidBounce;
import net.ccbluex.liquidbounce.api.minecraft.client.entity.IEntityPlayerSP;
import net.ccbluex.liquidbounce.event.EventTarget;
import net.ccbluex.liquidbounce.event.UpdateEvent;
import net.ccbluex.liquidbounce.features.module.Module;
import net.ccbluex.liquidbounce.features.module.ModuleCategory;
import net.ccbluex.liquidbounce.features.module.ModuleInfo;
import net.ccbluex.liquidbounce.features.module.modules.combat.KillAura;
import net.ccbluex.liquidbounce.features.module.modules.combat.Velocity;
import net.ccbluex.liquidbounce.features.module.modules.movement.Speed;
import net.ccbluex.liquidbounce.utils.MinecraftInstance;
import net.ccbluex.liquidbounce.value.BoolValue;
import net.ccbluex.liquidbounce.value.FloatValue;
import net.ccbluex.liquidbounce.value.TextValue;

@ModuleInfo(name="PitAutoLeave", description="Run", category= ModuleCategory.MOVEMENT)
public class PitAutoLeave extends Module {
    private final FloatValue health = new FloatValue("Health", 5.0f, 0.0f, 20.0f);
    private final TextValue text = new TextValue("Text", "/lobby");
    private final BoolValue autoDisable = new BoolValue("AutoDisable", true);
    private boolean lmao;

    @EventTarget
    public final void onUpdate(UpdateEvent event) {
        IEntityPlayerSP iEntityPlayerSP = mc.getThePlayer();
        if (iEntityPlayerSP.getHealth() <= ((Number)this.health.get()).floatValue() && !this.lmao) {
            MinecraftInstance.mc.getThePlayer().sendChatMessage("[LiquidLoader]-Bye");
            MinecraftInstance.mc.getThePlayer().sendChatMessage(this.text.get());
            this.lmao = true;
        }
        IEntityPlayerSP iEntityPlayerSP2 = MinecraftInstance.mc.getThePlayer();
        if (iEntityPlayerSP2.getHealth() <= ((Number)this.health.get()).floatValue() && this.autoDisable.get()) {
            Module module = LiquidBounce.INSTANCE.getModuleManager().get(KillAura.class);
            KillAura killAura = (KillAura)module;
            Module module2 = LiquidBounce.INSTANCE.getModuleManager().get(Velocity.class);
            Velocity velocity = (Velocity)module2;
            Module module3 = LiquidBounce.INSTANCE.getModuleManager().get(Speed.class);
            Speed speed = (Speed)module3;
            killAura.setState(false);
            velocity.setState(false);
            speed.setState(false);
        }
        IEntityPlayerSP iEntityPlayerSP3 = MinecraftInstance.mc.getThePlayer();
        if (iEntityPlayerSP3.getHealth() >= ((Number)this.health.get()).floatValue()) {
            this.lmao = false;
        }
    }
}
