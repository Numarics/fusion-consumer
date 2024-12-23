package com.company.example;

import com.numarics.engine.fusion.client.ClientApi;
import com.numarics.engine.fusion.client.ClientCreateResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExampleService {
    private final ClientApi clientApi;

    @Autowired
    public ExampleService(ClientApi clientApi) {
        this.clientApi = clientApi;
    }

    public String createClient() {
        String clientReference = "some client reference";
        String email = "test_email@email.ch";
        String name = "test";
        int type = 2; // 1 is enterprise, 2 is private person
        List<Integer> modules = List.of(2); // 2 for Docubox
        ClientCreateResponse clientCreateResponse = clientApi.create("test", "t@t.ch", "test-client-ref-2", type, modules);
        System.out.println(clientCreateResponse);
        return clientCreateResponse.getTenantUuid();
    };
}
