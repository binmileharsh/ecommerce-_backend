package com.example.storeelectronic.demo.Services;

import com.example.storeelectronic.demo.Dto.PageableResponse;
import com.example.storeelectronic.demo.Dto.UserDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface Userservice {
    UserDto createuser(UserDto user);

    UserDto updateuser(UserDto user, String userid);

    void deleteuser(String id);

    UserDto getuserbyid(String id);

    UserDto getuserbyemail(String email);

    PageableResponse<UserDto> getalluser(int pagenumber, int pagesize, String sortby, String sortdir);

    List<UserDto> getuserbysearch(String keyword);

}
