package com.company.example.tag;

import com.numarics.engine.fusion.tag.TagApi;
import com.numarics.engine.fusion.tag.TagDetailsResponse;
import com.numarics.engine.fusion.tag.TagGetAllResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TagExampleService {
  private final TagApi tagApi;

  @Autowired
  public TagExampleService(TagApi tagApi) {
    this.tagApi = tagApi;
  }

  public TagDetailsResponse getById() {
    String tenantUuid =
        "tenant-uuid-example"; // Use one from response for onboarding partner client.
    Integer tagId = 1;
    TagDetailsResponse response = tagApi.getById(tenantUuid, tagId);
    System.out.println(response);
    return response;
  }

  public TagGetAllResponse getAll() {
    String tenantUuid =
        "tenant-uuid-example"; // Use one from response for onboarding partner client.
    TagGetAllResponse response = tagApi.getAll(tenantUuid);
    System.out.println(response);
    return response;
  }
}
