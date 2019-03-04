package bot.bricolo.granite.network;

import bot.bricolo.granite.AndesiteNode;
import bot.bricolo.granite.Version;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;
import io.netty.handler.codec.http.HttpResponse;

public class HeadersHandler extends ChannelOutboundHandlerAdapter {
    private final AndesiteNode client;

    public HeadersHandler(AndesiteNode client) {
        this.client = client;
    }

    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
        if (msg instanceof HttpResponse) {
            HttpResponse httpResponse = ((HttpResponse) msg);
            if (client.connectionId != null) {
                httpResponse.headers().set("Andesite-Resume-Id", client.connectionId);
            }

            httpResponse.headers().set("Authorization", client.getPassword());
            httpResponse.headers().set("User-Id", client.getUserId());
            httpResponse.headers().set("User-Agent", "Granite v" + Version.VERSION + " (https://github.com/BricoloDuDimanche/Granite)");
        }

        super.write(ctx, msg, promise);
    }
}
