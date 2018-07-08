package com.sb.inventory.service;

import com.sb.services.common.entity.Product;
import com.sb.services.common.entity.filter.ProductFilter;
import com.sb.services.common.entity.model.Page;
import com.sb.services.common.entity.model.ProductDTO;
import com.sb.services.common.entity.model.SupplyByteException;

public interface IProductManager {

    public Page<Product> getProducts(ProductFilter productFilter) throws SupplyByteException;

    public Product getProductDetails(String productUid) throws SupplyByteException;

    public Product saveProduct(ProductDTO product) throws SupplyByteException;

    public Product updateProduct(ProductDTO product) throws SupplyByteException;

    public Product deleteProduct(String productUid) throws SupplyByteException;
}
