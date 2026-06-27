package com.buildcrm.dto.response;

import java.math.BigDecimal;
import java.util.UUID;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ProjectInventoryResponse {

    private UUID id;
    private String unitNo;
    private String type;          // Flat, Plot, NA Plot
    private String area;
    private BigDecimal price;
    private String status;        // Available, Sold, Blocked
}