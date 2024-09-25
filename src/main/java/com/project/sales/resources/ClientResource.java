package com.project.sales.resources;

import com.project.sales.DTO.ClientDTO;
import com.project.sales.DTO.ClientRetrieve;
import com.project.sales.model.Client;
import com.project.sales.services.ClientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/client")
@Tag(name = "clientes", description = "Documentação do resource Client")
public class ClientResource  {

    private final ClientService clientService;

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE})
    @Operation(summary = "Persiste um novo cliente no Banco de Dados",
            description = "Persiste um novo cliente no Banco de Dados, caso ele não encontre outro com o mesmo CPF." +
                          "Para o caso de encontrar, ele retorna a resposta Conflict",
            responses = {
                    @ApiResponse(description = "Created", responseCode = "201", content = @Content(schema = @Schema(implementation = Client.class))),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Conflict", responseCode = "409", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
            })
    public ResponseEntity<ClientRetrieve> create(@Valid @RequestBody ClientDTO clientDTO) {
        return new ResponseEntity<>(clientService.create(clientDTO), HttpStatus.CREATED);
    }

    @GetMapping(value = "/{cpf}", produces = {MediaType.APPLICATION_JSON_VALUE})
    @Operation(summary = "Buscar cliente pelo CPF", description = "Procura um cliente pela CPF. Caso não encontre, retorna um NOT_FOUND",
            responses = {
                    @ApiResponse(description = "OK", responseCode = "200", content = @Content(schema = @Schema(implementation = Client.class))),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
            })
    public ResponseEntity<ClientRetrieve> findByCpf(@PathVariable String cpf) {
        return new ResponseEntity<>(clientService.findByCpf(cpf), HttpStatus.OK);
    }

    @Operation(summary = "Buscar cliente pelo ID", description = "Procura um cliente por ID. Caso não encontre, retorna um NOT_FOUND",
            responses = {
                    @ApiResponse(description = "OK", responseCode = "200", content = @Content(schema = @Schema(implementation = Client.class))),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
            })
    @GetMapping(value = "/search/{id}")
    public ResponseEntity<ClientRetrieve> findById(@PathVariable Long id) {
        return new ResponseEntity<>(clientService.findById(id), HttpStatus.OK);
    }

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    @Operation(summary = "Busca todos os clientes",
            description = "Lista todos os clientes cadastrados no sistema.",
            responses = {
                        @ApiResponse(description = "Sucesso", responseCode = "200", content = {
                        @Content (mediaType = MediaType.APPLICATION_JSON_VALUE, array = @ArraySchema(schema = @Schema(implementation = Client.class)))}),
                        @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                        @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                        @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
            })
    public ResponseEntity<List<ClientRetrieve>> findAll() {
        return new ResponseEntity<>(clientService.findAll(), HttpStatus.OK);
    }

    @PutMapping(value = "/{id}",consumes = {MediaType.APPLICATION_JSON_VALUE})
    @Operation(summary = "Atualiza os dados de um cliente", description = "Busca o cliente, atualiza, persiste e retorna o cliente atualizado.",
            responses = {
                    @ApiResponse(description = "OK", responseCode = "200", content = @Content(schema = @Schema(implementation = Client.class))),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
            })
    public ResponseEntity<ClientRetrieve> update(@PathVariable Long id , @Valid @RequestBody ClientDTO clientDTO) {
        return new ResponseEntity<>(clientService.update(id, clientDTO), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    @Operation(summary = "Desativa um cliente através da ID", description = "Busca um cliente pelo ID fornecido e se encontrar, muda atributo isDeleted para true",
            responses = {
                    @ApiResponse(description = "OK", responseCode = "200", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
            })
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        clientService.delete(id);

        return  new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
