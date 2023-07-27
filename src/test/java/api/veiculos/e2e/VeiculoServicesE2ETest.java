package api.veiculos.e2e;

import api.veiculos.builder.BuilderGenerete;
import api.veiculos.core.entity.VeiculosEntity;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import static api.veiculos.builder.RequestSpecificationGenerete.createOperationVeiculoResultInId;
import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class VeiculoServicesE2ETest {
    @LocalServerPort
    private int port;

    private BuilderGenerete builder;

    @BeforeEach
    void setUp() {
        RestAssured.port = port;
        builder = new BuilderGenerete();
    }

    @Test
    void e2eTestingGenerateServices() {
        VeiculosEntity veiculo = builder.builderVeiculo();

        int createdVeiculoId = createOperationVeiculoResultInId(veiculo);

        given()
                .pathParam("id", createdVeiculoId)
                .when()
                .get("/veiculos/listar/{id}")
                .then()
                .statusCode(200)
                .body("id", equalTo(createdVeiculoId))
                .body("marca", equalTo(veiculo.getMarca()))
                .body("modelo", equalTo(veiculo.getModelo()));

        VeiculosEntity veiculoAtualizado = new VeiculosEntity();
        veiculoAtualizado.setMarca("MERCEDEZ");
        veiculoAtualizado.setModelo("GLA200");
        veiculoAtualizado.setAno("2023");
        veiculoAtualizado.setCarroceria(false);

        given()
                .pathParam("id", createdVeiculoId)
                .contentType(ContentType.JSON)
                .body(veiculoAtualizado)
                .when()
                .put("/veiculos/alterar/{id}", createdVeiculoId)
                .then()
                .statusCode(200);

        VeiculosEntity veiculoAtualizadoNoBanco = given()
                .pathParam("id", createdVeiculoId)
                .when()
                .get("/veiculos/listar/{id}")
                .then()
                .statusCode(200)
                .extract().as(VeiculosEntity.class);

        assertThat(veiculoAtualizadoNoBanco.getMarca(), equalTo(veiculoAtualizado.getMarca()));
        assertThat(veiculoAtualizadoNoBanco.getModelo(), equalTo(veiculoAtualizado.getModelo()));
        assertThat(veiculoAtualizadoNoBanco.getAno(), equalTo(veiculoAtualizado.getAno()));
        assertThat(veiculoAtualizadoNoBanco.isCarroceria(), equalTo(veiculoAtualizado.isCarroceria()));

        given()
                .pathParam("id", createdVeiculoId)
                .when()
                .delete("/veiculos/deletar/{id}", createdVeiculoId)
                .then()
                .statusCode(204);

    }
}