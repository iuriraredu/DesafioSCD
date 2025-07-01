package br.com.iuriraredu.models;

import lombok.Getter;
import lombok.Setter;

/**
 * Modelo que representa um produto retornado pela API.
 * Contém os principais atributos de um produto, como identificador, título, descrição, categoria, preço, estoque, desconto, avaliação, marca e imagem.
 */
@Getter
@Setter
public class Product {
    /**
     * Identificador único do produto.
     */
    private Integer id;

    /**
     * Título ou nome do produto.
     */
    private String title;

    /**
     * Descrição detalhada do produto.
     */
    private String description;

    /**
     * Categoria à qual o produto pertence.
     */
    private String category;

    /**
     * Preço do produto.
     */
    private Double price;

    /**
     * Quantidade disponível em estoque.
     */
    private Integer stock;

    /**
     * Percentual de desconto aplicado ao produto.
     */
    private Double discountPercentage;

    /**
     * Avaliação média do produto.
     */
    private Double rating;

    /**
     * Marca do produto.
     */
    private String brand;

    /**
     * URL da imagem principal (thumbnail) do produto.
     */
    private String thumbnail;
}
