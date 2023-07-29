package api.veiculos.service;

import api.veiculos.core.builder.VeiculoBuilder;
import api.veiculos.core.entity.VeiculosEntity;
import api.veiculos.repository.VeiculosRepository;
import api.veiculos.validator.VeiculoValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class VeiculosService {

    private final VeiculosRepository veiculosRepository;
    private final VeiculoValidator veiculoValidator;
    private final VeiculoBuilder veiculoBuilder;

    @Autowired
    public VeiculosService(VeiculoValidator veiculoValidator, VeiculoBuilder veiculoBuilder,
                           VeiculosRepository veiculosRepository) {
        super();
        this.veiculoBuilder = veiculoBuilder;
        this.veiculosRepository = veiculosRepository;
        this.veiculoValidator = veiculoValidator;
    }

    public List<VeiculosEntity> getAllVeiculos() {
        return veiculosRepository.findAll();
    }

    public VeiculosEntity createVeiculo(VeiculosEntity veiculo) {
        return veiculosRepository.save(veiculo);
    }

    public Optional<VeiculosEntity> getVeiculoFindById(Long id) {
        return veiculoBuilder.builderById(id);
    }

    public VeiculosEntity putVeiculo(Long id, VeiculosEntity veiculoAtualizado) {
        Optional<VeiculosEntity> veiculoExistente = veiculoValidator.existVeiculoById(id, veiculosRepository);

        if (veiculoExistente.isPresent()) {
            VeiculosEntity veiculoExistenteObj = veiculoExistente.get();
            veiculoExistenteObj = veiculoBuilder.builderGenerete(veiculoExistenteObj, veiculoAtualizado);

            return veiculosRepository.save(veiculoExistenteObj);
        } else {
            throw new IllegalArgumentException("Veiculo nao encontrado ou ID invalido.");
        }
    }

    public Optional<VeiculosEntity> deleteVeiculo(Long id) {
        Optional<VeiculosEntity> veiculo = veiculoBuilder.builderById(id);
        veiculo.ifPresent(veiculos -> veiculosRepository.deleteById(veiculos.getId()));
        return veiculo;
    }
}
