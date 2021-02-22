package jonjar.amgn.registry;

import io.netty.buffer.Unpooled;
import jonjar.amgn.Amgn;
import jonjar.amgn.util.Excavate;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.network.ServerSidePacketRegistry;
import net.minecraft.client.MinecraftClient;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.packet.c2s.play.CustomPayloadC2SPacket;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;

public class ModPacket extends  BaseRegistry{

    private static final Identifier START_EXCAVATE_PACKET = new Identifier(Amgn.MODID, "start_excavate_packet");

    @Environment(EnvType.CLIENT)
    public static void sendExcavatePacket(BlockPos blockPos) {
        PacketByteBuf buf = new PacketByteBuf(Unpooled.buffer());
        buf.writeBlockPos(blockPos);
        MinecraftClient.getInstance().getNetworkHandler().getConnection().send(new CustomPayloadC2SPacket(START_EXCAVATE_PACKET, new PacketByteBuf(buf)));
    }
    @Override
    public void register() {
        ServerSidePacketRegistry.INSTANCE.register(START_EXCAVATE_PACKET, (packetContext, packetByteBuf) -> {
            BlockPos blockPos = packetByteBuf.readBlockPos();
            packetContext.getTaskQueue().execute(() -> {
                Excavate excavate = new Excavate(blockPos, packetContext.getPlayer());
                excavate.startExcavate();
            });
        });
    }
}
