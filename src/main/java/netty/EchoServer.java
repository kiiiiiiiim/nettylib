package netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class EchoServer {
  private int port;

  public EchoServer(int port) {
    this.setPort(port);
  }

  public void start() throws Exception {
    EventLoopGroup bossgroup = new NioEventLoopGroup(1);
    EventLoopGroup workerGroup = new NioEventLoopGroup();
    try {
      ServerBootstrap b = new ServerBootstrap();
      b.group(bossgroup, workerGroup).channel(NioServerSocketChannel.class)
          .childHandler(new ChannelInitializer<SocketChannel>() {
            @Override
            public void initChannel(SocketChannel ch) {
              ChannelPipeline p = ch.pipeline();
              p.addLast(new EchoHandler());
            }
          });

      ChannelFuture f = b.bind(port).sync();
      f.channel().closeFuture().sync();

    } finally {
      workerGroup.shutdownGracefully();
      bossgroup.shutdownGracefully();
    }
  }

  public int getPort() {
    return port;
  }

  public void setPort(int port) {
    this.port = port;
  }
}
