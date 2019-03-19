/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mongo.gridfs.dao;

import com.mongodb.DBObject;
import com.mongodb.gridfs.GridFSDBFile;
import java.io.InputStream;

/**
 *
 * @author Md Gulam mustafa
 */
public interface GridFSDBFileDao {

    public String store(InputStream inputStream, String fileName, String contentType, DBObject metaData);

    public GridFSDBFile fileRetriveById(String id);

    public GridFSDBFile fileRetriveByFileName(String filename);

    public boolean deleteFileByGridFsId(String gridFsId);
}
