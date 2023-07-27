package api.veiculos.core.builder;

import api.veiculos.core.entity.VeiculosEntity;
import api.veiculos.repository.VeiculosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

import static api.veiculos.utils.ValidateEmptyIsNull.isEmptyOrNotNull;

@Component
public class VeiculoBuilder {

    @Autowired
    private VeiculosRepository veiculosRepository;

    public Optional<VeiculosEntity> builderById(Long id) {
        return veiculosRepository.findById(id);
    }

    public VeiculosEntity builderGenerete(VeiculosEntity veiculoExistente, VeiculosEntity veiculoAtualizado) {
        veiculoExistente.setAno(!isEmptyOrNotNull(veiculoAtualizado.getAno()) ? veiculoAtualizado.getAno() : "");
        veiculoExistente.setMarca(!isEmptyOrNotNull(veiculoAtualizado.getMarca()) ? veiculoAtualizado.getMarca() : "");
        veiculoExistente.setModelo(!isEmptyOrNotNull(veiculoAtualizado.getModelo()) ? veiculoAtualizado.getModelo() : "");
        veiculoExistente.setCarroceria(veiculoAtualizado.isCarroceria());

        return veiculoExistente;
    }

}
