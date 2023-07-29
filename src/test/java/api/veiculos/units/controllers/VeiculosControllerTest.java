package api.veiculos.units.controllers;

import api.veiculos.controllers.VeiculosController;
import api.veiculos.core.entity.VeiculosEntity;
import api.veiculos.facade.VeiculosFacade;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

public class VeiculosControllerTest {

    @Mock
    private VeiculosFacade veiculosFacade;

    @InjectMocks
    private VeiculosController veiculosController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreate() {
        VeiculosEntity veiculo = new VeiculosEntity();
        veiculo.setMarca("Ford");
        veiculo.setModelo("Fiesta");

        when(veiculosFacade.createVeiculo(any())).thenReturn(veiculo);

        VeiculosEntity result = veiculosController.create(veiculo);

        assertEquals(veiculo, result);
    }

    @Test
    void testGetById() {

        Long id = 1L;
        VeiculosEntity veiculo = new VeiculosEntity();
        veiculo.setId(id);
        veiculo.setMarca("Ford");
        veiculo.setModelo("Fiesta");

        when(veiculosFacade.getVeiculoById(id)).thenReturn(Optional.of(veiculo));

        Optional<VeiculosEntity> result = veiculosController.getById(id);

        assertEquals(veiculo, result.orElse(null));
    }

    @Test
    void testGetAll() {

        List<VeiculosEntity> veiculos = new ArrayList<>();
        VeiculosEntity veiculo1 = new VeiculosEntity();
        veiculo1.setMarca("Ford");
        veiculo1.setModelo("Fiesta");
        veiculos.add(veiculo1);

        VeiculosEntity veiculo2 = new VeiculosEntity();
        veiculo2.setMarca("Chevrolet");
        veiculo2.setModelo("Onix");
        veiculos.add(veiculo2);

        when(veiculosFacade.getAllVeiculos()).thenReturn(veiculos);

        List<VeiculosEntity> result = veiculosController.getAll();

        assertEquals(veiculos, result);
    }

    @Test
    void testPutVeiculo() {

        Long id = 1L;
        VeiculosEntity veiculoExistente = new VeiculosEntity();
        veiculoExistente.setId(id);
        veiculoExistente.setMarca("Ford");
        veiculoExistente.setModelo("Fiesta");

        VeiculosEntity veiculoAtualizado = new VeiculosEntity();
        veiculoAtualizado.setId(id);
        veiculoAtualizado.setMarca("Chevrolet");
        veiculoAtualizado.setModelo("Onix");

        when(veiculosFacade.updateVeiculo(id, veiculoAtualizado)).thenReturn(veiculoExistente);

        VeiculosEntity result = veiculosController.putVeiculo(id, veiculoAtualizado);

        assertEquals(veiculoExistente, result);
    }

    @Test
    void testDeleteVeiculo() {

        Long id = 1L;
        VeiculosEntity veiculoExistente = new VeiculosEntity();
        veiculoExistente.setId(id);
        veiculoExistente.setMarca("Ford");
        veiculoExistente.setModelo("Fiesta");

        when(veiculosFacade.deleteVeiculo(id)).thenReturn(Optional.of(veiculoExistente));

        Optional<VeiculosEntity> result = veiculosController.deleteVeiculo(id);

        assertEquals(veiculoExistente, result.orElse(null));
    }



}
