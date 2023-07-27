package api.veiculos.integration;

import api.veiculos.builder.BuilderGenerete;
import api.veiculos.core.entity.VeiculosEntity;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import java.util.List;
import static api.veiculos.builder.RequestSpecificationGenerete.createOperationVeiculoResultInId;
import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class VeiculosServicesIntegrationTest {

    @LocalServerPort
    private int port;

    private BuilderGenerete builderGenerete;

    @BeforeEach
    public void setUp() {
        RestAssured.port = port;
        builderGenerete = new BuilderGenerete();
    }

    @Test
    public void testCreateVeiculo() {
        VeiculosEntity veiculo = builderGenerete.builderVeiculo();

        createOperationVeiculoResultInId(veiculo);
    }

    @Test
    public void testCreateVeiculoAndGetById() {
        VeiculosEntity veiculo = builderGenerete.builderVeiculo();

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
    }

    @Test
    public void testCreateAndGetAll() {
        VeiculosEntity veiculo = builderGenerete.builderVeiculo();

        createOperationVeiculoResultInId(veiculo);

        List<VeiculosEntity> veiculos = given()
                .contentType(ContentType.JSON)
                .when()
                .get("/veiculos/listar/")
                .then()
                .statusCode(200)
                .extract().jsonPath().getList(".", VeiculosEntity.class);

        assertThat(veiculos, hasItem(hasProperty("marca", equalTo(veiculo.getMarca()))));
        assertThat(veiculos, hasItem(hasProperty("modelo", equalTo(veiculo.getModelo()))));
    }

    @Test
    public void testPutVeiculo() {

        VeiculosEntity veiculoInicial = builderGenerete.builderVeiculo();

        int idCreateVeiculo = createOperationVeiculoResultInId(veiculoInicial);

        VeiculosEntity veiculoAtualizado = new VeiculosEntity();
        veiculoAtualizado.setMarca("Chevrolet");
        veiculoAtualizado.setModelo("Onix");
        veiculoAtualizado.setAno("2023");
        veiculoAtualizado.setCarroceria(true);

        given()
                .pathParam("id", idCreateVeiculo)
                .contentType(ContentType.JSON)
                .body(veiculoAtualizado)
                .when()
                .put("/veiculos/alterar/{id}", idCreateVeiculo)
                .then()
                .statusCode(200);

        VeiculosEntity veiculoAtualizadoNoBanco = given()
                .pathParam("id", idCreateVeiculo)
                .when()
                .get("/veiculos/listar/{id}")
                .then()
                .statusCode(200)
                .extract().as(VeiculosEntity.class);

        assertThat(veiculoAtualizadoNoBanco.getMarca(), equalTo(veiculoAtualizado.getMarca()));
        assertThat(veiculoAtualizadoNoBanco.getModelo(), equalTo(veiculoAtualizado.getModelo()));
        assertThat(veiculoAtualizadoNoBanco.getAno(), equalTo(veiculoAtualizado.getAno()));
        assertThat(veiculoAtualizadoNoBanco.isCarroceria(), equalTo(veiculoAtualizado.isCarroceria()));
    }


    @Test
    public void testInDeleteOperation() {

        VeiculosEntity veiculo = builderGenerete.builderVeiculo();

        int idCreate = createOperationVeiculoResultInId(veiculo);

         given()
                .pathParam("id", idCreate)
                .when()
                .delete("/veiculos/deletar/{id}", idCreate)
                .then()
                .statusCode(204);
    }
}

