package com.sb.inventory.service.impl;

import com.sb.inventory.entity.filter.FilterHelper;
import com.sb.inventory.service.IProductManager;
import com.sb.inventory.service.dao.IProductDAO;
import com.sb.services.common.entity.Product;
import com.sb.services.common.entity.filter.ProductFilter;
import com.sb.services.common.entity.filter.SortOrderEnum;
import com.sb.services.common.entity.model.Page;
import com.sb.services.common.entity.model.ProductDTO;
import com.sb.services.common.entity.model.SupplyByteException;
import com.sb.services.common.generator.StringKeyGenerator;
import com.sb.services.common.search.OrderBy;
import com.sb.services.common.search.Pagination;
import com.sb.services.common.search.SearchExpression;
import com.sb.services.common.util.ModelConvertor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class ProductManagerImpl implements IProductManager{

    private static Logger LOG = LoggerFactory.getLogger(ProductManagerImpl.class);

    @Autowired
    private IProductDAO productDAO;

    @Override
    public Page<Product> getProducts(ProductFilter productFilter) throws SupplyByteException {
        List<SearchExpression> exps = FilterHelper.getExpressionForProductFilter(productFilter);
        OrderBy orderBy = new OrderBy(
                (productFilter != null) ? productFilter.getSortCriteria().getSortFieldName().getSortableColumn()
                        : "name",
                SortOrderEnum.ASC.equals((productFilter != null) ? productFilter.getSortCriteria().getSortOrder()
                        : SortOrderEnum.ASC));
        LOG.info("Getting product list for expressions:{}, orderby:{}", exps, orderBy);
        Pagination pagination = new Pagination(
                (productFilter != null) ? productFilter.getPageInfo().getPageNumber() : 1,
                (productFilter != null) ? productFilter.getPageInfo().getLimit() : 50);
        List<Product> productList = productDAO.search(exps, orderBy, pagination);
        Page<Product> page = new Page<>();
        page.setItems(productList);
        page.setTotalRows(pagination.getItemCount());
        page.setNextPageExists(pagination.getItemCount() > (pagination.getPageSize() * pagination.getPageNumber()));
        page.setPreviousPageExists(pagination.getPageNumber() > 1);
        return page;
    }

    @Override
    public Product getProductDetails(String productUid) throws SupplyByteException {
        LOG.debug("Getting product details for UID : {}", productUid);
        Product product = productDAO.get(productUid);
        return product;
    }

    @Override
    public Product saveProduct(ProductDTO product) throws SupplyByteException {
        Product productDB = ModelConvertor.convertObject(product,Product.class);
        LOG.debug("Saving product : {}", productDB);
        productDB.setUid((String)new StringKeyGenerator().generate(null,null));
        productDAO.add(productDB);
        return productDB;
    }

    @Override
    public Product updateProduct(ProductDTO product) throws SupplyByteException {
        Product productDB = ModelConvertor.convertObject(product,Product.class);
        LOG.debug("Updating product : {}", productDB);
        productDAO.update(productDB);
        return productDB;
    }

    @Override
    public Product deleteProduct(String productUid) throws SupplyByteException {
        LOG.debug("Deleting product with UID : {}", productUid);
        Product product = productDAO.get(productUid);
        if(product != null) {
            productDAO.delete(product);
        }
        else{
            LOG.error("Product with UID {} doesn't exist",productUid);
            //TODO - Throw Exception for product doesn't exist
        }
        return product;
    }
}
