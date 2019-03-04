package bot.bricolo.granite.network;

import bot.bricolo.granite.Version;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;
import io.netty.handler.codec.http.HttpResponse;

public class HeadersHandler extends ChannelOutboundHandlerAdapter {
    private final String password;
    private final String userId;

    HeadersHandler(String password, String userId) {
        this.password = password;
        this.userId = userId;
    }

    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
        if (msg instanceof HttpResponse) {
            HttpResponse httpResponse = ((HttpResponse) msg);
            httpResponse.headers().set("Authorization", password);
            httpResponse.headers().set("User-Id", userId);
            httpResponse.headers().set("User-Agent", "Granite v" + Version.VERSION + " (https://github.com/BricoloDuDimanche/Granite)");
        }

        super.write(ctx, msg, promise);
    }
}
