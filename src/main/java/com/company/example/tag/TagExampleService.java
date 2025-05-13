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
    String tenantUuid =
        "demo-tenant-uuid"; // Use one from a response for an onboarding partner client.
    Integer tagId = 1;
    return tagApi.getById(tenantUuid, tagId);
  }

  public TagGetAllResponse getAll() {
    String tenantUuid =
        "demo-tenant-uuid"; // Use one from a response for an onboarding partner client.
    return tagApi.getAll(tenantUuid);
  }

  public TagDetailsResponse createRootTag() {
    String tenantUuid =
        "demo-tenant-uuid"; // Use one from a response for an onboarding partner client.
    String name = "Demo Root Tag";
    String color = "#FFE4E5E1";
    return tagApi.createRootTag(tenantUuid, name, color);
  }

  public TagDetailsResponse createChildTag() {
    String tenantUuid =
        "demo-tenant-uuid"; // Use one from a response for an onboarding partner client.
    String name = "Demo Root Tag";
    Integer parentId = 1;
    return tagApi.createChildTag(tenantUuid, name, parentId);
  }

  public TagDetailsResponse updateRootTag() {
    String tenantUuid =
        "demo-tenant-uuid"; // Use one from a response for an onboarding partner client.
    Integer tagId = 10;
    String name = "Demo Tag Update";
    String color = "#FFE4E5E2";
    Boolean isHidden = false;
    return tagApi.updateRootTag(tenantUuid, tagId, name, color, isHidden);
  }

  public TagDetailsResponse updateChildTag() {
    String tenantUuid =
        "demo-tenant-uuid"; // Use one from a response for an onboarding partner client.
    Integer tagId = 10;
    String name = "Demo Tag Update";
    Integer parentId = 1;
    return tagApi.updateChildTag(tenantUuid, tagId, name, parentId);
  }

  public TagDeleteResponse delete() {
    String tenantUuid =
        "demo-tenant-uuid"; // Use one from a response for an onboarding partner client.
    Integer tagId = 10;
    return tagApi.delete(tenantUuid, tagId);
  }
}
