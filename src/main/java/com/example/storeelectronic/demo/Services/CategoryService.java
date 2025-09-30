package com.example.storeelectronic.demo.Services;

import com.example.storeelectronic.demo.Dto.CategoryDto;
import com.example.storeelectronic.demo.Dto.PageableResponse;

public interface CategoryService {
    CategoryDto createcategory(CategoryDto categoryu);
    CategoryDto updatecategory(String id,CategoryDto category);
    String deletecategory(String id);
    PageableResponse<CategoryDto> getallcategory(int pagenumber, int pagesize, String sortby, String sortdir);
    CategoryDto getbyid(String id);




}
