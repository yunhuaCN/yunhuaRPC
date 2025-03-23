package Client.netty.nettyInitializer;

import Client.netty.handler.NettyClientHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.serialization.ClassResolver;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;
import org.checkerframework.common.value.qual.IntRangeFromGTENegativeOne;

/**
 * 用于配置netty对消息的处理机制
 */
public class NettyClientInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline pipeline = socketChannel.pipeline();
        //消息格式【长度】【消息体】,解决沾包问题
        pipeline.addLast(
                new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE,0,4,0,4)
        );

        //计算当前带发送消息的长度，写入到前4个字节中
        pipeline.addLast(new LengthFieldPrepender(4));

        //编码器
        //使用Java序列化方式，netty的自带的编码解码支持传输这种结构
        pipeline.addLast(new ObjectEncoder());

        //解码器
        //使用netty中的ObjectDecoder，它用于讲字节流解码为Java对象
        //在ObjectDecoder的构造函数中传入了一个ClassResolver 对象，用于解析类名并加载相应的类。
        pipeline.addLast(new ObjectDecoder(new ClassResolver() {
            @Override
            public Class<?> resolve(String className) throws ClassNotFoundException {
                return Class.forName(className);
            }
        }));

        pipeline.addLast(new NettyClientHandler());
    }
}
