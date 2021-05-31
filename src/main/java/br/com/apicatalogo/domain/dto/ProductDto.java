package br.com.apicatalogo.domain.dto;

import java.math.BigDecimal;

import javax.validation.constraints.NotNull;

public class ProductDto {

	private Long id;

	@NotNull
	private String name;

	@NotNull
	private String description;

	@NotNull
	private BigDecimal price;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String descrition) {
		this.description = descrition;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

}
