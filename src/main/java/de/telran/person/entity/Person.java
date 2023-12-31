

package de.telran.person.entity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

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

    @Enumerated(EnumType.STRING) // Указываем, что это enum и используем строковое представление
    @Column(name = "Status")
    private Status status;

    // Геттеры и сеттеры
}
