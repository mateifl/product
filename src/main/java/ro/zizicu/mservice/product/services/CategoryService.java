package ro.zizicu.mservice.product.services;

import ro.zizicu.mservice.product.entities.Category;
import ro.zizicu.nwbase.service.NamedService;

public interface CategoryService extends NamedService<Category, Integer> {

    Category update(Category category);

}
