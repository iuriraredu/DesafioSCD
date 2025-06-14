package br.com.iuriraredu.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Product{
    private Integer id;
    private String title;
    private String description;
    private String category;
    private Double price;
    private Integer stock;
    private Double discountPercentage;
    private Double rating;
    private String brand;
    private String thumbnail;
}
