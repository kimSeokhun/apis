package com.chequer.ax5.api.demo.entity.file;

import com.chequer.ax5.api.demo.utils.JsonUtils;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static java.util.stream.Collectors.toList;


@Service
public class FilePersistService implements InitializingBean {

    @Value("${ax5uploader.repository.path}")
    private String path;

    public void persist(AX5File ax5File) throws IOException {
        // 파일 로컬시스템에 저장
        ax5File.getMultipartFile().transferTo(new File(path + File.separator + ax5File.getSaveName()));

        // JSON 정보 저장
        FileUtils.writeStringToFile(new File(path + File.separator + ax5File.getJsonName()), JsonUtils.toJson(ax5File));
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        FileUtils.forceMkdir(new File(path));
    }

    public List<AX5File> listFiles() {
        return FileUtils.listFiles(new File(path), new String[]{"json"}, false).stream().map(file -> {
            try {
                String json = FileUtils.readFileToString(file);
                return JsonUtils.fromJson(json, AX5File.class);
            } catch (Exception e) {
                // ignore
            }
            return null;
        }).collect(toList());
    }
}
