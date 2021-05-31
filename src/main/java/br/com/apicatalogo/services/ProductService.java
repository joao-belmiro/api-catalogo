package br.com.apicatalogo.services;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import br.com.apicatalogo.domain.dto.ProductDto;
import br.com.apicatalogo.domain.models.Product;
import br.com.apicatalogo.exceptions.NotFoundException;
import br.com.apicatalogo.exceptions.RegraException;
import br.com.apicatalogo.repository.ProductRepository;

@Component
public class ProductService {

	@Autowired
	private ProductRepository productRepository;

	public ProductDto salvarProduct(ProductDto productDto) {
		try {
			Product productSalvar = converterEntidade(productDto);
			Product productSalvo = productRepository.save(productSalvar);
			return converterParaDTO(productSalvo);
		} catch (Exception e) {
			throw new RegraException(e.getMessage());
		}

	}

	public ProductDto alterarProduct(Long id, ProductDto productDto) {
		Product productLocalizado = productRepository.findById(id)
				.orElseThrow(() -> new NotFoundException("Produto não localizado"));
		BeanUtils.copyProperties(productDto, productLocalizado, "id");
		Product productAlterado = productRepository.save(productLocalizado);
		return converterParaDTO(productAlterado);
	}

	public ProductDto buscarPorId(Long id) {
		Product productLocalizado = productRepository.findById(id)
				.orElseThrow(() -> new NotFoundException("Produto não localizado"));
		return converterParaDTO(productLocalizado);
	}

	public List<ProductDto> todosProdutos() {
		return productRepository.findAll().stream().map(product -> converterParaDTO(product))
				.collect(Collectors.toList());
	}

	public List<ProductDto> filtroProducts(String p, BigDecimal min_price, BigDecimal max_price) {
		return productRepository.buscarProdutos(p, min_price, max_price).stream()
				.map(product -> converterParaDTO(product)).collect(Collectors.toList());
	}

	public void excluirProduct(Long id) {
		Optional<Product> productLocalizado = productRepository.findById(id);
		if (productLocalizado.isPresent()) {
			productRepository.deleteById(id);
		} else {
			throw new NotFoundException("Produto não localizado");
		}
	}

	public ProductDto converterParaDTO(Product product) {
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(product, ProductDto.class);
	}

	public Product converterEntidade(ProductDto productDto) {
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(productDto, Product.class);
	}
}
