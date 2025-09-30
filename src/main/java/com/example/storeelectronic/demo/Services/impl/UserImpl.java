package com.example.storeelectronic.demo.Services.impl;

import com.example.storeelectronic.demo.Dto.PageableResponse;
import com.example.storeelectronic.demo.Dto.UserDto;
import com.example.storeelectronic.demo.Helper.Helper;
import com.example.storeelectronic.demo.Repository.UserRepository;
import com.example.storeelectronic.demo.Services.Userservice;
import com.example.storeelectronic.demo.entities.User;
import com.example.storeelectronic.demo.exception.ResourceNotFoundException;
import lombok.Builder;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Builder
public class UserImpl implements Userservice {
    @Autowired
    private UserRepository userRepo;
    @Autowired
    private ModelMapper modelmapper;


    @Override
    public UserDto createuser(UserDto userdto) {
        String userid = UUID.randomUUID().toString();
        userdto.setUserId(userid);
        User x = Userfromdto(userdto);
        User savedUser = userRepo.save(x);
        UserDto USERDTO = this.dtofromentity(savedUser);
        return USERDTO;


    }

    @Override
    public UserDto updateuser(UserDto user, String userid) {

        User founduser = this.userRepo.findById(userid).orElseThrow(() -> new RuntimeException("USER NOT FOUND"));
        founduser.setName(user.getName());
        founduser.setAbout(user.getAbout());
        founduser.setGender(user.getGender());
        founduser.setPassword(user.getPassword());
        founduser.setEmail(user.getEmail());
        founduser.setImageName(user.getImageName());
        User updateuser = this.userRepo.save(founduser);
        UserDto updatedone = this.dtofromentity(updateuser);
        return updatedone;


    }


    @Override
    public void deleteuser(String id) {
        User founduser = this.userRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
this.userRepo.delete(founduser);    }

    @Override
    public UserDto getuserbyid(String id) {
        User founduser = this.userRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
        UserDto foundone = this.dtofromentity(founduser);
        return foundone;
    }

    @Override
    public UserDto getuserbyemail(String email) {
        User user = this.userRepo.findByEmail(email).orElseThrow(() -> new RuntimeException("USER NOT FOUND"));

        UserDto foundone = this.dtofromentity(user);
        return foundone;

    }

    @Override
    public PageableResponse<UserDto> getalluser(int pagenumber, int pagesize, String sortby, String sortdir) {
        Sort sort = sortdir.equalsIgnoreCase("desc")
                ? Sort.by(sortby).descending()
                : Sort.by(sortby).ascending();
        Pageable pageable = PageRequest.of(pagenumber, pagesize, sort);
        Page<User> page = this.userRepo.findAll(pageable);
        PageableResponse<UserDto> pageableResponse = Helper.getpageableresponse(page, UserDto.class);
        return pageableResponse;

    }

    @Override
    public List<UserDto> getuserbysearch(String keyword) {
        List<User> alluser = this.userRepo.findByNameContainingIgnoreCase(keyword);
        List<UserDto> allusers = alluser.stream().map((user) -> dtofromentity(user)).collect(Collectors.toList());
        ;
        return allusers;
    }

    public User Userfromdto(UserDto userdto) {


        return this.modelmapper.map(userdto, User.class);
    }

    public UserDto dtofromentity(User user) {
        return this.modelmapper.map(user, UserDto.class);
    }
}




