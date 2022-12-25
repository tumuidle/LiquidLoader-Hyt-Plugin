package h3xadecimal.liquidloader.hytplugin.modules;

import net.ccbluex.liquidbounce.api.minecraft.client.entity.IEntityPlayerSP;
import net.ccbluex.liquidbounce.api.minecraft.client.network.IINetHandlerPlayClient;
import net.ccbluex.liquidbounce.api.minecraft.network.IPacket;
import net.ccbluex.liquidbounce.api.minecraft.network.play.server.ISPacketPosLook;
import net.ccbluex.liquidbounce.event.EventTarget;
import net.ccbluex.liquidbounce.event.PacketEvent;
import net.ccbluex.liquidbounce.features.module.Module;
import net.ccbluex.liquidbounce.features.module.ModuleCategory;
import net.ccbluex.liquidbounce.features.module.ModuleInfo;
import net.ccbluex.liquidbounce.value.ListValue;

@ModuleInfo(name="HytVelocity", description="Heck", category= ModuleCategory.COMBAT)
public class HytVelocity extends Module {
    private boolean canCancel;
    private final ListValue modeValue = new ListValue("Mode", new String[]{"b"}, "b");

    @EventTarget
    public final void onPacket(PacketEvent event) {
        if (classProvider.isSPacketEntityVelocity(event.getPacket())) {
            event.cancelEvent();
            this.canCancel = true;
        }
        String string = (String)this.modeValue.get();
        boolean bl = false;
        String string2 = string;
        String string3 = string2.toLowerCase();
        switch (string3) {
            case "b": {
                if (!classProvider.isSPacketPlayerPosLook(event.getPacket()) || !this.canCancel) break;
                ISPacketPosLook packet = event.getPacket().asSPacketPosLook();
                event.cancelEvent();
                IINetHandlerPlayClient iINetHandlerPlayClient = mc.getNetHandler();
                float f = packet.getYaw();
                float f3 = packet.getPitch();
                IEntityPlayerSP iEntityPlayerSP = mc.getThePlayer();
                iINetHandlerPlayClient.addToSendQueue(classProvider.createCPacketPlayerLook(f, f3, iEntityPlayerSP.getOnGround()));
                this.canCancel = false;
            }
        }
    }
}
