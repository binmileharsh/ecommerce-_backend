package com.example.storeelectronic.demo.Services;

import com.example.storeelectronic.demo.Dto.PageableResponse;
import com.example.storeelectronic.demo.Dto.ProductDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {
    ProductDto createProduct(ProductDto product);
    ProductDto updataProduct(ProductDto  pruduct,String id);
    void deleteProduct(String id);
    PageableResponse<ProductDto> getAllProduct(int pagenumber, int pagesize, String sortby, String sortdir);
    ProductDto getProductById(String id);
    PageableResponse<ProductDto>getallLive(int pagenumber, int pagesize, String sortby, String sortdir);
    PageableResponse<ProductDto>searchbytitle(String subtitle,int pagenumber, int pagesize, String sortby, String sortdir);
    ProductDto createproductwithcategory(ProductDto product,String cat_id);
    ProductDto updatecategoryofproduct(String Productid,String cat_id);
PageableResponse<ProductDto> allproductbycatid(String catid, int pagenumber, int pagesize, String sortby, String sortdir);



}
