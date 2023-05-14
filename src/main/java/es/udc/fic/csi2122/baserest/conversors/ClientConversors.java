package es.udc.fic.csi2122.baserest.conversors;

import java.util.List;

import es.udc.fic.csi2122.baserest.dto.ClientDto;
import es.udc.fic.csi2122.baserest.entity.Client;

/**
 * Utility class with conversions between {@link Client} and {@link ClientDto}
 * 
 * @author pabloroade
 */
public class ClientConversors {

    /**
     * Private constructor to avoid instantiation
     */
    private ClientConversors() {
    }

    /**
     * Convert a {@link Client} to a {@link ClientDto}
     * 
     * @param client the client entity
     * @return a client dto
     */
    public static ClientDto toClientDto(Client client) {
        return new ClientDto(client.getId(), client.getFirstName(), client.getLastName(),
                client.getAddress(), client.getPaymentMethod());
    }

    /**
     * Convert a {@link List} of {@link Client} to a {@link List} of {@link ClientDto}
     * 
     * @param clients the list of client entities
     * @return a list of client dtos
     */
    public static List<ClientDto> toClientDtoList(List<Client> clients) {
        return clients.stream().map(ClientConversors::toClientDto).toList();
    }

    /**
     * Convert a {@link ClientDto} to a {@link Client} without id
     * 
     * @param client the client dto
     * @return a client entity without id
     *
    public static Client toClient(ClientDto client) {
    return new Client(client.FirstName(), client.LastName(),
            client.Address(), client.PaymentMethod());
    }*/

}
