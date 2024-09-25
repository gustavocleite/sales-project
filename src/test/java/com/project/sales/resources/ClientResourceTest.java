//package com.project.sales.resources;
//
//import com.project.sales.DTO.ClientDTO;
//import com.project.sales.DTO.ClientRetrieve;
//
//import com.project.sales.services.ClientService;
//
//import org.junit.jupiter.api.Test;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//import org.springframework.web.context.WebApplicationContext;
//
//import static org.mockito.ArgumentMatchers.any;
//
//import static org.mockito.ArgumentMatchers.eq;
//import static org.mockito.Mockito.when;
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.times;
//import static org.mockito.Mockito.verifyNoMoreInteractions;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//import java.time.LocalDate;
//import java.util.Arrays;
//import java.util.List;
//
//@ExtendWith(MockitoExtension.class)
//@WebMvcTest(ClientResource.class)
//class ClientResourceTest {
//
//    private final String apiPatch = "/api/v1/client";
//
//    private final Long id = 1L;
//    @Autowired
//    private MockMvc mockMvc;
//
//    @MockBean
//    private ClientService clientService;
//
//    @Autowired
//    private ObjectMapper objectMapper;
//
//    private ClientDTO clientDTO;
//
//    private ClientRetrieve clientRetrieve;
//
//    @Autowired
//    private WebApplicationContext webApplicationContext;
//
//    @BeforeEach
//    void setup() {
//        clientDTO = new ClientDTO("59935134032", "João Silva", "joao.silva@example.com", LocalDate.of(1990,1,1), false);
//
//        clientRetrieve = new ClientRetrieve(id, "59935134032", "João Silva", "joao.silva@example.com",LocalDate.of(1990,1,1), false);
//
//        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
//    }
//
//    @Test
//    void givenClientDTO_whenPostClient_thenReturnClientRetrieve() throws Exception {
//        when(clientService.create(clientDTO)).thenReturn(clientRetrieve);
//
//        mockMvc.perform(post(apiPatch)
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(clientDTO)))
//                .andExpect(status().isCreated());
//    }
//
//    @Test
//    void givenClientCPF_WhenGetFindByCpf_thenReturnClientRetrieve() throws Exception {
//        when(clientService.findByCpf("59935134032")).thenReturn(clientRetrieve);
//
//        mockMvc.perform(get(apiPatch + "/{cpf}", "59935134032")
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk());
//    }
//
//    @Test
//    void givenListClient_whenGetFindAll_returnListRetrieve() throws Exception {
//        List<ClientRetrieve> expectedClients = Arrays.asList(
//                new ClientRetrieve(id,"59935134032", "João Silva", "joao.silva@example.com",LocalDate.of(1990,1,1), false),
//                new ClientRetrieve(2L,"59935134032", "Silva João", "silva@example.com",LocalDate.of(1989,2,4), false)
//        );
//
//        when(clientService.findAll()).thenReturn(expectedClients);
//
//        mockMvc.perform(get(apiPatch)
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk());
//
//        verify(clientService, times(1)).findAll();
//
//        verifyNoMoreInteractions(clientService);
//    }
//
//    @Test
//    void givenIdClientAndClientDTO_whenPutUpdate_returnClientRetrieve() throws Exception {
//        String cpf = "59935134032";
//
//        ClientRetrieve updatedClient = new ClientRetrieve(id, cpf, "Silvaaaaaa", "silva@example.com", LocalDate.of(1990,1,1), false);
//
//        when(clientService.update(eq(id), any(ClientDTO.class))).thenReturn(updatedClient);
//
//        mockMvc.perform(put(apiPatch + "/{id}", id)
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(clientDTO)))
//                .andExpect(status().isOk());
//
//        verify(clientService, times(1)).update(eq(id), any(ClientDTO.class));
//
//        verifyNoMoreInteractions(clientService);
//    }
//
//    @Test
//    void givenIdClient_whenDelete_returnNoContent() throws Exception {
//        mockMvc.perform(delete(apiPatch + "/{id}", id))
//                .andExpect(status().isNoContent());
//
//        verify(clientService, times(1)).delete(id);
//
//        verifyNoMoreInteractions(clientService);
//    }
//}