package api.veiculos.builder;

import api.veiculos.core.entity.VeiculosEntity;

public class BuilderGenerete {

    public VeiculosEntity builderVeiculo() {
        VeiculosEntity veiculo = new VeiculosEntity();
        veiculo.setId(1L);
        veiculo.setCarroceria(false);
        veiculo.setAno("2023");
        veiculo.setMarca("BMW");
        veiculo.setModelo("115I");
        return veiculo;
    }
}
