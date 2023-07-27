package api.veiculos.validator;

import api.veiculos.core.entity.VeiculosEntity;
import api.veiculos.repository.VeiculosRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;


@Component
public class VeiculoValidator {

    public Optional<VeiculosEntity> existVeiculoById(Long id,
                                                     VeiculosRepository veiculosRepository) {
        return veiculosRepository.findById(id);
    }


}
