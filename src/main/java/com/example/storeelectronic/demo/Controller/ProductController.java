package com.example.storeelectronic.demo.Controller;

import com.example.storeelectronic.demo.Dto.ImageResponse;
import com.example.storeelectronic.demo.Dto.PageableResponse;
import com.example.storeelectronic.demo.Dto.ProductDto;
import com.example.storeelectronic.demo.Dto.UserDto;
import com.example.storeelectronic.demo.Services.Fileservice;
import com.example.storeelectronic.demo.Services.ProductService;
import jakarta.servlet.http.HttpServletResponse;
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


@RestController
@RequestMapping("product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @Value("${product.file.image.path}")
    private String imageUploadPath;
    @Autowired
    private Fileservice fileservice;


    @PostMapping("/addproduct")
    public ResponseEntity<ProductDto> createproduct(@RequestBody ProductDto product) {
        ProductDto pr = this.productService.createProduct(product);
        return new ResponseEntity<>(pr, HttpStatus.CREATED);
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<ProductDto> updateuuser(@RequestBody ProductDto reqbody, @PathVariable String id) {
        ProductDto productdto = this.productService.updataProduct(reqbody, id);
        return new ResponseEntity<>(productdto, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{productid}")
    public ResponseEntity<String> deleteproduct(@PathVariable String productid) {
        this.productService.deleteProduct(productid);
        String s = "thsi product has been deleted thanku";
        return new ResponseEntity<>(s, HttpStatus.OK);

    }

    @GetMapping("/getproductbyid/{productid}")
    public ResponseEntity<ProductDto> getproduct(@PathVariable String productid) {
        ProductDto product = this.productService.getProductById(productid);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @GetMapping("ALLproduct")
    public ResponseEntity<PageableResponse<ProductDto>> getallproduct(@RequestParam(value = "pagenumber", defaultValue = "0") @Min(0) int pagenumber,
                                                                      @RequestParam(value = "pagesize", defaultValue = "10") @Min(1) int pagesize,
                                                                      @RequestParam(value = "sortBy", defaultValue = "title") String sortby,
                                                                      @RequestParam(value = "sortdir", defaultValue = "ASC") String sortdir) {

        PageableResponse<ProductDto> products = this.productService.getAllProduct(pagenumber, pagesize, sortby, sortdir);
        return new ResponseEntity<>(products, HttpStatus.OK);

    }

    @GetMapping("liveroduct")
    public ResponseEntity<PageableResponse<ProductDto>> getallliveproduct(@RequestParam(value = "pagenumber", defaultValue = "0") @Min(0) int pagenumber,
                                                                          @RequestParam(value = "pagesize", defaultValue = "10") @Min(10) int pagesize,
                                                                          @RequestParam(value = "sortBy", defaultValue = "title") String sortby,
                                                                          @RequestParam(value = "sortdir", defaultValue = "ASC") String sortdir) {

        PageableResponse<ProductDto> products = this.productService.getallLive(pagenumber, pagesize, sortby, sortdir);
        return new ResponseEntity<>(products, HttpStatus.OK);

    }


    @GetMapping("searchproduct/{query}")
    public ResponseEntity<PageableResponse<ProductDto>> getalllsearchproduct(
            @PathVariable String query, @RequestParam(value = "pagenumber", defaultValue = "0") @Min(0) int pagenumber,
            @RequestParam(value = "pagesize", defaultValue = "10") @Min(10) int pagesize,
            @RequestParam(value = "sortBy", defaultValue = "title") String sortby,
            @RequestParam(value = "sortdir", defaultValue = "ASC") String sortdir) {

        PageableResponse<ProductDto> products = this.productService.searchbytitle(query, pagenumber, pagesize, sortby, sortdir);

        return new ResponseEntity<>(products, HttpStatus.OK);

    }


    @PostMapping("upload/{productid}")
    public ResponseEntity<ImageResponse> uploadimage(@RequestParam("productimage") MultipartFile image, @PathVariable String productid) {
        String imagename = this.fileservice.uploadFile(image, imageUploadPath);
        ProductDto product = this.productService.getProductById(productid);
        product.setImageName(imagename);
        this.productService.updataProduct(product, productid);
        ImageResponse imageResponse = ImageResponse.builder().imageName(imagename).messsage("product image").success(true).build();
        return new ResponseEntity<>(imageResponse, HttpStatus.OK);
    }

    @GetMapping("/imageofproduct/{productid}")
    public void serveImage(@PathVariable String productid, HttpServletResponse response) throws IOException {
        ProductDto product = this.productService.getProductById(productid);
        InputStream input = this.fileservice.getResource(imageUploadPath, product.getImageName());
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        StreamUtils.copy(input, response.getOutputStream());

    }

    @PostMapping("/{cat_id}/product")
    public ResponseEntity<ProductDto> createproductwithcategory(@RequestBody ProductDto product, @PathVariable String cat_id) {
        ProductDto pr = this.productService.createproductwithcategory(product, cat_id);
        return new ResponseEntity<>(pr, HttpStatus.CREATED);
    }
    @PutMapping("/updateproduct/{cat_id}/{product_id}")
    public ResponseEntity<ProductDto> updatecategoryofproduct(@PathVariable String product_id,@PathVariable String cat_id){
        ProductDto product=this.productService.updatecategoryofproduct(product_id,cat_id);
        return new ResponseEntity<>(product, HttpStatus.CREATED);
    }
    @GetMapping("allproduct/{catid}")
    public ResponseEntity<PageableResponse<ProductDto>> alproductbycategoryid(@PathVariable String catid,@RequestParam(value = "pagenumber", defaultValue = "0") @Min(0) int pagenumber,
                                                            @RequestParam(value = "pagesize", defaultValue = "10") @Min(1) int pagesize,
                                                            @RequestParam(value = "sortBy", defaultValue = "title") String sortby,
                                                            @RequestParam(value = "sortdir", defaultValue = "ASC") String sortdir){
        PageableResponse<ProductDto> products=this.productService.allproductbycatid(catid,pagenumber,pagesize,sortby,sortdir);
        return new ResponseEntity<>(products, HttpStatus.CREATED);





}}