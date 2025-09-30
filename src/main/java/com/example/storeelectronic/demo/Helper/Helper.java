package com.example.storeelectronic.demo.Helper;

import com.example.storeelectronic.demo.Dto.PageableResponse;
import com.example.storeelectronic.demo.Dto.UserDto;
import com.example.storeelectronic.demo.entities.User;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

public class Helper {
    public static <U,V> PageableResponse<V> getpageableresponse(Page<U> page,Class<V> type){

        List<U> alluser=page.getContent();

        List<V> allluser = alluser.stream().map((obj) ->new ModelMapper().map(obj,type)).collect(Collectors.toList());
        PageableResponse<V> pageableResponse=new PageableResponse<>();
        pageableResponse.setData(allluser);
        pageableResponse.setPagesize(page.getSize());
        pageableResponse.setPagenumber(page.getNumber());
        pageableResponse.setTotalElement((int) page.getTotalElements());
        pageableResponse.setLastpage(page.isLast());
        return pageableResponse;

    }

}
