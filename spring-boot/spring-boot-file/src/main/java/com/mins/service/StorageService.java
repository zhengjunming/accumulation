package com.mins.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.nio.file.Path;
import java.util.stream.Stream;

/**
 * @author <a href="http://minsming.com">小铭</a>
 * Date: 2018/4/29
 * No struggle, talent how to match the willfulness.
 * Description: 文件存储的逻辑接口
 */
public interface StorageService {

    /**
     * init dir
     */
    void init();

    /**
     * store file for write file
     *
     * @param filename    filename
     * @param inputStream inputStream
     */
    void store(String filename, InputStream inputStream);

    /**
     * store file for upload file
     *
     * @param file file
     * @param directoryPath directory path
     */
    void store(MultipartFile file, String directoryPath);

    /**
     * load file
     *
     * @param filename filename
     * @param directoryPath directory path
     * @return Path Examples
     */
    Path load(String filename, String directoryPath);

    /**
     * load file as resource
     *
     * @param filename filename
     * @param directoryPath directory path
     * @return Resource Examples
     */
    Resource loadAsResource(String filename, String directoryPath);

    /**
     * delete all files
     */
    void deleteAll();

    /**
     * delete all files by directory name
     *
     * @param directoryName directory name
     */
    void deleteAllByDirectoryName(String directoryName);

    /**
     * delete file by path
     *
     * @param path file path
     * @return success: true
     */
    boolean deleteFileByPath(String path);

    /**
     * load all files
     *
     * @return Stream
     */
    Stream<Path> loadAll();

    /**
     * load files by directory name
     *
     * @param directoryName directory name
     * @return Stream
     */
    Stream<Path> loadFilesByDirectorName(String directoryName);
}
