package br.com.apicatalogo.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.apicatalogo.domain.models.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
	
	@Query("SELECT p FROM Product p WHERE "
			+ "(:p IS NULL OR p.name = :p) AND "
			+ "(:p IS NULL OR p.description = :p) AND "
			+ "(:min_price IS NULL OR p.price >= :min_price) AND "
			+ "(:max_price IS NULL OR p.price <= :max_price)")
	List<Product> buscarProdutos(@Param("p") String p,@Param("min_price") BigDecimal min_price, @Param("max_price") BigDecimal max_price);

}
