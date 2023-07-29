package api.veiculos.facade;

import api.veiculos.builder.BuilderGenerete;
import api.veiculos.core.entity.VeiculosEntity;
import api.veiculos.service.VeiculosService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class VeiculosFacadeTest {

    private VeiculosFacade veiculosFacade;
    private VeiculosService veiculosServiceMock;

    private BuilderGenerete builderGenerete;
    @BeforeEach
    void setUp() {
        veiculosServiceMock = mock(VeiculosService.class);
        veiculosFacade = new VeiculosFacade(veiculosServiceMock);
        builderGenerete = new BuilderGenerete();
    }

    @Test
    void testGetAllVeiculos() {

        List<VeiculosEntity> veiculosList = new ArrayList<>();
        VeiculosEntity veiculo = builderGenerete.builderVeiculo();
        veiculosList.add(veiculo);

        when(veiculosServiceMock.getAllVeiculos()).thenReturn(veiculosList);

        List<VeiculosEntity> result = veiculosFacade.getAllVeiculos();

        assertEquals(veiculosList, result);
    }

    @Test
    void testCreateVeiculo() {

        VeiculosEntity veiculo = builderGenerete.builderVeiculo();

        when(veiculosServiceMock.createVeiculo(any(VeiculosEntity.class))).thenReturn(veiculo);

        VeiculosEntity result = veiculosFacade.createVeiculo(veiculo);

        assertEquals(veiculo, result);
    }

    @Test
    void testGetVeiculoById() {

        VeiculosEntity veiculo = builderGenerete.builderVeiculo();
        when(veiculosServiceMock.getVeiculoFindById(1L)).thenReturn(Optional.of(veiculo));

        Optional<VeiculosEntity> result = veiculosFacade.getVeiculoById(1L);

        assertTrue(result.isPresent());
        assertEquals(veiculo, result.get());
    }

    @Test
    void testUpdateVeiculo() {

        VeiculosEntity veiculo = builderGenerete.builderVeiculo();

        when(veiculosServiceMock.putVeiculo(1L, veiculo)).thenReturn(veiculo);

        VeiculosEntity result = veiculosFacade.updateVeiculo(1L, veiculo);

        assertEquals(veiculo, result);
    }

    @Test
    void testDeleteVeiculo() {

        VeiculosEntity veiculo = builderGenerete.builderVeiculo();

        when(veiculosServiceMock.deleteVeiculo(1L)).thenReturn(Optional.of(veiculo));

        Optional<VeiculosEntity> result = veiculosFacade.deleteVeiculo(1L);

        assertTrue(result.isPresent());
        assertEquals(veiculo, result.get());
    }
}
