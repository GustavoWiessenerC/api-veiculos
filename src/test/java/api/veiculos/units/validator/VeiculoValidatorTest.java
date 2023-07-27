package api.veiculos.units.validator;


import api.veiculos.core.entity.VeiculosEntity;
import api.veiculos.repository.VeiculosRepository;
import api.veiculos.validator.VeiculoValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class VeiculoValidatorTest {

    @Mock
    private VeiculosRepository veiculosRepository;

    @InjectMocks
    private VeiculoValidator veiculoValidator;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testExistVeiculoById_ExistingId_ReturnsOptionalVeiculoEntity() {
        Long id = 1L;
        VeiculosEntity veiculoEntity = new VeiculosEntity();
        when(veiculosRepository.findById(id)).thenReturn(Optional.of(veiculoEntity));

        Optional<VeiculosEntity> result = veiculoValidator.existVeiculoById(id, veiculosRepository);

        assertEquals(veiculoEntity, result.orElse(null));
    }

    @Test
    public void testExistVeiculoById_NonExistingId_ReturnsEmptyOptional() {
        Long id = 2L;
        when(veiculosRepository.findById(id)).thenReturn(Optional.empty());

        Optional<VeiculosEntity> result = veiculoValidator.existVeiculoById(id, veiculosRepository);

        assertEquals(Optional.empty(), result);
    }
}
