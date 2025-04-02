package com.company.example.document;

public enum MimeType {
  PDF("application/pdf"),
  PNG("image/png"),
  JPG("image/jpeg"),
  JPEG("image/jpeg");

  private final String value;

  MimeType(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }
}
