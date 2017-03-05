package ua.nure.fomin.services;

import java.util.Map;

public interface FileService {

    void createWorkDirectory(String userName);

    void createFolder(String folderName, String userName);

    void removeFile(String fileName, String userName);

    Map<String, String> getFiles(String folderName, String userName);

    void uploadFile(String fileName, String folderName, String userName, byte[] bytes);

    String downloadFile(String fileName, String userName);

    void shareFile(String fileName, String userName, String userForSharingName);

}
