package com.zlc.springboot.unti;

import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.Date;

//文件操作工具类
public class FileUntil {

    //具体业务实现
    public static ResponseEntity<FileSystemResource> export(File file){
        if (file==null){
            return null;
        }
        HttpHeaders headers = new HttpHeaders();
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        try {
            headers.add("Content-Disposition", "attachment; filename=" + new String(file.getName().getBytes("UTF-8"),"iso8859-1"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");
        headers.add("Last-Modified", new Date().toString());
        headers.add("ETag", String.valueOf(System.currentTimeMillis()));
        headers.add("Content-Length", String.valueOf(file.length()));
        return ResponseEntity
                .ok()
                .headers(headers)
                .contentLength(file.length())
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                //本地环境
                .body(new FileSystemResource(file));

    }
}

