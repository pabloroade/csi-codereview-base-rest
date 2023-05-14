package es.udc.fic.csi2122.baserest.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.udc.fic.csi2122.baserest.entity.Sale;

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
public interface SaleRepository extends JpaRepository<Sale, Long> {

  /**
   * Search Sales by client id
   * 
   * @param idClient the client id
   * @return maybe the sale or an empty Optional
   */
  List<Sale> findByIdClient(Long idClient);

    /**
   * Search Sales by product id
   * 
   * @param idProduct the product id
   * @return maybe the sale or an empty Optional
   */
  List<Sale> findByIdProduct(Long idProduct);

  /**
   * Find a sale by id
   * 
   * @param id the id
   * @return the list of clients
   */
  Optional<Sale> findById(Long id);

}