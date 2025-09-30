package com.example.storeelectronic.demo.Controller;

import com.example.storeelectronic.demo.Dto.ApiResponse;
import com.example.storeelectronic.demo.Dto.ImageResponse;
import com.example.storeelectronic.demo.Dto.PageableResponse;
import com.example.storeelectronic.demo.Dto.UserDto;
import com.example.storeelectronic.demo.Services.Fileservice;
import com.example.storeelectronic.demo.Services.Userservice;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@RestController
@RequestMapping("/user")
public class Usercontroller {

    @Autowired
    private Userservice userservice;
    @Autowired
    private Fileservice fileservice;

    @Value("${user.file.image.path}")
    private String imageUploadPath;

    @PostMapping("/create")
    public ResponseEntity<UserDto> createuser(@Valid @RequestBody UserDto reqbody) {
        UserDto userdto = this.userservice.createuser(reqbody);
        return new ResponseEntity<>(userdto, HttpStatus.CREATED);
    }

    @PutMapping("/upate/{userid}")
    public ResponseEntity<UserDto> updateuuser(@RequestBody UserDto reqbody, @PathVariable String userid) {
        UserDto userdto = this.userservice.updateuser(reqbody, userid);
        return new ResponseEntity<>(userdto, HttpStatus.OK);

    }

    @DeleteMapping("delete/{userid}")
    public ResponseEntity<ApiResponse<String>> deleteuser(@PathVariable String userid) {
        this.userservice.deleteuser(userid);
        String s = "User has been deleted";
        ApiResponse<String> res = new ApiResponse(s, "ok report", true);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping("ALL")
    public ResponseEntity<PageableResponse<UserDto>> alluser(@RequestParam(value = "pagenumber", defaultValue = "0") @Min(0) int pagenumber,
                                                             @RequestParam(value = "pagesize", defaultValue = "10") @Min(10) int pagesize,
                                                             @RequestParam(value = "sortBy", defaultValue = "name") String sortby,
                                                             @RequestParam(value = "sortdir", defaultValue = "ASC") String sortdir) {
        PageableResponse<UserDto> alluser = this.userservice.getalluser(pagenumber, pagesize, sortby, sortdir);
        return new ResponseEntity<>(alluser, HttpStatus.OK);

        

    }

    @GetMapping("userbyis/{id}")
    public ResponseEntity<UserDto> user(@PathVariable String id) {
        UserDto userdto = this.userservice.getuserbyid(id);

        return new ResponseEntity<>(userdto, HttpStatus.OK);

    }

    @GetMapping("userbyemail/{email}")
    public ResponseEntity<UserDto> userbyemail(@PathVariable String email) {
        UserDto userdto = this.userservice.getuserbyemail(email);
        return new ResponseEntity<>(userdto, HttpStatus.OK);
    }

    @GetMapping("bykey=/{keyword}")
    public ResponseEntity<List<UserDto>> userbysearch(@PathVariable String keyword) {
        List<UserDto> userdto = this.userservice.getuserbysearch(keyword);
        return new ResponseEntity<>(userdto, HttpStatus.OK);
    }

    @PostMapping("upload/{userid}")
    public ResponseEntity<ImageResponse> uploadimage(@RequestParam("userimage") MultipartFile image, @PathVariable String userid) {
        String imagename = this.fileservice.uploadFile(image, imageUploadPath);
        UserDto user = this.userservice.getuserbyid(userid);
        user.setImageName(imagename);
        this.userservice.updateuser(user, userid);
        ImageResponse imageResponse = ImageResponse.builder().imageName(imagename).messsage("user image").success(true).build();
        return new ResponseEntity<>(imageResponse, HttpStatus.OK);

    }

    @GetMapping("/imageofuser/{userid}")
    public void serveImage(@PathVariable String userid, HttpServletResponse response) throws IOException {
        UserDto user = this.userservice.getuserbyid(userid);
        InputStream input = this.fileservice.getResource(imageUploadPath, user.getImageName());
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        StreamUtils.copy(input, response.getOutputStream());

    }



}
