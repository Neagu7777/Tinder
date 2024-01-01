package de.telran.person.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import org.hibernate.annotations.Type;
@Getter
@Data
@Entity
@Table(name = "person")
public class Person {



    @Id
    @Column(name = "Id")
    private Integer id;

    @Column(name = "Name")
    private String name;

    @Column(name = "Surname")
    private String surname;

    @Column(name = "Password")
    private String password;

//    @Column(name = "Status")
//    private String status;

    @Enumerated(EnumType.STRING)
    @Column(name = "Status", columnDefinition = "status_enum")
    private Status status;

    @Column(name = "Description")
    private String description;

    @Column(name = "Rating")
    private int rating;

    public void setRating(int rating) {
        this.rating = rating;
    }

    // Геттеры и сеттеры
}