package netty;

public class Main {
  public static void main(String[] args) throws Exception {
    EchoServer server = new EchoServer(30000);
    server.start();
  }
}
