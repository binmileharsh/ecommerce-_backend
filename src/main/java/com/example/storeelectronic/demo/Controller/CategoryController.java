package com.example.storeelectronic.demo.Controller;

import com.example.storeelectronic.demo.Dto.ApiResponse;
import com.example.storeelectronic.demo.Dto.CategoryDto;
import com.example.storeelectronic.demo.Dto.PageableResponse;
import com.example.storeelectronic.demo.Dto.UserDto;
import com.example.storeelectronic.demo.Services.CategoryService;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @PostMapping ("/create")
    public ResponseEntity<CategoryDto> createCategory(@RequestBody CategoryDto reqbody){
        CategoryDto categorydto=this.categoryService.createcategory(reqbody);
        return  new ResponseEntity<>(categorydto,HttpStatus.CREATED);

    }
    @PutMapping("/update/{catid}")
    public ResponseEntity<CategoryDto> updateuuser(@RequestBody CategoryDto reqbody,@PathVariable String catid){
        CategoryDto catdto = this.categoryService.updatecategory(catid,reqbody);
        return new ResponseEntity<>(catdto, HttpStatus.OK);

    }
    @DeleteMapping("delete/{catid}")
    public ResponseEntity<ApiResponse<String>> deleteuser(@PathVariable String catid) {
        this.categoryService.deletecategory(catid);
        String s = "User has been deleted";
        ApiResponse<String> res = new ApiResponse( s, "ok report",true);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping("ALL")
    public ResponseEntity<PageableResponse<CategoryDto>> alluser(@RequestParam(value="pagenumber",defaultValue = "0") @Min(0) int pagenumber,
                                                             @RequestParam(value="pagesize" ,defaultValue = "10")  @Min(10)int pagesize,
                                                             @RequestParam(value = "sortBy" ,defaultValue="title") String sortby,
                                                             @RequestParam(value = "sortdir" ,defaultValue="ASC") String sortdir)


    {
        PageableResponse<CategoryDto> allcat=this.categoryService.getallcategory(pagenumber,pagesize,sortby,sortdir);
        return new ResponseEntity<>(allcat, HttpStatus.OK);

    }

    @GetMapping("catbyis/{id}")
    public ResponseEntity<CategoryDto> user(@PathVariable String id){
        CategoryDto catdto=this.categoryService.getbyid(id);

        return new ResponseEntity<>(catdto, HttpStatus.OK);

    }
}
