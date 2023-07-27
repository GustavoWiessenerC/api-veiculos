package api.veiculos.units.core;


import api.veiculos.core.builder.VeiculoBuilder;
import api.veiculos.core.entity.VeiculosEntity;
import api.veiculos.repository.VeiculosRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class VeiculoBuilderTest {

    @Mock
    private VeiculosRepository veiculosRepository;

    @InjectMocks
    private VeiculoBuilder veiculoBuilder;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testBuilderById() {
        Long id = 1L;
        VeiculosEntity veiculo = new VeiculosEntity();
        veiculo.setId(id);
        veiculo.setMarca("Fiat");
        veiculo.setModelo("Uno");
        veiculo.setAno("2020");

        when(veiculosRepository.findById(id)).thenReturn(Optional.of(veiculo));

        Optional<VeiculosEntity> result = veiculoBuilder.builderById(id);

        assertEquals(veiculo, result.orElse(null));
    }

    @Test
    void testBuilderGenerete() {
        VeiculosEntity veiculo = new VeiculosEntity();
        veiculo.setMarca("Fiat");
        veiculo.setModelo("Uno");
        veiculo.setAno("2020");
        veiculo.setCarroceria(true);

        VeiculosEntity veiculoAtualizado = new VeiculosEntity();
        veiculoAtualizado.setMarca("VW");
        veiculoAtualizado.setModelo("ONIX");
        veiculoAtualizado.setAno("2020");
        veiculoAtualizado.setCarroceria(true);

        VeiculosEntity result = veiculoBuilder.builderGenerete(veiculo, veiculoAtualizado);

        assertEquals(veiculo, result);
    }
}
