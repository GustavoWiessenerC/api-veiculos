package api.veiculos.contract;

import api.veiculos.core.entity.VeiculosEntity;
import api.veiculos.service.VeiculosService;
import au.com.dius.pact.core.model.RequestResponsePact;
import au.com.dius.pact.provider.PactVerifyProvider;
import au.com.dius.pact.provider.junit5.PactVerificationContext;
import au.com.dius.pact.provider.junitsupport.Provider;
import au.com.dius.pact.provider.junitsupport.State;
import au.com.dius.pact.provider.junitsupport.loader.PactFolder;
import au.com.dius.pact.provider.spring.junit5.PactVerificationSpringProvider;
import io.pactfoundation.consumer.dsl.LambdaDsl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestTemplate;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Collections;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ExtendWith(SpringExtension.class)
@Provider("VeiculosProvider") // Nome do provedor (sua aplicação)
@PactFolder("pacts") // Pasta onde os arquivos de contrato serão gerados
public class VeiculosControllerContractTest {

    @MockBean
    private VeiculosService veiculosService;

    @BeforeEach
    void setup() {
        // Configuração de comportamento do serviço mockado para testes de contrato
        when(veiculosService.getVeiculoFindById(anyLong())).thenReturn(Optional.of(new VeiculosEntity(1L, "Ford", "Mustang", 2022)));
    }

    @TestTemplate
    @ExtendWith(PactVerificationSpringProvider.class)
    void pactVerificationTestTemplate(PactVerificationContext context) {
        context.verifyInteraction();
    }

    @State("veiculo com ID 1 existe") // Nome do estado que o provedor (sua aplicação) deve estar antes do teste de contrato
    public void veiculoComId1Existe() {
        // Nenhum código é necessário aqui, pois o comportamento foi configurado usando o MockBean na configuração acima
    }

    @PactVerifyProvider("uma requisicao para buscar veiculo por ID") // Nome do teste de contrato
    public RequestResponsePact verificarBuscarVeiculoPorIdPact(PactDslWithProvider builder) {
        return builder.given("veiculo com ID 1 existe") // Estado do provedor esperado antes do teste de contrato
                .uponReceiving("uma requisicao para buscar veiculo por ID") // Descrição do teste de contrato
                .path("/veiculos/listar/1")
                .method("GET")
                .willRespondWith()
                .status(200)
                .headers(Collections.singletonMap("Content-Type", "application/json"))
                .body(LambdaDsl.newJsonBody((o) -> {
                    o.numberType("id", 1L);
                    o.stringType("marca", "Ford");
                    o.stringType("modelo", "Mustang");
                    o.numberType("ano", 2022);
                }).build())
                .toPact();
    }
}
