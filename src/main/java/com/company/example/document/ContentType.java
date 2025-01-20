package com.company.example.document;

public enum ContentType {
  PDF("application/pdf"),
  PNG("image/png");

  private final String value;

  ContentType(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }
}
