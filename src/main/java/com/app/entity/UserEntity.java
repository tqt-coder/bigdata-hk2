package com.app.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity {
    private String product_id;
    private String price;
    private String stock;
    private String currency;
    private String brand;
    private String serial_number;
    private String product;
    private String category;
    private String created_on;
    private String updated_on;
    private String model_number;
    private String product_name;
    private String category_type;
    private String weight;

}
