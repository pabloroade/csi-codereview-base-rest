package es.udc.fic.csi2122.baserest.conversors;

import java.util.List;

import es.udc.fic.csi2122.baserest.dto.SaleDto;
import es.udc.fic.csi2122.baserest.entity.Sale;

/**
 * Utility class with conversions between {@link Sale} and {@link SaleDto}
 * 
 * @author pabloroade
 */
public class SaleConversors {

    /**
     * Private constructor to avoid instantiation
     */
    private SaleConversors() {
    }

    /**
     * Convert to a {@link SaleDto}
     * 
     * @param sale the sale entity
     * @return a sale dto
     */
    public static SaleDto toSaleDto(Sale sale) {
        return new SaleDto(sale.getId(), sale.getProduct().getId(), sale.getProduct().getName(),
                sale.getClient().getId(), sale.getClient().getFirstName(), sale.getClient().getLastName(), sale.getUnitsSold(), sale.getSalePrice());
    }

    /**
     * Convert a {@link List} of {@link Sale} to a {@link List} of {@link SaleDto}
     * 
     * @param sales the list of sale entities
     * @return a list of sale dtos
     */
    public static List<SaleDto> toSaleDtoList(List<Sale> sales) {
        return sales.stream().map(SaleConversors::toSaleDto).toList();
    }

    /**
     * Convert a {@link SaleDto} to a {@link Sale}
     * 
     * @param saleDto the sale dto
     * @return a sale entity
     *
    public static Sale toSale(SaleDto saleDto) {
        return new Sale(saleDto.ProductId(), saleDto.toClient(saleDto.getClientId()),
                saleDto.UnitsSold(), saleDto.SalePrice());
    }*/

}
