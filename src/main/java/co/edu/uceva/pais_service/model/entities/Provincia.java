package co.edu.uceva.pais_service.model.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Provincia {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private String provincia;

    @ManyToOne
    @JoinColumn(name="id_pais")
    private Pais pais;
}
