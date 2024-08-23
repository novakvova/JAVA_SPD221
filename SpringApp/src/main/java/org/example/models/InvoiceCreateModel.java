package org.example.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class InvoiceCreateModel {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String location;
    private MultipartFile image;
    private Double amount;
}
