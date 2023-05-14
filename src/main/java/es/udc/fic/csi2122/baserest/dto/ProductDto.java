package es.udc.fic.csi2122.baserest.dto;

/**
 * Dto class for controller responses
 * 
 * @param name        the product name
 * @param price       the product price
 * @param stock       the product stock
 * @param description the product description
 * 
 * @author pabloroade
 */
public record ProductDto(String name, Double price, Integer stock, String description) {
}
