package api.veiculos.facade;

import api.veiculos.core.entity.VeiculosEntity;
import api.veiculos.service.VeiculosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class VeiculosFacade {

    private final VeiculosService veiculosService;

    @Autowired
    public VeiculosFacade(VeiculosService veiculosService) {
        this.veiculosService = veiculosService;
    }

    public List<VeiculosEntity> getAllVeiculos() {
        return veiculosService.getAllVeiculos();
    }

    public VeiculosEntity createVeiculo(VeiculosEntity veiculo) {
        return veiculosService.createVeiculo(veiculo);
    }

    public Optional<VeiculosEntity> getVeiculoById(Long id) {
        return veiculosService.getVeiculoFindById(id);
    }

    public VeiculosEntity updateVeiculo(Long id, VeiculosEntity veiculoAtualizado) {
        return veiculosService.putVeiculo(id, veiculoAtualizado);
    }

    public Optional<VeiculosEntity> deleteVeiculo(Long id) {
        return veiculosService.deleteVeiculo(id);
    }
}
