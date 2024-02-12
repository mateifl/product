package ro.zizicu.mservice.product.data.impl;

import java.util.List;
import java.util.Optional;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;

import org.springframework.stereotype.Repository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ro.zizicu.mservice.product.data.ProductRepositoryCustom;
import ro.zizicu.mservice.product.entities.*;

@Repository
@Slf4j
@AllArgsConstructor
public class ProductRepositoryCustomImpl implements ProductRepositoryCustom {

    private final EntityManager em;

    @Override
    public List<Product> find(String name, Optional<Category> category, Optional<Supplier> supplier) {
        log.debug("filter products ");
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        // this sets the desired result type (in this case Product)
        CriteriaQuery<Product> criteriaQuery = criteriaBuilder.createQuery(Product.class);
        Root<Product> root = criteriaQuery.from(Product.class);

        Predicate predicate = criteriaBuilder.and();

        if(name != null && !name.isEmpty())
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.like(root.get("name"), name));

        if(category.isPresent()) {
            Join<Product, Category> join = root.join(Product_.category);
            log.debug("filter by category id {}", category.get().getId());
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(join.get("id"), category.get().getId()));
        }
        if(supplier.isPresent()) {
            Join<Product, Supplier> join = root.join(Product_.supplier);
            log.debug("filter by supplier id {}", supplier.get().getId());
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(join.get("id"), supplier.get().getId()));

        }
        criteriaQuery.select(root).where(predicate);
        TypedQuery<Product> typedQuery = em.createQuery(criteriaQuery);
        log.debug("executing query");
        return typedQuery.getResultList();
    }

}
