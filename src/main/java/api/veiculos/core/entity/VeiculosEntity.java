package api.veiculos.core.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@ApiModel(description = "Representação de um veículo")
public class VeiculosEntity {

    @ApiModelProperty(notes = "ID do veículo", example = "1")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ApiModelProperty(notes = "Ano do veículo", example = "2023")
    @Column(nullable = false)
    private String ano;

    @ApiModelProperty(notes = "Marca do veículo", example = "BMW")
    @Column(nullable = false)
    private String marca;

    @ApiModelProperty(notes = "Modelo do veículo", example = "115I")
    @Column(nullable = false)
    private String modelo;

    @ApiModelProperty(notes = "Indicador se o veículo possui carroceria", example = "true")
    @Column(nullable = false)
    private boolean carroceria;

}
