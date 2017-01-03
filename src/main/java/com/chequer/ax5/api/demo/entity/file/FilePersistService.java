package com.chequer.ax5.api.demo.entity.file;

import com.chequer.ax5.api.demo.utils.JsonUtils;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

import static java.util.stream.Collectors.toList;


@Service
public class FilePersistService implements InitializingBean {

    @Value("${ax5uploader.repository.path}")
    private String path;

    public void persist(AX5File ax5File) throws IOException {
        clean();

        // 파일 로컬시스템에 저장
        ax5File.getMultipartFile().transferTo(new File(path + File.separator + ax5File.getSaveName()));

        // JSON 정보 저장
        FileUtils.writeStringToFile(new File(path + File.separator + ax5File.getJsonName()), JsonUtils.toJson(ax5File), "UTF-8");
    }

    int maxSaveFileCount = 20;

    public void clean() throws IOException {
        List<AX5File> ax5Files = listFiles();

        if (ax5Files.size() > maxSaveFileCount) {
            for (int i = maxSaveFileCount - 1; i < ax5Files.size(); i++) {
                delete(ax5Files.get(i));
            }
        }
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        FileUtils.forceMkdir(new File(path));
    }

    public List<AX5File> listFiles() {
        List<AX5File> files = FileUtils.listFiles(new File(path), new String[]{"json"}, false).stream().map(file -> {
            try {
                return getAx5File(file);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }).collect(toList());

        Collections.sort(files);

        return files;
    }

    public AX5File getAx5File(File file) throws IOException {
        AX5File ax5File = JsonUtils.fromJson(FileUtils.readFileToString(file, "UTF-8"), AX5File.class);
        ax5File.setLastModified(file.lastModified());
        ax5File.setFile(new File(path + File.separator + ax5File.getSaveName()));
        return ax5File;
    }

    public AX5File getAx5File(String id) throws IOException {
        return getAx5File(new File(path + File.separator + id + ".json"));
    }

    public void flush() {
        FileUtils.deleteQuietly(new File(path));
    }

    public void delete(AX5File ax5File) throws IOException {
        ax5File = getAx5File(ax5File.getId());
        FileUtils.deleteQuietly(new File(path + File.separator + ax5File.getSaveName()));
        FileUtils.deleteQuietly(new File(path + File.separator + ax5File.getJsonName()));
    }
}
