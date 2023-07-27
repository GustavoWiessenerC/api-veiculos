package api.veiculos.builder;

import api.veiculos.core.entity.VeiculosEntity;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class RequestSpecificationGenerete {

    public static int createOperationVeiculoResultInId(VeiculosEntity veiculo) {
        return given()
                .contentType(ContentType.JSON)
                .body(veiculo)
                .when()
                .post("/veiculos/criar")
                .then()
                .statusCode(201)
                .body("marca", equalTo(veiculo.getMarca()))
                .body("modelo", equalTo(veiculo.getModelo()))
                .extract().path("id");
    }
}
