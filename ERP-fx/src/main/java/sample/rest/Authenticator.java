package sample.rest;

import sample.dto.OperatorCredentialsDto;
import sample.handler.AuthenticationResultHandler;

public interface Authenticator {

    void authenticate(OperatorCredentialsDto operatorCredentialsDTO, AuthenticationResultHandler authenticationResultHandler);

}
