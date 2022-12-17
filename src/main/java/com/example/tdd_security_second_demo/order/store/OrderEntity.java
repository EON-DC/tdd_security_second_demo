package com.example.tdd_security_second_demo.order.store;

import com.example.tdd_security_second_demo.order.domain.Order;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class OrderEntity {

    @Id
    private String id;

    protected OrderEntity() {

    }

    public OrderEntity(Order order) {

    }

    public Order toDomain() {
        Order order = new Order();
        // todo: copy logic
        return order;
    }
}
