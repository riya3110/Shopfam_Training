package com.shopfam.order;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shopfam.common.entity.order.Order;

public interface OrderRepository extends JpaRepository<Order , Integer> {

}
