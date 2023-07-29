package api.veiculos.controllers;

import api.veiculos.core.entity.VeiculosEntity;
import api.veiculos.facade.VeiculosFacade;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/veiculos")
@Api(tags = "Veiculos", description = "API para gerenciamento de veiculos")
public class VeiculosController {

    private final VeiculosFacade veiculosFacade;

    @Autowired
    public VeiculosController(VeiculosFacade veiculosFacade) {
        this.veiculosFacade = veiculosFacade;
    }

    @PostMapping("/criar")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Cria um novo veiculo")
    public VeiculosEntity create(@RequestBody VeiculosEntity veiculo) {
        return veiculosFacade.createVeiculo(veiculo);
    }

    @GetMapping("/listar/{id}")
    @ApiOperation(value = "Lista um veiculo por ID")
    @ResponseStatus(HttpStatus.OK)
    public Optional<VeiculosEntity> getById(@PathVariable("id") Long id) {
        return veiculosFacade.getVeiculoById(id);
    }

    @GetMapping("/listar")
    @ApiOperation(value = "Lista todos os veiculo")
    @ResponseStatus(HttpStatus.OK)
    public List<VeiculosEntity> getAll() {
        return veiculosFacade.getAllVeiculos();
    }

    @PutMapping("/alterar/{id}")
    @ApiOperation(value = "Altera dados um veiculo por ID")
    @ResponseStatus(HttpStatus.OK)
    public VeiculosEntity putVeiculo(@PathVariable("id") Long id,
                                     @RequestBody VeiculosEntity veiculo) {

        return veiculosFacade.updateVeiculo(id, veiculo);
    }

    @DeleteMapping("/deletar/{id}")
    @ApiOperation(value = "Deleta um veiculo por ID")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Optional<VeiculosEntity> deleteVeiculo(@PathVariable("id") Long id) {
        return veiculosFacade.deleteVeiculo(id);
    }

}
