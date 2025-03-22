package Server.server;

public interface RpcServer {
    //开启监听
    void start(int port); // ??? TODO 为什么只穿入port
    void stop();
}
