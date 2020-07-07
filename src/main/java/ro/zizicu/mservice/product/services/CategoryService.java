package ro.zizicu.mservice.product.services;

import ro.zizicu.mservice.product.entities.Category;
import ro.zizicu.nwbase.service.CrudService;
import ro.zizicu.nwbase.service.NamedService;

public interface CategoryService extends CrudService<Category, Integer>, NamedService<Category, Integer> {

}
