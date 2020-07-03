package ro.zizicu.mservice.product.services;

import ro.zizicu.mservice.product.entities.Category;
import ro.zizicu.nwbase.service.CrudService;

public interface CategoryService extends CrudService<Category, Integer>{
	Category loadByName(String name);
}
