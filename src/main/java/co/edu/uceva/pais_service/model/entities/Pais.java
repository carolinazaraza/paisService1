package co.edu.uceva.pais_service.model.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
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

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Provincia> provincias;
}
