/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mongo.main;

/**
 *
 * @author mustafa
 */
import com.mongo.gridfs.dao.GridFSDBFileDao;
import com.mongo.gridfs.dao.GridFSDBFileDaoImpl;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.Resource;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.gridfs.GridFSDBFile;
import org.apache.log4j.Logger;

public class ApplicationMain {

    public static void main(String[] args) {
        InputStream inputStream = null;
        try {
            ApplicationContext context = new ClassPathXmlApplicationContext("SpringBeans.xml");

            GridFSDBFileDao gridFSDBFileDao = (GridFSDBFileDao) context.getBean("gridFSDBFileDao");

            Resource resource = context.getResource("file:D:/sampleFile.jpg");

            DBObject metaData = new BasicDBObject();
            metaData.put("filename", "sampleFile.jpg");
            metaData.put("fileMimeType", "jpg");
            metaData.put("fileExtension", "image/jpeg");

            String id = gridFSDBFileDao.store(resource.getInputStream(), "sampleFile.jpg", "image/jpeg", metaData);

            System.out.println("Find By Id ::"+id);
            GridFSDBFile byId = gridFSDBFileDao.fileRetriveById(id);
            System.out.println("File Name:- " + byId.getFilename());
            System.out.println("Content Type:- " + byId.getContentType());

            System.out.println("Find By Filename ::"+"mustafa.jpg");
            GridFSDBFile getFileByName = gridFSDBFileDao.fileRetriveByFileName("sampleFile.jpg");
            System.out.println("File Name:- " + getFileByName.getFilename());
            System.out.println("Content Type:- " + getFileByName.getContentType());
            getFileByName.writeTo("D:\\newSampleFile.jpg");

        } catch (BeansException e) {
            System.out.println("BeansException:-" + e.getMessage());
        } catch (IOException e) {
            System.out.println("IOException:-" + e.getMessage());
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    System.out.println("IOException Finally:-" + e.getMessage());
                }
            }
        }

    }
}
