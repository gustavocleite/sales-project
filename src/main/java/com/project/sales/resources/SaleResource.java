package com.project.sales.resources;

import com.project.sales.DTO.SaleDTO;
import com.project.sales.DTO.SaleRetrieve;
import com.project.sales.DTO.StatusDTO;
import com.project.sales.model.Sale;
import com.project.sales.services.SaleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/sale")
@Tag(name = "sale", description = "Documentação do resource Sale")
public class SaleResource{

    private final SaleService saleService;

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE})
    @Operation(summary = "Persiste um nova venda no Banco de Dados",
            description = "Persiste um nova venda no Banco de Dados",
            responses = {
                    @ApiResponse(description = "Created", responseCode = "201", content = @Content(schema = @Schema(implementation = Sale.class))),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
            })
    public ResponseEntity<SaleRetrieve> create(@Valid @RequestBody SaleDTO saleDTO) {
        return new ResponseEntity<>(saleService.create(saleDTO), HttpStatus.CREATED);
    }

    @GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    @Operation(summary = "Buscar venda pelo ID", description = "Procura uma venda pelo id. Caso não encontre, retorna um NOT_FOUND",
            responses = {
                    @ApiResponse(description = "OK", responseCode = "200", content = @Content(schema = @Schema(implementation = Sale.class))),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
            })
    public ResponseEntity<SaleRetrieve> findById(@PathVariable Long id) {
        return new ResponseEntity<>(saleService.findById(id), HttpStatus.OK);
    }

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    @Operation(summary = "Busca todas as vendas",
            description = "Lista todas as vendas cadastrados no sistema.",
            responses = {
                    @ApiResponse(description = "Sucesso", responseCode = "200", content = {
                    @Content (mediaType = MediaType.APPLICATION_JSON_VALUE, array = @ArraySchema(schema = @Schema(implementation = Sale.class)))}),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
            })
    public ResponseEntity<List<SaleRetrieve>> findAll() {
        return new ResponseEntity<>(saleService.findAll(), HttpStatus.OK);
    }


    @PutMapping(value = "/{id}", consumes = {MediaType.APPLICATION_JSON_VALUE})
    @Operation(summary = "Atualiza os dados de uma venda", description = "Busca uma venda, atualiza, persiste e retorna a venda atualizado.",
            responses = {
                    @ApiResponse(description = "OK", responseCode = "200", content = @Content(schema = @Schema(implementation = Sale.class))),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
            })
    public ResponseEntity<SaleRetrieve> update(@PathVariable Long id, @Valid @RequestBody StatusDTO statusDTO) {
        return new ResponseEntity<>(saleService.update(id, statusDTO), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    @Operation(summary = "Desativa uma venda através da ID", description = "Busca uma venda pelo ID fornecido e se encontrar, muda atributo isDeleted para true",
            responses = {
                    @ApiResponse(description = "OK", responseCode = "200", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
            })
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        saleService.delete(id);

        return  new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}


