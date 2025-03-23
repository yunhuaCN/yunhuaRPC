package Client.rpcClient.Impl;

import Client.rpcClient.RpcClient;
import common.Message.RpcRequest;
import common.Message.RpcResponse;
import lombok.AllArgsConstructor;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * 这个就是上个版本中的IOClient
 */
@AllArgsConstructor
public class SimpleSocketRpcClient implements RpcClient {

    private String host;
    private int port;

    @Override
    public RpcResponse sendRequest(RpcRequest request){
        try {
            Socket socket=new Socket(host, port);
            ObjectOutputStream oos=new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream ois=new ObjectInputStream(socket.getInputStream());

            oos.writeObject(request);
            oos.flush();

            RpcResponse response=(RpcResponse) ois.readObject();
            return response;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
}
