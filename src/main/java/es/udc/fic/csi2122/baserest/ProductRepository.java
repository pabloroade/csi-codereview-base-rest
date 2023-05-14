package es.udc.fic.csi2122.baserest.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.udc.fic.csi2122.baserest.entity.Product;

/**
 * The user repository (aka DAO)
 * 
 * We extend {@link JpaRepository} indicating the type of the entity and the
 * type of the id
 * 
 * {@link JpaRepository} provides us already with some CRUD operations
 * 
 * @author pabloroade
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

  /**
   * Search Product by name.
   * 
   * @param name the product name
   * @return maybe the product or an empty Optional
   */
  Optional<Product> findOneByName(String name);

  /**
   * Find a product by id
   * 
   * @param id the id
   * @return the list of clients
   */
  Optional<Product> findById(Long id);

}