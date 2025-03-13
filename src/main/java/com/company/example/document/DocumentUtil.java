package com.company.example.document;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;

public final class DocumentUtil {

  public static FileInfo getFileInfo(String filePath, String contentType) {
    try {
      File file = new File(filePath);

      byte[] fileContent = Files.readAllBytes(file.toPath());
      String content = Base64.getEncoder().encodeToString(fileContent);
      List<byte[]> chunks = getChunks(fileContent, 4096);

      String fileName = file.getName();
      long fileSize = file.length();

      return new FileInfo(fileName, fileSize, contentType, content, chunks);
    } catch (IOException e) {
      e.printStackTrace();
      return null;
    }
  }

  private static List<byte[]> getChunks(byte[] fileContent, int chunkSize) {
    List<byte[]> chunks = new ArrayList<>();
    int totalLength = fileContent.length;

    for (int i = 0; i < totalLength; i += chunkSize) {
      int remaining = totalLength - i;
      int currentChunkSize = Math.min(chunkSize, remaining);

      byte[] chunk = Arrays.copyOfRange(fileContent, i, i + currentChunkSize);
      chunks.add(chunk);
    }

    return chunks;
  }
}
