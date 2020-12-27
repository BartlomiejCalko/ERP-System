package sample.rest;

import com.sun.xml.internal.ws.encoding.XMLHTTPBindingCodec;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import sample.dto.EmployeeDto;
import sample.handler.SavedEmployeeHandler;

import java.util.Arrays;
import java.util.List;

public class EmployeeRestClient {

    private static final String EMPLOYEES_URL = "http://localhost:8081/employees";
    private final RestTemplate restTemplate;

    public EmployeeRestClient() {
        restTemplate = new RestTemplate();
    }

    public List<EmployeeDto> getEmployees() {
        ResponseEntity<EmployeeDto[]> employeesResponseEntity = restTemplate.getForEntity(EMPLOYEES_URL, EmployeeDto[].class);
        return Arrays.asList(employeesResponseEntity.getBody());
    }

    public void saveEmployee(EmployeeDto dto, SavedEmployeeHandler handler) {
        ResponseEntity<EmployeeDto> responseEntity = restTemplate.postForEntity(EMPLOYEES_URL, dto, EmployeeDto.class);
        if (HttpStatus.OK.equals(responseEntity.getStatusCode())) {
            handler.handle();
        } else {
            //TODO implement
        }
    }
}
