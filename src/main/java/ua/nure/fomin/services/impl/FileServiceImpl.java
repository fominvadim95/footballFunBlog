package ua.nure.fomin.services.impl;

import com.backendless.Backendless;
import com.backendless.BackendlessCollection;
import com.backendless.files.FileInfo;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Service;
import ua.nure.fomin.services.FileService;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.*;

@Service
public class FileServiceImpl implements FileService {

    private static final String PATH = "https://api.backendless.com/BA37451B-0CD4-ECDE-FF9E-6A1E669E6100/V1/files";

    private static final String SHARED_FOLDER = "shared_with_me";

    private static final String TOMCAT_ROOT = "files";

    public FileServiceImpl() {
    }

    public void createWorkDirectory(String userName) {
        try {
            File file = createDefaultFile();
            Backendless.Files.upload(file, userName + "/" + SHARED_FOLDER);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void createFolder(String folderName, String userName) {
        try {
            File file = createDefaultFile();
            Backendless.Files.upload(file, userName + "/" + folderName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeFile(String fileName, String userName) {
        File workDirectory = new File(PATH + "/" + userName);
        String url = PATH + "/" + search(fileName, workDirectory).getURL();
        File searchedFile = new File(url);
        try {
            if (searchedFile.isDirectory()) {
                Backendless.Files.removeDirectory(fileName);
            } else {
                Backendless.Files.remove(url.replace(PATH, ""));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Map<String, String> getFiles(String folderName, String userName) {
        Map<String, String> files = new LinkedHashMap<>();
        File workDirectory = new File(PATH + "/" + userName);
        String folderUrl = search(folderName, workDirectory).getURL();
        BackendlessCollection<FileInfo> collection = Backendless.Files.listing(folderUrl);
        for (FileInfo info : collection.getData()) {
            String url = PATH + "/" + info.getURL();
            if (!new File(url).isDirectory()) {
                files.put(info.getName(), url);
            }
        }

        return files;
    }

    @Override
    public void uploadFile(String fileName, String folderName, String userName, byte[] bytes) {
        File workDirectory = new File(PATH + "/" + userName);
        String folderUrl = search(folderName, workDirectory).getURL();
        File upload = new File(TOMCAT_ROOT + "/" + fileName);
        OutputStream stream = null;
        try {
            stream = new FileOutputStream(upload);
            stream.write(bytes);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                stream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try {
            Backendless.Files.upload(upload, folderUrl);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String downloadFile(String fileName, String userName) {
        File workDirectory = new File(PATH + "/" + userName);
        String url = PATH + "/" + search(fileName, workDirectory).getURL();
        return url;
    }


    private File createDefaultFile() {
        File file = new File(TOMCAT_ROOT + "/default.txt");
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }

    private FileInfo search(String fileName, File workDirectory) {
        String path = "/" + workDirectory.getName();
        String extension = FilenameUtils.getExtension(fileName);
        BackendlessCollection<FileInfo> collection = null;
        if (extension.isEmpty()) {
            collection = Backendless.Files.listing(path);
        } else {
            collection = Backendless.Files.listing(path, "*." + FilenameUtils.getExtension(fileName), true);
        }

        for (FileInfo info : collection.getData()) {
            if (info.getName().equals(fileName)) {
                return info;
            }
        }

        return new FileInfo();
    }

}
