package com.example.storeelectronic.demo.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AddItemDto {
  private   String productid;
  private int quantity;

}
