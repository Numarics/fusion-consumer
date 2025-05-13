package com.company.example.client;

import com.numarics.engine.fusion.client.ClientApi;
import com.numarics.engine.fusion.client.ClientCreateResponse;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientExampleService {
  private final ClientApi clientApi;

  @Autowired
  public ClientExampleService(ClientApi clientApi) {
    this.clientApi = clientApi;
  }

  public ClientCreateResponse createClient() {
    String clientReference = "";
    String email = "example@email.ch";
    String name = "ref no";
    int type = 2; // 1 is enterprise, 2 is a private person
    List<Integer> modules = List.of(2); // 2 for Docubox
    return clientApi.create(name, email, clientReference, type, modules);
  }
}
