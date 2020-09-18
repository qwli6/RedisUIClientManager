package com.lqw.redisui.utils;

import com.lqw.redisui.model.RedisServerInfo;
import com.lqw.redisui.i18n.I18n;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class FileUtil {


    public static String readStringFromFile(Path path) {
        if(path == null || path.toFile().isDirectory()){
            throw new RuntimeException("file not found");
        }

        if(path.toFile().exists()) {
            //存在
            try {
                List<String> strings = Files.readAllLines(path, Charset.defaultCharset());

                return strings.toString();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return "";
    }


    public static void initConfigFileIfNotExists(Path path) {
        try {
            Files.createFile(path);
            RedisServerInfo redisServerInfo = new RedisServerInfo();
            redisServerInfo.setAlias(I18n.DEFAULT_LOCAL_ALIAS);
            redisServerInfo.setPort(I18n.DEFAULT_PORT);
            redisServerInfo.setAddress(I18n.DEFAULT_LOCAL_ADDRESS);

            Files.write(path, "".getBytes(Charset.defaultCharset()));
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public static boolean checkConfigFile(String defaultConfigFilePath) {
        Path path = Paths.get(defaultConfigFilePath);
        if(!path.toFile().exists()){
            try {

                if(path.toFile().isDirectory()){
                    Files.createDirectories(path);
                    return true;
                }

                //currentPath is file
                //parent is directory
                Path parent = path.getParent();

                if(!parent.toFile().exists()){
                    Files.createDirectories(parent);
                }

                //must first create directories
                //then create file
                Files.createFile(path);
                return true;
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        } else {
            return true;
        }
    }
}
