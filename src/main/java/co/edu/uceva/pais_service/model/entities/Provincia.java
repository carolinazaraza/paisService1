package co.edu.uceva.pais_service.model.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @JsonIgnore  //Ignoramos este atributo en la generacion del json, para evitar la referencia circular
    @ManyToOne(fetch = FetchType.LAZY) //Datos de pais no se cargarán automáticamente cuando se cargue entidad provincia
    @JoinColumn(name="id_pais")
    private Pais pais;

    @JsonIgnore
    public Pais getPais() {
        return pais;
    }

}
