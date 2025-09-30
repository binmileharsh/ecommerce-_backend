package com.example.storeelectronic.demo.Services.impl;

import com.example.storeelectronic.demo.Dto.CategoryDto;
import com.example.storeelectronic.demo.Dto.PageableResponse;
import com.example.storeelectronic.demo.Dto.ProductDto;
import com.example.storeelectronic.demo.Dto.UserDto;
import com.example.storeelectronic.demo.Helper.Helper;
import com.example.storeelectronic.demo.Repository.CategoryRepository;
import com.example.storeelectronic.demo.Repository.ProductRepository;
import com.example.storeelectronic.demo.Services.ProductService;
import com.example.storeelectronic.demo.entities.Category;
import com.example.storeelectronic.demo.entities.Product;
import com.example.storeelectronic.demo.entities.User;
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
public class ProductImpl implements ProductService {

    @Autowired
    private CategoryRepository categoryrepo;
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private ProductRepository productrepo;

    @Override
    public ProductDto createProduct(ProductDto product) {
        Product to_product=this.mapper.map(product,Product.class);
        Product savedProduct=this.productrepo.save(to_product);
        ProductDto saved_one_product_Dto=this.mapper.map(savedProduct,ProductDto.class);
        return saved_one_product_Dto;    }

    @Override
    public ProductDto updataProduct(ProductDto product, String id) {
        Product find_Product=this.productrepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("product_ID","id",id));
        find_Product.setTitle(product.getTitle());
        find_Product.setQuantity(product.getQuantity());
        find_Product.setLive(product.isLive());
        find_Product.setAddedDate(product.getAddedDate());
        find_Product.setDescription(product.getDescription());
        find_Product.setPrice(product.getPrice());
        find_Product.setImageName(product.getImageName());
        find_Product.setDiscountedPrice(product.getDiscountedPrice());
        Product savedupdated=this.productrepo.save(find_Product);
        ProductDto updatedProdut=this.mapper.map(savedupdated,ProductDto.class);
        return updatedProdut;

    }

    @Override
    public void deleteProduct(String id) {
        Product foundProduct = this.productrepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
        this.productrepo.delete(foundProduct);

    }

    @Override
    public PageableResponse<ProductDto> getAllProduct(int pagenumber, int pagesize, String sortby, String sortdir) {
        Sort sort = sortdir.equalsIgnoreCase("desc")
                ? Sort.by(sortby).descending()
                : Sort.by(sortby).ascending();
        Pageable pageable = PageRequest.of(pagenumber, pagesize, sort);
        Page<Product> page = this.productrepo.findAll(pageable);
        PageableResponse<ProductDto> pageableResponse = Helper.getpageableresponse(page, ProductDto.class);
        return pageableResponse;
    }

    @Override
    public ProductDto getProductById(String id) {
        Product foundproduct = this.productrepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
        ProductDto foundone = this.mapper.map(foundproduct,ProductDto.class);
        return foundone;

    }

    @Override
    public PageableResponse<ProductDto> getallLive(int pagenumber, int pagesize, String sortby, String sortdir) {
        Sort sort = sortdir.equalsIgnoreCase("desc")
                ? Sort.by(sortby).descending()
                : Sort.by(sortby).ascending();
        Pageable pageable = PageRequest.of(pagenumber, pagesize, sort);
        Page<Product> page=this.productrepo.findByLiveTrue(pageable);
        PageableResponse<ProductDto> pageableResponse = Helper.getpageableresponse(page, ProductDto.class);
        return pageableResponse;

    }

    @Override
    public PageableResponse<ProductDto> searchbytitle(String subtitle, int pagenumber, int pagesize, String sortby, String sortdir) {
        Sort sort = sortdir.equalsIgnoreCase("desc")
                ? Sort.by(sortby).descending()
                : Sort.by(sortby).ascending();
        Pageable pageable = PageRequest.of(pagenumber, pagesize, sort);
        Page<Product> page=this.productrepo.findByTitleContaining( pageable,subtitle);
        PageableResponse<ProductDto> pageableResponse = Helper.getpageableresponse(page, ProductDto.class);
        return pageableResponse;
    }

    @Override
    public ProductDto createproductwithcategory(ProductDto product, String cat_id) {

        Category foundcategory=this.categoryrepo.findById(cat_id).orElseThrow(()->new ResourceNotFoundException("CATEGORY" ,"ID",cat_id));
        CategoryDto categoryDto=this.mapper.map(foundcategory,CategoryDto.class);
        product.setCategory(categoryDto);
        Product tobesaved=this.mapper.map(product,Product.class);
        Product savedProduct_withcategory=this.productrepo.save(tobesaved);
        ProductDto productdto=this.mapper.map(savedProduct_withcategory,ProductDto.class);
        return productdto;

    }

    @Override
    public ProductDto updatecategoryofproduct(String Productid, String cat_id) {
        Product product=this.productrepo.findById(Productid).orElseThrow(()->new ResourceNotFoundException("product","id",Productid));
        Category category=this.categoryrepo.findById(cat_id).orElseThrow(()->new ResourceNotFoundException("catid","id",cat_id));
         product.setCategory(category);
         Product getproduct=this.productrepo.save(product);
         ProductDto productdto=this.mapper.map(product,ProductDto.class);
         return productdto;

    }

    @Override
    public PageableResponse<ProductDto> allproductbycatid(String catid, int pagenumber, int pagesize, String sortby, String sortdir) {
        Sort sort = sortdir.equalsIgnoreCase("desc")
                ? Sort.by(sortby).descending()
                : Sort.by(sortby).ascending();
        Pageable pageable = PageRequest.of(pagenumber, pagesize, sort);
        Page<Product> page=this.productrepo.findByCategory_CategoryId(catid,pageable);
        PageableResponse<ProductDto> pageableResponse = Helper.getpageableresponse(page, ProductDto.class);
        return pageableResponse;
    }




}
