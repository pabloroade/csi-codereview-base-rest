package es.udc.fic.csi2122.baserest.dto;

/**
 * Dto class for client responses
 * 
 * @param id            the client id
 * @param firstName     the client first name
 * @param lastName      the client last name
 * @param address       the client address
 * @param paymentMethod the client payment method
 * 
 * @author pabloroade
 */
public record ClientDto(Long id, String firstName, String lastName, String address, String paymentMethod) {
}
