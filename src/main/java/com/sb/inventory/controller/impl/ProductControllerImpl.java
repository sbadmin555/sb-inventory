package com.sb.inventory.controller.impl;

import com.sb.inventory.controller.IProductController;
import com.sb.inventory.service.IProductManager;
import com.sb.services.common.entity.Product;
import com.sb.services.common.entity.filter.ProductFilter;
import com.sb.services.common.entity.model.Page;
import com.sb.services.common.entity.model.ProductDTO;
import com.sb.services.common.entity.model.SupplyByteException;
import com.sb.services.common.util.ModelConvertor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductControllerImpl implements IProductController {

    private static Logger LOG = LoggerFactory.getLogger(ProductControllerImpl.class);


    @Autowired
    private IProductManager productManager;

    @Override
    public Page<ProductDTO> getProducts(ProductFilter productFilter) throws SupplyByteException {
        LOG.debug("getting products for filter {}",productFilter);
        Page<Product> productPage = productManager.getProducts(productFilter);
        return ModelConvertor.convertPage(productPage,ProductDTO.class);
    }

    @Override
    public ProductDTO getProductDetails(@PathVariable("uid") String productUid) throws SupplyByteException {
        LOG.debug("getting product details for UID {}",productUid);
        Product product = productManager.getProductDetails(productUid);
        return ModelConvertor.convertObject(product,ProductDTO.class);
    }

    @Override
    public ProductDTO saveProduct(@RequestBody ProductDTO product) throws SupplyByteException {
        LOG.debug("Adding product : {}",product);
        Product productDB = productManager.saveProduct(product);
        return ModelConvertor.convertObject(productDB,ProductDTO.class);
    }

    @Override
    public ProductDTO updateProduct(@RequestBody ProductDTO product) throws SupplyByteException {
        LOG.debug("Updating product : {}",product);
        Product productDB = productManager.updateProduct(product);
        return ModelConvertor.convertObject(productDB,ProductDTO.class);
    }

    @Override
    public ProductDTO deleteProduct(@PathVariable("uid") String productUid) throws SupplyByteException {
        LOG.debug("Deleting product with UID : {}",productUid);
        Product productDB = productManager.deleteProduct(productUid);
        return ModelConvertor.convertObject(productDB,ProductDTO.class);
    }
}
