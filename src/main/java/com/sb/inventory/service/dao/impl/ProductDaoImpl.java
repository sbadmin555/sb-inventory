package com.sb.inventory.service.dao.impl;

import com.sb.inventory.service.dao.IProductDAO;
import com.sb.services.common.data.GenericHibernateDaoImpl;
import com.sb.services.common.entity.Product;
import org.springframework.stereotype.Service;

@Service
public class ProductDaoImpl extends GenericHibernateDaoImpl<Product,String> implements IProductDAO{
}
