package com.wust.bean;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Data

public class Ball {
    @Qualifier("car1")
    @Autowired
    private  Car car;
}
