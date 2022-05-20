package com.example.ecommerce.entity;

import com.example.ecommerce.entity.base.BaseEntity;
import com.example.ecommerce.entity.enums.ProductSimpleStatus;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@ToString
@Table(name = "products")
public class Product extends BaseEntity {
    @Id
//    @GeneratedValue(generator = "uuid")
//    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String slug;
    private String description;
    private String detail;
    private String thumbnails; // nhiều ảnh cách nhau bởi dấu ,
    private BigDecimal price;
    @Enumerated(EnumType.ORDINAL)
    private ProductSimpleStatus status;

}
