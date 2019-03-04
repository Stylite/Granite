package bot.bricolo.granite.network;

import bot.bricolo.granite.AndesiteNode;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

class SocketHandler extends SimpleChannelInboundHandler<String> {
    private final AndesiteNode client;

    SocketHandler(AndesiteNode client) {
        this.client = client;
    }

    @Override
    public void channelRead0(ChannelHandlerContext ctx, String msg) {
        client.onMessage(msg);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        client.onNetworkError(cause);
        ctx.close();
    }
}
