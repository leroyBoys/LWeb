package com.lweb.manager;

import com.lgame.util.comm.StringTool;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by Administrator on 2018/7/9.
 */
public class UploadManager {
    private static String root;
    private static UploadManager ourInstance = new UploadManager();

    public static UploadManager getInstance() {
        return ourInstance;
    }

    private UploadManager() {
    }

    public static void init(String path){
        root = path;
    }

    public String uploadAdminPic(MultipartFile file){
        if(file == null){
            return null;
        }
        String fileName = file.getOriginalFilename();
        if(StringTool.isEmpty(fileName)){
            return null;
        }
        String filePath=root+"/head/admin";
        fileName = "head"+fileName.substring(fileName.lastIndexOf("."));
        uploadFile(file,filePath,fileName);
        return filePath+"/"+fileName;
    }


    public static void uploadFile(MultipartFile file, String filePath, String fileName){
        File targetFile = new File(filePath);
        if(!targetFile.exists()){
            targetFile.mkdirs();
        }

        filePath +="/"+fileName;
        targetFile = new File(filePath);
        targetFile.deleteOnExit();
        FileOutputStream out = null;
        try{
            out = new FileOutputStream(filePath);
            out.write(file.getBytes());
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(out != null){
                try {
                    out.flush();
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }
}
