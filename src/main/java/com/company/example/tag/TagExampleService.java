package com.company.example.tag;

import com.numarics.engine.fusion.tag.TagApi;
import com.numarics.engine.fusion.tag.TagDeleteResponse;
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
    String tenantUuid = "demo-tenant-uuid"; // Use one from response for onboarding partner client.
    Integer tagId = 1;
    TagDetailsResponse response = tagApi.getById(tenantUuid, tagId);
    System.out.println(response);
    return response;
  }

  public TagGetAllResponse getAll() {
    String tenantUuid = "demo-tenant-uuid"; // Use one from response for onboarding partner client.
    TagGetAllResponse response = tagApi.getAll(tenantUuid);
    System.out.println(response);
    return response;
  }

  public TagDetailsResponse createRootTag() {
    String tenantUuid = "demo-tenant-uuid"; // Use one from response for onboarding partner client.
    String name = "Demo Root Tag";
    String color = "#FFE4E5E1";
    TagDetailsResponse response = tagApi.createRootTag(tenantUuid, name, color);
    System.out.println(response);
    return response;
  }

  public TagDetailsResponse createChildTag() {
    String tenantUuid = "demo-tenant-uuid"; // Use one from response for onboarding partner client.
    String name = "Demo Root Tag";
    Integer parentId = 1;
    TagDetailsResponse response = tagApi.createChildTag(tenantUuid, name, parentId);
    System.out.println(response);
    return response;
  }


  public TagDetailsResponse updateRootTag() {
    String tenantUuid = "demo-tenant-uuid"; // Use one from response for onboarding partner client.
    Integer tagId = 10;
    String name = "Demo Tag Update";
    String color = "#FFE4E5E2";
    TagDetailsResponse response = tagApi.updateRootTag(tenantUuid, tagId, name, color);
    System.out.println(response);
    return response;
  }

  public TagDetailsResponse updateChildTag() {
    String tenantUuid = "demo-tenant-uuid"; // Use one from response for onboarding partner client.
    Integer tagId = 10;
    String name = "Demo Tag Update";
    Integer parentId = 1;
    TagDetailsResponse response = tagApi.updateChildTag(tenantUuid, tagId, name, parentId);
    System.out.println(response);
    return response;
  }

  public TagDeleteResponse delete() {
    String tenantUuid = "demo-tenant-uuid"; // Use one from response for onboarding partner client.
    Integer tagId = 10;
    TagDeleteResponse response = tagApi.delete(tenantUuid, tagId);
    System.out.println(response);
    return response;
  }
}
