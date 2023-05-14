package es.udc.fic.csi2122.baserest.dto;

import es.udc.fic.csi2122.baserest.entity.Sale;
import es.udc.fic.csi2122.baserest.entity.Client;
import es.udc.fic.csi2122.baserest.entity.Product;

/**
 * Dto class for sales
 * 
 * @param id         the sale id
 * @param productId  the product id
 * @param productName the product name
 * @param clientId   the client id
 * @param clientFisrtName the client name1
 * @param clientLastName the client name2
 * @param unitsSold  the units sold
 * @param salePrice  the sale price
 * 
 * @author pabloroade
 */
public record SaleDto(Long id, Long productId, String productName, Long clientId, String clientFisrtName, String clientLastName, int unitsSold, double salePrice) {
    
    public static SaleDto fromSale(Sale sale) {
        Product product = sale.getProduct();
        Client client = sale.getClient();
        return new SaleDto(sale.getId(), product.getId(), product.getName(), client.getId(), client.getFirstName(), client.getLastName(), sale.getUnitsSold(), sale.getSalePrice());
    }
    
}
