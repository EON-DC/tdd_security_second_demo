package com.example.tdd_security_second_demo.order.domain;

import com.example.tdd_security_second_demo.authentication.domain.User;
import com.example.tdd_security_second_demo.share.domain.NameValue;
import com.example.tdd_security_second_demo.share.domain.NameValueList;
import com.example.tdd_security_second_demo.share.json.JsonUtil;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.Date;

@Getter
@Setter
public class Order {

    private String id;
    private Timestamp creationOnTime;
    private User user;
    private Integer price;

    public Order() {
        creationOnTime = new Timestamp(new Date().getTime());
    }

    @Builder
    public Order(Timestamp creationOnTime, User user, Integer price) {
        this();
        this.creationOnTime = creationOnTime;
        this.user = user;
        this.price = price;
    }

    public void setValues(NameValueList nameValueList) {
        for (NameValue nameValue : nameValueList.getNameValues()) {
            String name = nameValue.getName();
            String value = nameValue.getValue();
            switch (name) {
                case "user":
                    this.user = JsonUtil.fromJson(value, User.class);
                    break;
                case "price":
                    this.price = Integer.valueOf(value);
                    break;
                default:
                    throw new RuntimeException("No such field : " + name);
            }
        }
    }
}
