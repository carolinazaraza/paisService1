package co.edu.uceva.pais_service.model.service;

import co.edu.uceva.pais_service.model.dao.IPaisDao;
import co.edu.uceva.pais_service.model.entities.Pais;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Clase implementa el servicio de pais
 * Un servicio es un objeto controlado por el contenedor de Spring
 */
@Service
public class PaisServiceImpl implements IPaisService{

    @Autowired
    IPaisDao paisDao;

    @Override
    public Pais save(Pais pais) {
        return paisDao.save(pais);
    }

    @Override
    public void delete(Pais pais) {
        paisDao.delete(pais);
    }

    @Override
    public Pais update(Pais pais) {
        return paisDao.save(pais);
    }

    /**
     * Este metodo lista los paises guardados
     * @return una lista de paises
     */
    @Override
    public List<Pais> findAll() {
        return (List<Pais>) paisDao.findAll();
    }

    /**
     * Este metodo busca un pais
     * @param id Llave primaria del pais que se quiere buscar
     * @return el pais identificado por el campo id
     */
    @Override
    public Pais findById(Long id) {
        return paisDao.findById(id).get();
    }
}
