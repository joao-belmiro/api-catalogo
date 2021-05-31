package br.com.apicatalogo.controller;

import java.math.BigDecimal;
import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.apicatalogo.controller.openApi.ProductControllerOpenApi;
import br.com.apicatalogo.domain.dto.ProductDto;
import br.com.apicatalogo.services.ProductService;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("products")
@Tag(name = "Products Controller", description = "Gerenciamento de produtos")
public class ProductController implements ProductControllerOpenApi{

	@Autowired
	private ProductService productService;

	@Override
	@PostMapping
	public ResponseEntity<ProductDto> salvar(@RequestBody  ProductDto productDto) {
		ProductDto productDtoSalvo = productService.salvarProduct(productDto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
				.buildAndExpand(productDtoSalvo.getId()).toUri();
		return ResponseEntity.created(uri).body(productDtoSalvo);

	}

	@Override
	@PutMapping("{id}")
	public ResponseEntity<ProductDto> alterar(@PathVariable Long id, @RequestBody @Valid ProductDto productDto) {
		ProductDto productDtoAlterado = productService.alterarProduct(id,productDto);
		return ResponseEntity.ok(productDtoAlterado);

	}

	@Override
	@GetMapping("{id}")
	public ResponseEntity<ProductDto> buscarPorId(@PathVariable Long id) {
		ProductDto productLocalizado = productService.buscarPorId(id);
		return ResponseEntity.ok(productLocalizado);

	}
	
	@Override
	@GetMapping
	public ResponseEntity<List<ProductDto>> findAll() {
		return ResponseEntity.ok(productService.todosProdutos());

	}

	@Override
	@GetMapping("search")
	public ResponseEntity<List<ProductDto>> filtroProducts(@RequestParam(required = false) String q,
			@RequestParam(required = false) BigDecimal min_price,
			@RequestParam(required = false) BigDecimal max_price) {
		return ResponseEntity.ok(productService.filtroProducts(q, min_price, max_price));

	}

}
