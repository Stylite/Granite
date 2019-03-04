package bot.bricolo.granite.network;

import bot.bricolo.granite.AndesiteNode;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.util.concurrent.CompletableFuture;

public class SocketClient {
    private final AndesiteNode node;
    private final NioEventLoopGroup group;

    public SocketClient(AndesiteNode node) {
        this.node = node;
        this.group = new NioEventLoopGroup();
    }

    public CompletableFuture<Channel> buildChannel() {
        CompletableFuture<Channel> completableFuture = new CompletableFuture<>();

        Bootstrap b = new Bootstrap();
        b.group(group)
                .channel(NioSocketChannel.class)
                .handler(new HeadersHandler(node.getPassword(), node.getUserId()))
                .handler(new SocketInitializer());

        // Start the connection attempt.
        ChannelFuture channelFuture = b.connect(node.getHost() + "/websocket", node.getPort());
        channelFuture.addListener((ChannelFutureListener) future -> completableFuture.complete(future.channel()));
        return completableFuture;
    }

    public void shutdown() {
        group.shutdownGracefully();
    }
}
