package com.sb.inventory.controller;

import com.sb.services.common.entity.filter.ProductFilter;
import com.sb.services.common.entity.model.Page;
import com.sb.services.common.entity.model.ProductDTO;
import com.sb.services.common.entity.model.SupplyByteException;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequestMapping("/api/v1/product")
@Api(value = "Products", description = "Operations pertaining to Products")
public interface IProductController {

    @RequestMapping(value = "products", method = RequestMethod.GET)
    public Page<ProductDTO> getProducts(ProductFilter productFilter) throws SupplyByteException;

    @RequestMapping(value = "products/{uid}", method = RequestMethod.GET)
    public ProductDTO getProductDetails(String productUid) throws SupplyByteException;

    @RequestMapping(value = "products", method = RequestMethod.POST)
    public ProductDTO saveProduct(ProductDTO product) throws SupplyByteException;

    @RequestMapping(value = "products", method = RequestMethod.PUT)
    public ProductDTO updateProduct(ProductDTO product) throws SupplyByteException;

    @RequestMapping(value = "products/{uid}", method = RequestMethod.DELETE)
    public ProductDTO deleteProduct(String productUid) throws SupplyByteException;

}
