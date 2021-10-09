package com.wust.java8.test1;

public class FilterBySalary implements MyPredicate<User>{
    @Override
    public boolean filter(User user) {
        return user.getSalary() > 5000;
    }
}
