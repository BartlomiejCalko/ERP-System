package sample.rest;

import org.springframework.web.client.RestTemplate;
import sample.dto.OperatorAuthenticationResultDto;
import sample.dto.OperatorCredentialsDto;

public class AuthenticatorImpl implements Authenticator{

    private static final String AUTHENTICATION_URL = "http://localhost:8081/verify_operator_credentials";
    private final RestTemplate restTemplate;

    public AuthenticatorImpl() {
        restTemplate = new RestTemplate();
    }

    @Override
    public void authenticate(OperatorCredentialsDto operatorCredentialsDTO, AuthenticationResultHandler authenticationResultHandler) {
        Runnable authenticationTask = () -> processAuthentication(operatorCredentialsDTO, authenticationResultHandler);
        Thread authenticationThread = new Thread(authenticationTask);
        authenticationThread.setDaemon(true);
        authenticationThread.start();
    }

    private void processAuthentication(OperatorCredentialsDto operatorCredentialsDTO, AuthenticationResultHandler authenticationResultHandler) {
//        ResponseEntity<OperatorAuthenticationResultDTO> responseEntity = restTemplate.postForEntity(AUTHENTICATION_URL, operatorCredentialsDTO, OperatorAuthenticationResultDTO.class);

        OperatorAuthenticationResultDto dto = new OperatorAuthenticationResultDto();
        dto.setAuthenticated(true);
        dto.setFirstName("bart");
        dto.setLastName("cal");
        dto.setIdOperator(1L);
        authenticationResultHandler.handle(dto);
    }
}
