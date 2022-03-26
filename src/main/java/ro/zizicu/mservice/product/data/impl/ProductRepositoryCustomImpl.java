package ro.zizicu.mservice.product.data.impl;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ro.zizicu.mservice.product.data.ProductRepositoryCustom;
import ro.zizicu.mservice.product.entities.Category;
import ro.zizicu.mservice.product.entities.Product;
import ro.zizicu.mservice.product.entities.Supplier;

@Repository
@Slf4j
@AllArgsConstructor
public class ProductRepositoryCustomImpl implements ProductRepositoryCustom {

		
	private final EntityManager em;
	
	@Override
	public Optional<List<Product>> find(String name, Optional<Category> category, Optional<Supplier> supplier) {
		log.debug("find ");
		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		CriteriaQuery<Product> criteriaQuery = criteriaBuilder.createQuery(Product.class);
		Root<Product> root = criteriaQuery.from(Product.class);
		Optional<Predicate>	categoryPredicate = category.isEmpty() ? Optional.empty() : 
							Optional.of(criteriaBuilder.equal(root.get("category"), category));  
		Optional<Predicate>	supplierPredicate = supplier.isEmpty() ? Optional.empty() : 
							Optional.of(criteriaBuilder.equal(root.get("supplier"), supplier));
		Optional<Predicate>	namePredicate = name == null ? Optional.empty() : 
							Optional.of(criteriaBuilder.like(root.get("name"), name));
		if(categoryPredicate.isPresent()) criteriaQuery.where(categoryPredicate.get());
		if(supplierPredicate.isPresent()) criteriaQuery.where(supplierPredicate.get());
		if(namePredicate.isPresent()) criteriaQuery.where(namePredicate.get());
		TypedQuery<Product> typedQuery = em.createQuery(criteriaQuery);
		log.debug("executing query");
		return Optional.of(typedQuery.getResultList());
	}
	
}
