package com.epam.amorozov.kafka.listener.models.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShareDTO {
    Long id;
    String name;
    BigDecimal price;

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Share: ")
                .append(id).append(" ")
                .append(name).append(" ")
                .append(", price: ")
                .append(price);
        return builder.toString();
    }
}
