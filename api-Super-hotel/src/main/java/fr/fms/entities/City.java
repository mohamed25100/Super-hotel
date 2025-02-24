package fr.fms.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "T_cities")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class City implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    // Relation avec les hôtels (si nécessaire)
    @OneToMany(mappedBy = "city")
    @JsonIgnore
    private List<Hotel> hotels;
}
