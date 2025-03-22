package Server;

import Server.provider.ServiceProvider;
import Server.server.Impl.RpcServerImpl;
import Server.server.Impl.ThreadPoolRPCRPCServer;
import Server.server.RpcServer;
import common.service.Impl.UserServiceImpl;
import common.service.UserService;

public class TestServer {

    public static void main(String[] args) {
        UserService userService=new UserServiceImpl();

        ServiceProvider serviceProvider=new ServiceProvider();
        serviceProvider.provideServiceInterface(userService);

        RpcServer rpcServer=new RpcServerImpl(serviceProvider);
        rpcServer.start(9999);
    }

}
