//package com.project.sales.services;
//
//import com.project.sales.DTO.ClientDTO;
//import com.project.sales.DTO.ClientRetrieve;
//import com.project.sales.mapper.ClientMapper;
//import com.project.sales.model.Client;
//import com.project.sales.repository.ClientRepository;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Nested;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import java.time.LocalDate;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.any;
//import static org.mockito.Mockito.when;
//import static org.mockito.Mockito.times;
//
//@ExtendWith(MockitoExtension.class)
//class ClientServiceImpTest {
//
//    private final Long id = 1L;
//
//    @Mock
//    private ClientRepository clientRepository;
//
//    @InjectMocks
//    private ClientServiceImpl clientService;
//
//    @Mock
//    private ClientMapper clientMapper;
//
//    private ClientRetrieve buildClientRetrieve() {
//        return new ClientRetrieve(id, "59935134032", "João Silva",
//                "joao.silva@example.com",
//                LocalDate.of(1990, 1, 1), false);
//    }
//
//    private List<Client> buildClientList() {
//        List<Client> clientList = new ArrayList<>();
//
//        Client client = new Client(id,"59935134032", "Silva Silva",
//                "silva@example.com",
//                LocalDate.of(1989, 2, 20), false);
//
//        clientList.add(client);
//
//        clientList.add(buildClient());
//
//        return clientList;
//    }
//    private Client buildClient() {
//        return new Client(id, "59935134032", "João Silva",
//                "joao.silva@example.com",
//                LocalDate.of(1990, 1, 1), false);
//    }
//
//    private ClientDTO buildClientDTO() {
//        return new ClientDTO("59935134032", "João Silva",
//                "joao.silva@example.com",
//                LocalDate.of(1990, 1, 1), false);
//    }
//
//    @Nested
//    class CreateClient {
//
//        @Test
//        @DisplayName("Should Create Client with success")
//        void givenClientDTO_whenCreate_thenReturnClientRetrieve() {
//            // Arrange
//            Client client = buildClient();
//
//            ClientDTO clientDTO = buildClientDTO();
//
//            ClientRetrieve clientRetrieve = buildClientRetrieve();
//
//            // when
//            when(clientMapper.fromDTO(clientDTO)).thenReturn(client);
//            when(clientMapper.fromEntity(client)).thenReturn(clientRetrieve);
//            when(clientRepository.save(any(Client.class))).thenReturn(client);
//
//            // Act
//            var result = clientService.create(clientDTO);
//
//            // Assert
//            assertNotNull(result);
//
//            assertEquals(result, clientRetrieve);
//        }
//    }
//
//    @Nested
//    class UpdateClient {
//
//        @Test
//        @DisplayName("Successfully changes a client")
//        void givenClientDTO_whenUpdate_thenReturnClientRetrieve() {
//            // Arrange
//            Client client = buildClient();
//
//            Client updatedClient = buildClient();
//
//            updatedClient.setName("Name update");
//
//            ClientDTO clientDTO = buildClientDTO();
//
//            clientDTO.setName("Name update");
//
//            ClientRetrieve clientRetrieve = buildClientRetrieve();
//
//            //when
//            when(clientRepository.findById(id)).thenReturn(Optional.of(client));
//            when(clientMapper.fromDTO(clientDTO)).thenReturn(client);
//            when(clientMapper.fromEntity(updatedClient)).thenReturn(clientRetrieve);
//            when(clientRepository.save(any(Client.class))).thenReturn(updatedClient);
//
//            // Act
//            var result = clientService.update(id, clientDTO);
//
//            // Assert
//            assertNotNull(result);
//
//            assertEquals(client.getName(), result.getName());
//        }
//    }
//
//    @Nested
//    class ListAllClients {
//
//        @Test
//        @DisplayName("Find all clients")
//        void givenListClients_whengetFindAllClients_thenReturnClientList() {
//            // Arrange
//            List<Client> list = buildClientList();
//
//            Client client = new Client(id,"59935134032", "Silva Silva",
//                    "silva@example.com",
//                    LocalDate.of(1989, 2, 20), false);
//
//            //when
//            when(clientRepository.findAll()).thenReturn(list);
//
//            // Act
//            var result = clientService.findAll();
//
//            // Assert
//            assertNotNull(result);
//
//            assertEquals(list.get(0), client);
//        }
//    }
//
//    @Nested
//    class DeleteClient {
//
//        @Test
//        @DisplayName("alter atributo isDeleted to true")
//        void givenCPF_whenDelete_returnClientRetrieveDeleted() {
//            // Arrange
//            Client client = buildClient();
//
//            Client clientUpdate = new Client(1L,"59935134032", "João Silva",
//                    "joao.silva@example.com",
//                    LocalDate.of(1990, 1, 1), false);
//
//            clientUpdate.setDeleted(true);
//            // when
//            when(clientRepository.findById(id)).thenReturn(Optional.of(client));
//
//            when(clientRepository.save(any(Client.class))).thenReturn(clientUpdate);
//
//            // Act
//            clientService.delete(id);
//
//            // Assert
//            assertTrue(clientUpdate.isDeleted());
//            verify(clientRepository, times(1)).save(client);
//        }
//    }
//}