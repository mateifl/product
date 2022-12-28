package ro.zizicu.mservice.product.service.integration;


import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import ro.zizicu.mservice.product.entities.Category;
import ro.zizicu.mservice.product.services.CategoryService;
import ro.zizicu.nwbase.entity.IdentityOwner;

@SpringBootTest
public class CategoryServiceIntegrationTest {

    @Autowired
    private CategoryService categoryService;

    @Test
    void testLoadCategorySuccess() {
        Category c = categoryService.load(1);
        assertNotNull(c);
    }

    @Test
    void testSaveCategorySuccess() {
        IdentityOwner<Integer> e =  categoryService.create(new Category(null, "TestC", "testCD", "xxxxx"));
        assertNotNull(e);
        categoryService.delete((Category)e);
    }


}
