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

    @Autowired
    private VeiculosRepository veiculosRepository;

    @Autowired
    private VeiculoValidator veiculoValidator;

    @Autowired
    private VeiculoBuilder veiculoBuilder;

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
            throw new IllegalArgumentException("Veículo não encontrado ou ID inválido.");
        }
    }

    public Optional<VeiculosEntity> deleteVeiculo(Long id) {
        Optional<VeiculosEntity> veiculo = veiculoBuilder.builderById(id);
        veiculo.ifPresent(veiculos -> veiculosRepository.deleteById(veiculos.getId()));
        return veiculo;
    }
}