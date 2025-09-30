package com.example.storeelectronic.demo.Dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApiResponse<T> {
    T data;
    String messsage;
    boolean success;
    ApiResponse(T data,boolean success,String messsage){
        this.messsage=messsage;
        this.success=success;
        this.data=data;
    }
}
