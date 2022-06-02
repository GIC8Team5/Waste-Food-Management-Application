package net.guides.springboot2.springboot2jpacrudexample.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import net.guides.springboot2.springboot2jpacrudexample.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{
	@Query("SELECT u FROM Product u WHERE u.emailId = ?1")
    public Product findByEmail(String email);
	
	@Query("SELECT u FROM Product u WHERE u.emailId = ?1")
    public List<Product> findByEmailList(String email);
}
