package com.lunz.fin.utils;

import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

@Slf4j
public class FileUtil {

    public String readFile(File file){

        try{

            BufferedReader reader = null;
            StringBuffer buffer = new StringBuffer();
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "utf-8"));
            String tempString = "";
            while (( tempString = reader.readLine()) != null) {
                buffer.append(tempString);
                buffer.append("\n");
            }
            return buffer.toString();

        }catch(Exception ex){

            log.error(String.format("读取文件内容失败：%s %n %s",ex.getMessage(),ex.getStackTrace()));
            return "";
        }

    }

    public String readFile(String path){

        try{
            File file=new File(path);
            return this.readFile(file);
        }catch(Exception ex){

            log.error(String.format("读取文件失败：%s %n %s",ex.getMessage(),ex.getStackTrace()));
            return "";
        }

    }
}
