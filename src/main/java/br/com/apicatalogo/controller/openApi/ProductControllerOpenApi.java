package br.com.apicatalogo.controller.openApi;


import java.math.BigDecimal;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import br.com.apicatalogo.domain.dto.ProductDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;

public interface ProductControllerOpenApi {
	
	@Operation(tags="salvar", description="salvo novo produto" ,summary = "salvar novo produto", operationId = "salvar")
	ResponseEntity<ProductDto> salvar( ProductDto productDto);
	
	@Operation(tags="alterar", description="Altera um produto" ,summary = "altera um produto", operationId = "alterar")
	@Parameter(description = "código do produto a ser alterado", name="id", required = true)
	ResponseEntity<ProductDto> alterar(Long id,  ProductDto productDto);
	
	@Operation(tags="buscarPorId", description="Busca um porduto na base comforme o código inserido" ,summary = "busca por id", operationId = "buscarPorId")
	@Parameter(description = "código do produto", name="id", required = true)
	ResponseEntity<ProductDto> buscarPorId(@PathVariable Long id);
	
	@Operation(tags="findAll", description="retorna todos dos produtos cadastrados" ,summary = "buscar todos", operationId = "findAll")
	ResponseEntity<List<ProductDto>> findAll();
	
	@Operation(tags="buscarPorId", description="Busca um porduto na base comforme o código inserido" ,summary = "busca por id", operationId = "buscarPorId")
	@Parameter(description = "pode ser tanto a descrição quanto nome do produto", name="q", required = false)
	@Parameter(description = "preço mínimo do produto", name="min_price", required = false)
	@Parameter(description = "preço máximo dp produto", name="max_price", required = false)
	ResponseEntity<List<ProductDto>> filtroProducts(String q, BigDecimal min_price,BigDecimal max_price);
}
