package com.student.bookshop.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.io.Serializable;
import java.util.Date;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class BookDto implements Serializable {
    private Long id;
    private String title;
    private String publicationYear;
    private Long numberOfPage;
    private String paperFormat;
    private Long availableQuantity;
    private Double price;
    private Long authorId;
    private Long categoryId;
    private Long publisherId;
    private Long languageId;
    private String imageName;

}

