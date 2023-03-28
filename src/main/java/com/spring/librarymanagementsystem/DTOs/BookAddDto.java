package com.spring.librarymanagementsystem.DTOs;

import com.spring.librarymanagementsystem.Enums.Genre;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class BookAddDto {
    private String title;
    private int price;
    private Genre genre;
    private int authorId;
}
