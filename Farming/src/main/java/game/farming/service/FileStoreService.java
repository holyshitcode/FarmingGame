package game.farming.service;


import game.farming.domain.UploadFile;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
@Slf4j
@Component
public class FileStoreService {
    @Value("${file.dir}")
    private String fileDir;

    public String getFullPath(String fileName) {
        log.info("Full path for {}: {}", fileName, fileDir); // 로깅 추가
        log.info("Full path for {}: {}", null,fileDir +fileName);
        return fileDir + File.separator + fileName;
    }

    public UploadFile storeFile(MultipartFile file) throws IOException {
        if (file.isEmpty()) {
            return null;
        }
        String originalFilename = file.getOriginalFilename();
        String ext = originalFilename.substring(originalFilename.lastIndexOf("."));
        String uuid = UUID.randomUUID().toString();
        String storeFileName = uuid + ext;
        file.transferTo(new File(getFullPath(storeFileName)));
        log.info("Stored file: {}", storeFileName);
        return new UploadFile(originalFilename, storeFileName);
    }

    public List<UploadFile> storeFiles(List<MultipartFile> files) throws IOException {
        List<UploadFile> storeFileResult = new ArrayList<>();
        for (MultipartFile file : files) {
            if(!file.isEmpty()){
                UploadFile uploadFile = storeFile(file);
                storeFileResult.add(uploadFile);
            }
        }
        return storeFileResult;
    }
}
