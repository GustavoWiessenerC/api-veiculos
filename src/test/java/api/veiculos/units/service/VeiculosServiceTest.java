package api.veiculos.units.service;

import api.veiculos.builder.BuilderGenerete;
import api.veiculos.core.builder.VeiculoBuilder;
import api.veiculos.core.entity.VeiculosEntity;
import api.veiculos.repository.VeiculosRepository;
import api.veiculos.service.VeiculosService;
import api.veiculos.validator.VeiculoValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class VeiculosServiceTest {

    @Mock
    private VeiculosRepository veiculosRepository;

    @Mock
    private VeiculoValidator veiculoValidator;

    @Mock
    private VeiculoBuilder veiculoBuilder;

    @InjectMocks
    private VeiculosService veiculosService;

    private BuilderGenerete veiculoBuilderInTest;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        veiculoBuilderInTest = new BuilderGenerete();
    }

    @Test
    public void testGetAllVeiculos_ReturnsListOfVeiculos() {
        List<VeiculosEntity> veiculosList = new ArrayList<>();
        VeiculosEntity veiculo = veiculoBuilderInTest.builderVeiculo();

        veiculosList.add(veiculo);
        when(veiculosRepository.findAll()).thenReturn(veiculosList);

        List<VeiculosEntity> result = veiculosService.getAllVeiculos();

        assertEquals(veiculosList, result);
    }

    @Test
    public void testCreateVeiculo_ReturnsSavedVeiculoEntity() {
        VeiculosEntity veiculo = veiculoBuilderInTest.builderVeiculo();
        when(veiculosRepository.save(veiculo)).thenReturn(veiculo);

        VeiculosEntity result = veiculosService.createVeiculo(veiculo);

        assertEquals(veiculo, result);
    }

    @Test
    public void testGetVeiculoFindById_ExistingId_ReturnsOptionalVeiculoEntity() {
        Long id = 1L;
        VeiculosEntity veiculoEntity = veiculoBuilderInTest.builderVeiculo();
        when(veiculoBuilder.builderById(id)).thenReturn(Optional.of(veiculoEntity));

        Optional<VeiculosEntity> result = veiculosService.getVeiculoFindById(id);

        assertEquals(veiculoEntity, result.orElse(null));
    }

    @Test
    public void testGetVeiculoFindById_NonExistingId_ReturnsEmptyOptional() {
        Long id = 2L;
        when(veiculoBuilder.builderById(id)).thenReturn(Optional.empty());

        Optional<VeiculosEntity> result = veiculosService.getVeiculoFindById(id);

        assertEquals(Optional.empty(), result);
    }

    @Test
    public void testPutVeiculo_ExistingId_ReturnsUpdatedVeiculoEntity() {
        Long id = 1L;
        VeiculosEntity veiculo = veiculoBuilderInTest.builderVeiculo();
        VeiculosEntity updatedVeiculo = new VeiculosEntity();
        Optional<VeiculosEntity> veiculoExistente = Optional.of(new VeiculosEntity());

        when(veiculoValidator.existVeiculoById(id, veiculosRepository)).thenReturn(veiculoExistente);
        when(veiculoBuilder.builderGenerete(updatedVeiculo, veiculo)).thenReturn(updatedVeiculo);
        when(veiculosRepository.save(updatedVeiculo)).thenReturn(updatedVeiculo);

        VeiculosEntity result = veiculosService.putVeiculo(id, veiculo);

        assertEquals(updatedVeiculo, result);
    }

    @Test
    public void testPutVeiculo_NonExistingId_ThrowsIllegalArgumentException() {
        Long id = 2L;
        VeiculosEntity veiculo = new VeiculosEntity();
        Optional<VeiculosEntity> veiculoExistente = Optional.empty();

        when(veiculoValidator.existVeiculoById(id, veiculosRepository)).thenReturn(veiculoExistente);

        assertThrows(IllegalArgumentException.class, () -> veiculosService.putVeiculo(id, veiculo));
    }

    @Test
    public void testDeleteVeiculo_ExistingId_ReturnsDeletedVeiculoEntity() {
        Long id = 1L;
        VeiculosEntity veiculoEntity = veiculoBuilderInTest.builderVeiculo();
        Optional<VeiculosEntity> veiculo = Optional.of(veiculoEntity);

        when(veiculoBuilder.builderById(id)).thenReturn(veiculo);

        Optional<VeiculosEntity> result = veiculosService.deleteVeiculo(id);

        assertEquals(veiculoEntity, result.orElse(null));
        verify(veiculosRepository, times(1)).deleteById(id);
    }

    @Test
    public void testDeleteVeiculo_NonExistingId_ReturnsEmptyOptional() {
        Long id = 2L;
        when(veiculoBuilder.builderById(id)).thenReturn(Optional.empty());

        Optional<VeiculosEntity> result = veiculosService.deleteVeiculo(id);

        assertEquals(Optional.empty(), result);
        verify(veiculosRepository, never()).deleteById(id);
    }
}
