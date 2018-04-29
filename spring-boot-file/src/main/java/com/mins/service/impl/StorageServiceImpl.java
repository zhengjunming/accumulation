package com.mins.service.impl;

import com.mins.exception.StorageException;
import com.mins.exception.StorageFileNotFoundException;
import com.mins.model.StorageProperties;
import com.mins.service.StorageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.stream.Stream;

/**
 * @author <a href="http://minsming.com">小铭</a>
 * Date: 2018/4/29
 * No struggle, talent how to match the willfulness.
 * Description: 文件存储逻辑层实现类
 */
@Service
@Slf4j
public class StorageServiceImpl implements StorageService {

    private final Path rootLocation;

    private final String rootPath;

    @Autowired
    public StorageServiceImpl(StorageProperties properties) {
        this.rootLocation = Paths.get(properties.getLocation());
        this.rootPath = properties.getLocation();
    }

    @Override
    public void init() {
        try {
            Files.createDirectories(rootLocation);
        } catch (IOException e) {
            log.error("Could not initialize storage");
            throw new StorageException("Could not initialize storage", e);
        }
    }

    @Override
    public void store(String filename, InputStream inputStream) {
        try {
            Files.copy(inputStream, this.rootLocation.resolve(filename), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void store(MultipartFile file, String directoryPath) {
        Path path;
        if (directoryPath == null) {
            path = Paths.get(rootPath);
        } else {
            path = Paths.get(rootPath + directoryPath);
        }
        String filename = StringUtils.cleanPath(file.getOriginalFilename());
        try {
            // 创建文件夹，如果文件夹已经存在，文件会继续往以存在的文件夹放入
            // 可以是多级文件夹，例如 “/test/child”
            Files.createDirectories(path);

            if (file.isEmpty()) {
                throw new StorageException("Failed to store empty file " + filename);
            }
            if (filename.contains("..")) {
                // This is a security check
                throw new StorageException(
                        "Cannot store file with relative path outside current directory "
                                + filename);
            }
            try (InputStream inputStream = file.getInputStream()) {
                Files.copy(inputStream, path.resolve(filename),
                        StandardCopyOption.REPLACE_EXISTING);
            }
        } catch (IOException e) {
            throw new StorageException("Failed to store file " + filename, e);
        }
    }

    @Override
    public Path load(String filename, String directoryPath) {
        Path root;
        // 如果有子文件夹，就进入子文件夹，可以是多级文件夹，例如 “test/child”
        if (directoryPath == null) {
            return rootLocation.resolve(filename);
        } else {
            root = rootLocation.resolve(directoryPath);
            return root.resolve(filename);
        }
    }

    @Override
    public Resource loadAsResource(String filename, String directoryPath) {
        try {
            Path file = load(filename, directoryPath);
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new StorageFileNotFoundException(
                        "Could not read file: " + filename);
            }
        } catch (MalformedURLException e) {
            throw new StorageFileNotFoundException("Could not read file: " + filename, e);
        }
    }

    @Override
    public void deleteAll() {
        FileSystemUtils.deleteRecursively(rootLocation.toFile());
    }

    @Override
    public void deleteAllByDirectoryName(String directoryName) {
        System.out.println(directoryName);
        FileSystemUtils.deleteRecursively(Paths.get(directoryName).toFile());
    }

    @Override
    public boolean deleteFileByPath(String path) {
        boolean result;
        try {
            // 如果成功删除，返回true
            result = Files.deleteIfExists(Paths.get(path));
        } catch (IOException e) {
            e.printStackTrace();
            log.error("文件不存在");
            throw new StorageFileNotFoundException("the file is not exist", e);
        }
        return result;
    }

    @Override
    public Stream<Path> loadAll() {
        try {
            return Files.walk(this.rootLocation, 1)
                    .filter(path -> !path.equals(this.rootLocation))
                    .map(this.rootLocation::relativize);
        } catch (IOException e) {
            e.printStackTrace();
            throw new StorageException("Failed to read stored files", e);
        }
    }

    @Override
    public Stream<Path> loadFilesByDirectorName(String directoryName) {
        Path directory = Paths.get(directoryName);
        try {
            return Files.walk(directory, 1)
                    .filter(path -> !path.equals(directory))
                    .map(directory::relativize);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
