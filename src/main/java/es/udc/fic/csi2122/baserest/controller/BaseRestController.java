package es.udc.fic.csi2122.baserest.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import es.udc.fic.csi2122.baserest.conversors.ClientConversors;
import es.udc.fic.csi2122.baserest.dto.ClientDto;
import es.udc.fic.csi2122.baserest.entity.Client;
import es.udc.fic.csi2122.baserest.repository.ClientRepository;

/**
 * Example spring REST controller
 * 
 * The {@link RequestMapping} annotation indicates the base path for all
 * petitions that this
 * controller will handle. the path for the endpoints defined is appended to
 * this path
 * 
 * @author alfonso.landin@udc.es
 */
@RestController
@Transactional
@RequestMapping("client")
public class BaseRestController {

  private static final Logger logger = LoggerFactory.getLogger(BaseRestController.class);

  @PersistenceContext
  private EntityManager em;

  private ClientRepository clientRepository;

  /**
   * Constructor dependency injection signaled by the {@link Autowired} annotation
   * 
   * @param clientRepository the client repository
   */
  @Autowired
  public BaseRestController(ClientRepository clientRepository) {
    this.clientRepository = clientRepository;
  }

  /**
   * Get all clients
   * 
   * @return a list of all the clients
   */
  @GetMapping(value = "all")
  public List<ClientDto> getClients() {
    return ClientConversors.toClientDtoList(clientRepository.findAll());
  }

  /**
   * Get client by id
   * 
   * The ResponseEntity.of static method will return a 404 response if the
   * Optional&lt;Client&gt; is empty.
   * 
   * @param id the id of the client
   * @return the client or not found
   */
  @GetMapping(value = "{id}")
  public ResponseEntity<ClientDto> get(@PathVariable Long id) {
    logger.info("Fetching client with id: {}", id);
    var client = clientRepository.findById(id);

    return ResponseEntity.of(client.map(u -> {
      logger.info("Found client with id {}: {}", id, u);
      return ClientConversors.toClientDto(u);
    }));
  }

  /**
   * Create a new client
   * 
   * We use the JPA EntityManager to do the operation. Errors during this
   * operation are not handled and will results in an exceptions thrown and a 500
   * Internal Server Error response
   * 
   * Alternatively we could use the save() method of the ClientRepository.
   * 
   * @param client the new client
   * @return the id of the new client
   */
  @PostMapping(value = "new")
  public Long newClient(@RequestBody ClientDto client) {
    logger.info("Creating new client: {}", client);
    var newClient = em.merge(ClientConversors.toClient(client));
    logger.info("Created client: {}", newClient);
    return newClient.getId();
  }

  /**
   * Search clients
   * 
   * Parameters are optional, but at least one is required. sIf no query parameter
   * is provided a 400 Bad Response is issued.
   * 
   * @param name      the name of the client
   * @param olderThan the minimum age
   * @return a list of clients
   */
  @GetMapping(value = "search")
  public ResponseEntity<List<ClientDto>> search(@RequestParam Optional<String> name,
      @RequestParam(name = "older-than") Optional<Integer> olderThan) {

    final List<Client> clients;

    if (name.isEmpty() && olderThan.isEmpty()) {
      return ResponseEntity.badRequest().build();
    } else if (name.isPresent() && olderThan.isEmpty()) {
      clients = clientRepository.findOneByName(name.get())
          .map(Arrays::asList)
          .orElseGet(Arrays::asList);
    } else if (name.isEmpty()) {
      clients = clientRepository.findByAgeGreaterThan(olderThan.get());
    } else {
      clients = clientRepository.findByNameAndAgeGreaterThan(name.get(), olderThan.get());
    }

    return ResponseEntity.ok(ClientConversors.toClientDtoList(clients));
  }
}
