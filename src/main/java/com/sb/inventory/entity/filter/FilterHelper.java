package com.sb.inventory.entity.filter;

import com.sb.services.common.entity.filter.ProductFilter;
import com.sb.services.common.search.EnumEqualExpression;
import com.sb.services.common.search.SearchExpression;
import com.sb.services.common.search.StringEqualExpression;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

public class FilterHelper {
    public static List<SearchExpression> getExpressionForProductFilter(ProductFilter filter) {
        List<SearchExpression> exps = new ArrayList<>();

        SearchExpression expn = null;

        if (filter.getByName() != null) {
            expn = new StringEqualExpression("name", filter.getByName());
            exps.add(expn);
        }

        if (filter.getByProductType() != null) {
            expn = new EnumEqualExpression("productType", filter.getByProductType());
            exps.add(expn);
        }

        return exps;
    }
}
