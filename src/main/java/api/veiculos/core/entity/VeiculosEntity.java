package api.veiculos.core.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@ApiModel(description = "Representacao de um veiculo")
public class VeiculosEntity {

    @ApiModelProperty(notes = "ID do veiculo", example = "1")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ApiModelProperty(notes = "Ano do veiculo", example = "2023")
    @Column(nullable = false)
    private String ano;

    @ApiModelProperty(notes = "Marca do veiculo", example = "BMW")
    @Column(nullable = false)
    private String marca;

    @ApiModelProperty(notes = "Modelo do veiculo", example = "115I")
    @Column(nullable = false)
    private String modelo;

    @ApiModelProperty(notes = "Indicador se o veiculo possui carroceria", example = "true")
    @Column(nullable = false)
    private boolean carroceria;

}
