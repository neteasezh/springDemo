package com.wust.util;

import com.wust.bean.User;
import org.springframework.beans.factory.FactoryBean;

public class UserFactoryBean implements FactoryBean<User> {
    //设置返回的对象
    @Override
    public User getObject() throws Exception {
        return new User();
    }
    //设置返回对象的类型
    @Override
    public Class<?> getObjectType() {
        return User.class;
    }
    //设置是否单例
    @Override
    public boolean isSingleton() {
        return true;
    }
}
