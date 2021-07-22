package com.gunjan768.redis_demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@RedisHash("Product")   // Can give any name, not necessarily "Product"
public class Product implements Serializable {
    @Id
    private int id;
    private String name;
    private int qty;
    private long price;
}