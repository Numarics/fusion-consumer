package com.company.example.document;

import java.util.List;

public record FileInfo(
    String name, long size, String contentType, String content, List<byte[]> chunks) {}
