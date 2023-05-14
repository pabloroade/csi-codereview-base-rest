package es.udc.fic.csi2122.baserest.conversors;

import java.util.List;

import es.udc.fic.csi2122.baserest.dto.ProductDto;
import es.udc.fic.csi2122.baserest.entity.Product;

/**
 * Utility class with conversions between {@link Product} and {@link ProductDto}.
 * 
 * @author pabloroade
 */
public class ProductConversors {

    /**
     * Private constructor to avoid instantiation.
     */
    private ProductConversors() {
    }

    /**
     * Convert a {@link Product} to a {@link ProductDto}.
     * 
     * @param product the product entity
     * @return a product dto
     */
    public static ProductDto toProductDto(Product product) {
        return new ProductDto(product.getName(), product.getPrice(), product.getStock(), product.getDescription());
    }

    /**
     * Convert a {@link List} of {@link Product} to a {@link List} of {@link ProductDto}.
     * 
     * @param products the list of product entities
     * @return a list of product dtos
     */
    public static List<ProductDto> toProductDtoList(List<Product> products) {
        return products.stream().map(ProductConversors::toProductDto).toList();
    }

    /**
     * Convert a {@link ProductDto} to a {@link Product} without id.
     * 
     * @param product the product dto
     * @return a product entity without id
     */
    public static Product toProduct(ProductDto product) {
        return new Product(product.name(), product.price(), product.stock(), product.description());
    }

}
