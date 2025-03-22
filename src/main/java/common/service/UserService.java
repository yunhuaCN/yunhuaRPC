package common.service;

import common.pojo.User;

public interface UserService {
    //客户端通过这个接口调用服务端的实现类
    User getUserByUserId(Integer id);
    //服务端通过这个接口调用客户端的实现类
    Integer insertUserId(User user);
}
