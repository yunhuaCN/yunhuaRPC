package common.Message;

import common.constants.MessageConstants;
import io.netty.handler.codec.mqtt.MqttMessageIdVariableHeader;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RpcResponse {

    /**
     * 状态码
     * 成功:200
     * 失败:500
     */
    private int code;
    //状态信息
    private String message;
    //具体数据
    private Object data;

    //使用构建器模式构建信息
    //构造成功信息
    public static RpcResponse sucess(Object data) {
        return RpcResponse.builder()
                .code(MessageConstants.SUCCESS)
                .data(data)
                .build();
    }
    //构造失败信息
    public static RpcResponse fail() {
        return RpcResponse.builder()
                .code(MessageConstants.FAIL)
                .message(MessageConstants.SERVER_ERROR_OCCURRED)
                .build();
    }

}
