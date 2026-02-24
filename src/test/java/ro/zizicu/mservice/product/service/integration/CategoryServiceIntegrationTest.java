package ro.zizicu.mservice.product.service.integration;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ro.zizicu.mservice.product.BaseIntegrationTest;
import ro.zizicu.mservice.product.entities.Category;
import ro.zizicu.mservice.product.services.CategoryService;
import ro.zizicu.nwbase.entity.IdentityOwner;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CategoryServiceIntegrationTest extends BaseIntegrationTest {

    @Autowired
    private CategoryService categoryService;

    @Test
    void testLoadCategorySuccess() {
        Category c = categoryService.load(1);
        assertNotNull(c);
    }

    @Test
    void testSaveCategorySuccess() {
        IdentityOwner<Integer> e =  categoryService.create(new Category(null, "TestC", "testCD"));
        assertNotNull(e);
        categoryService.delete(e.getId());
    }
}
