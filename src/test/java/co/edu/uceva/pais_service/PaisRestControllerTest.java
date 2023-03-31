package co.edu.uceva.pais_service;

import co.edu.uceva.pais_service.model.entities.Pais;
import co.edu.uceva.pais_service.model.service.IPaisService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PaisRestControllerTest {

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @Autowired
    private IPaisService paisService;

    @Before
    public void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    /**
     * Prueba del hola mundo
     * @throws Exception
     */
    @Test
    public void testHolaMundo() throws Exception {
        String nombre = "Juan";
        this.mockMvc.perform(get("/pais-service/hola/{nombre}", nombre))
                .andExpect(status().isOk())
                .andExpect(content().string("Hola " + nombre));
    }

    /**
     * Prueba de listar paises
     * @throws Exception
     */
    @Test
    public void testListar() throws Exception {
        Pais pais1 = new Pais(null, "Croacia");
        Pais pais2 = new Pais(null, "España");
        paisService.save(pais1);
        paisService.save(pais2);
        List<Pais> listaPaises = new ArrayList<>();
        listaPaises.add(pais1);
        listaPaises.add(pais2);

        this.mockMvc.perform(get("/pais-service/paises"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].nombre", is(pais1.getNombre())))
                .andExpect(jsonPath("$[1].nombre", is(pais2.getNombre())));

        paisService.delete(pais1);
        paisService.delete(pais2);
    }

    /**
     * Prueba de buscar un pais por su id
     * @throws Exception
     */
    @Test
    public void testBuscarPais() throws Exception {
        Pais pais = new Pais(null, "España");
        paisService.save(pais);

        this.mockMvc.perform(get("/pais-service/paises/{id}", pais.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre", is(pais.getNombre())));

        paisService.delete(pais);
    }

    @Test
    public void testCrearPais() throws Exception {
        Pais pais = new Pais(null, "España");

        this.mockMvc.perform(post("/pais-service/paises")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(pais)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre", is(pais.getNombre())));

        paisService.delete(pais);
    }

    @Test
    public void testActualizarPais() throws Exception {
        Pais pais = new Pais(null, "España");
        paisService.save(pais);
        pais.setNombre("Portugal");

        this.mockMvc.perform(put("/pais-service/paises")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(pais)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre", is(pais.getNombre())));
//                .andExpect(jsonPath("$.capital", is(pais.getNombre())));

        paisService.delete(pais);
    }

    @Test
    public void testBorrarPais() throws Exception {
        Pais pais = new Pais(null, "España");
        paisService.save(pais);

        this.mockMvc.perform(delete("/pais-service/paises/{id}", pais.getId()))
                .andExpect(status().isOk());

        assertNull(paisService.findById(pais.getId()));
    }

    /**
     * Método para convertir un objeto a una cadena JSON
     *
     * @param obj Objeto a convertir
     * @return Cadena JSON
     */
    private String asJsonString(Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
