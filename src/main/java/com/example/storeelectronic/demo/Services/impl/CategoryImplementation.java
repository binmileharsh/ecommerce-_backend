package com.example.storeelectronic.demo.Services.impl;

import com.example.storeelectronic.demo.Dto.CategoryDto;
import com.example.storeelectronic.demo.Dto.PageableResponse;
import com.example.storeelectronic.demo.Dto.UserDto;
import com.example.storeelectronic.demo.Helper.Helper;
import com.example.storeelectronic.demo.Repository.CategoryRepository;
import com.example.storeelectronic.demo.Services.CategoryService;
import com.example.storeelectronic.demo.entities.Category;
import com.example.storeelectronic.demo.exception.ResourceNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryImplementation implements CategoryService {
    @Autowired
    private ModelMapper modelmapper;
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public CategoryDto createcategory(CategoryDto categoryu) {
        Category TO_BE_SAVED=modelmapper.map(categoryu,Category.class);
        Category saved=this.categoryRepository.save(TO_BE_SAVED);
        CategoryDto returned_saved=this.modelmapper.map(saved,CategoryDto.class);
        return  returned_saved;

    }

    @Override
    public CategoryDto updatecategory(String id, CategoryDto category) {
        Category foundcategory=this.categoryRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("CATEGORY" ,"ID",id));
        foundcategory.setTitle(category.getTitle());
        foundcategory.setDescription(category.getDescription());
        foundcategory.setCoverImage(category.getCoverImage());
        Category category1=this.categoryRepository.save(foundcategory);
        CategoryDto updated=this.modelmapper.map(category1,CategoryDto.class);
        return updated;

    }

    @Override
    public String deletecategory(String id) {
        Category foundcategory=this.categoryRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("CATEGORY" ,"ID",id));
        this.categoryRepository.delete(foundcategory);
        String s="Category with the give Id has been deleted thanku!!!";
        return s;
    }


    @Override
    public PageableResponse<CategoryDto> getallcategory(int pagenumber, int pagesize, String sortby, String sortdir) {
        Sort sort = sortdir.equalsIgnoreCase("desc")
                ? Sort.by(sortby).descending()
                : Sort.by(sortby).ascending();
        Pageable pageable = PageRequest.of(pagenumber, pagesize, sort);
        Page<Category> all=this.categoryRepository.findAll(pageable);
        PageableResponse<CategoryDto> pageableResponse = Helper.getpageableresponse(all, CategoryDto.class);

        return pageableResponse;  }

    @Override
    public CategoryDto getbyid(String id) {
        Category foundcategory=this.categoryRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("CATEGORY" ,"ID",id));
        CategoryDto returned_saved=this.modelmapper.map(foundcategory,CategoryDto.class);
        return  returned_saved;
    }
}
