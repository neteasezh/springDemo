package com.wust.java8.test1;

public class FilterByAge implements MyPredicate<User>{
    @Override
    public boolean filter(User user) {
        return user.getAge() >= 35;
    }
}
