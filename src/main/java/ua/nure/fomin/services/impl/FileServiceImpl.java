package ua.nure.fomin.services.impl;

import com.backendless.Backendless;
import com.backendless.BackendlessCollection;
import com.backendless.BackendlessUser;
import com.backendless.files.FileInfo;
import com.backendless.logging.Logger;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.nure.fomin.services.FileService;
import ua.nure.fomin.services.UserService;

import java.io.*;
import java.net.URI;
import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class FileServiceImpl implements FileService {

    private Logger logger = Backendless.Logging.getLogger(FileServiceImpl.class);

    private static final String PATH = "https://api.backendless.com/BA37451B-0CD4-ECDE-FF9E-6A1E669E6100/V1/files";

    private static final String SHARED_FOLDER = "shared_with_me";

    private static final String TOMCAT_ROOT = "files";

    @Autowired
    private UserService userService;

    public FileServiceImpl() {
    }

    public void createWorkDirectory(String userName) {
        try {
            File file = createDefaultFile();
            Backendless.Files.upload(file, userName + "/" + SHARED_FOLDER);
        } catch (Exception e) {
            logger.error("Directory is not created");
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
        try {
            File workDirectory = new File(PATH + "/" + userName);
            String folderUrl = search(folderName, workDirectory).getURL();
            BackendlessCollection<FileInfo> collection = Backendless.Files.listing(folderUrl);
            if (folderUrl.contains(SHARED_FOLDER)) {
                for (FileInfo info : collection.getData()) {
                    File file = new File(TOMCAT_ROOT + "/" + info.getName());
                    String path = readUrl(file);
                    String[] mass = path.split("/");
                    files.put(mass[mass.length - 1], path);
                }
            } else {
                for (FileInfo info : collection.getData()) {
                    String url = PATH + "/" + info.getURL();
                    if (!new File(url).isDirectory()) {
                        files.put(info.getName(), url);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
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
        if (url.contains(SHARED_FOLDER)) {
            File file = new File(TOMCAT_ROOT + "/" + fileName);
            url = readUrl(file);
        }
        return url;
    }

    @Override
    public void shareFile(String fileName, String userName, String userForSharingName) {
        try {
            BackendlessUser user = userService.find(userForSharingName);
            if (!user.getProperties().isEmpty()) {
                File workDirectory = new File(PATH + "/" + userName);
                String srcUrl = PATH + "/" + search(fileName, workDirectory).getURL();
                String destUrl = userForSharingName + "/" + SHARED_FOLDER;
                File file = new File(TOMCAT_ROOT + "/" + fileName);
                writeUrl(file, srcUrl);
                Backendless.Files.upload(file, destUrl);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<String> getAllFiles(String userName) {
        return Backendless.Files.listing(userName, "*.*",true).getData().stream().map(file->file.getName()).collect(Collectors.toList());
    }

    @Override
    public List<String> getAllFolders(String userName) {
        return Backendless.Files.listing(userName).getData().stream().map(file->file.getName()).collect(Collectors.toList());
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

    private void writeUrl(File file, String url) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write(url);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private String readUrl(File file) {
        String url = null;
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            url = reader.readLine();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return url;
    }

}
