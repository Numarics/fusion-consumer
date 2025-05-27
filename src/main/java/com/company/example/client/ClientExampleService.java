package com.company.example.client;

import com.numarics.engine.fusion.client.ClientApi;
import com.numarics.engine.fusion.client.ClientCreateResponse;
import com.numarics.engine.fusion.client.ClientOffboardingResponse;
import com.numarics.engine.fusion.client.GetClientInfoResponse;
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

  public GetClientInfoResponse getClientInfo() {
    String tenantUuid =
        "tenant-uuid-example"; // Use one from a response for an onboarding partner client.
    return clientApi.getInfo(tenantUuid);
  }

  public ClientOffboardingResponse initiateClientOffboarding() {
    String tenantUuid =
        "tenant-uuid-example"; // Use one from a response for an onboarding partner client.
    return clientApi.initiateOffboarding(tenantUuid);
  }
}
