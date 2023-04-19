package co.edu.uceva.pais_service.model.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="pais")
public class Pais {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id_pais")
    private Long id;

    private String nombre;

    @JsonProperty("provincias")
    @OneToMany(mappedBy = "pais", cascade = CascadeType.ALL)
    private List<Provincia> provincias = new ArrayList<>();
}
