package api.veiculos.core.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NonNull;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

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
    @NotNull(message = "Ano nao pode ser nulo.")
    @NotEmpty(message = "Ano nao pode ser vazio.")
    @Pattern(regexp = "\\d{4}", message = "Ano deve conter exatamente 4 posicoes.")
    private String ano;

    @ApiModelProperty(notes = "Marca do veiculo", example = "BMW")
    @Column(nullable = false)
    @NotNull(message = "Marca nao pode ser nulo.")
    @NotEmpty(message = "Marca nao pode ser vazio.")
    private String marca;

    @ApiModelProperty(notes = "Modelo do veiculo", example = "115I")
    @Column(nullable = false)
    @NotNull(message = "Modelo nao pode ser nulo.")
    @NotEmpty(message = "Modelo nao pode ser vazio.")
    private String modelo;

    @ApiModelProperty(notes = "Indicador se o veiculo possui carroceria", example = "true")
    @Column(nullable = false)
    @NonNull
    private boolean carroceria;

}
