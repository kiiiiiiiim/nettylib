package netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class EchoHandler extends ChannelInboundHandlerAdapter {

  @Override
  public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
    ByteBuf byteBufMessage = (ByteBuf) msg;
    int size = byteBufMessage.readableBytes();
    byte[] byteMessage = new byte[size];
    for (int i = 0; i < size; i++) {
      byteMessage[i] = byteBufMessage.getByte(i);
    }
    String str = new String(byteMessage);
    System.out.println(str);
    ctx.write(msg);
  }

  @Override
  public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
    ctx.flush();
  }

  @Override
  public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
    cause.printStackTrace();
  }
}

