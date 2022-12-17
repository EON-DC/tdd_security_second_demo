package com.example.tdd_security_second_demo.authentication.store;


import com.example.tdd_security_second_demo.authentication.domain.User;
import com.example.tdd_security_second_demo.authentication.domain.UserRole;
import com.example.tdd_security_second_demo.order.domain.Order;
import com.example.tdd_security_second_demo.order.store.OrderEntity;
import com.fasterxml.jackson.databind.util.BeanUtil;
import lombok.*;
import org.springframework.beans.BeanUtils;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "TB_USERS")
public class UserEntity {

    @Id
    @Column(name = "user_id")
    private String id;
    private String name;
    @Column(unique = true)
    private String email;
    private String password;
    private Timestamp creationTime;

    @OneToMany(mappedBy = "order_id")
    private List<OrderEntity> orders;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<UserRole> roles;

    protected UserEntity() {
        orders = new ArrayList<>();
        roles = new ArrayList<>();
    }

    public UserEntity(User user){
        this();
        BeanUtils.copyProperties(user, this);
        if (orders.size() > 0) {
            for (Order order : user.getOrders()) {
                orders.add(new OrderEntity(order));
            }
        }
    }

    public User toDomain() {
        User user = User.builder().build();
        BeanUtils.copyProperties(this, user);
        if (this.orders.size() > 0) {
            for (OrderEntity orderEntity : orders) {
                user.getOrders().add(orderEntity.toDomain());
            }
        }
        return user;
    }
}
