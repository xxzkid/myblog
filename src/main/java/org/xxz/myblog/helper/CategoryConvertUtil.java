package org.xxz.myblog.helper;

import org.xxz.myblog.common.util.DozerUtil;
import org.xxz.myblog.entity.Category;
import org.xxz.myblog.model.dto.CategoryDTO;

/**
 * @author tt
 */
public final class CategoryConvertUtil {

    public static Category categoryDTO2Category(CategoryDTO categoryDto, Category category) {
        if(category == null) {
            category = new Category();
        }
        DozerUtil.map(categoryDto, category);
        return category;
    }

}
