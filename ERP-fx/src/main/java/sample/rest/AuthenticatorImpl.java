package sample.rest;

import sample.dto.UserCredentialsDTO;

public class AuthenticatorImpl implements Authenticator{

    private static final String LOGIN = "barto";
    private static final String PASSWORD = "barto123";


    @Override
    public void authenticate(UserCredentialsDTO userCredentialsDTO, AuthenticationResultHandler authenticationResultHandler) {
        Runnable authenticationTask = createAuthenticationTask(userCredentialsDTO, authenticationResultHandler);
        Thread authenticationThread = new Thread(authenticationTask);
        authenticationThread.setDaemon(true);
        authenticationThread.start();
    }

    private Runnable createAuthenticationTask(UserCredentialsDTO userCredentialsDTO, AuthenticationResultHandler authenticationResultHandler) {
        return () -> {
            try {
                Thread.sleep(1000);
                boolean authenticated = LOGIN.equals(userCredentialsDTO.getLogin()) && PASSWORD.equals(userCredentialsDTO.getPassword());
                authenticationResultHandler.handle(authenticated);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };
    }
}
