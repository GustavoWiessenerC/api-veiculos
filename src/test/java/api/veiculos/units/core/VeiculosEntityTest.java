package api.veiculos.units.core;

import api.veiculos.core.entity.VeiculosEntity;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class VeiculosEntityTest {

    @Test
    public void testVeiculoEntity() {
        VeiculosEntity veiculo = new VeiculosEntity();
        veiculo.setId(1L);
        veiculo.setAno("2023");
        veiculo.setMarca("BMW");
        veiculo.setModelo("115I");
        veiculo.setCarroceria(true);

        assertEquals(1L, veiculo.getId());
        assertEquals("2023", veiculo.getAno());
        assertEquals("BMW", veiculo.getMarca());
        assertEquals("115I", veiculo.getModelo());
        assertEquals(true, veiculo.isCarroceria());
    }
}