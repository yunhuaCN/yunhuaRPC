package common.service.Impl;

import common.pojo.User;
import common.service.UserService;

import java.util.Random;
import java.util.UUID;

public class UserServiceImpl implements UserService {
    @Override
    public User getUserByUserId(Integer id) {
        System.out.println("客户端查询了"+id+"用户");
        //模拟从数据库取用户的行为
        Random random = new Random();
        //使用构造器方式构造
        User user = User.builder()
                .userName(UUID.randomUUID().toString())
                .id(id)
                .age(random.nextInt(50))
                .sex(random.nextBoolean())
                .build();
        return user;
    }

    @Override
    public Integer insertUserId(User user) {
        System.out.println("插入数据成功："+user.getUserName() + "userId" + user.getId());
        return user.getId();
    }
}
