/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mongo.gridfs.dao;

import com.mongo.constant.MongoCollectionKeyName;
import java.io.InputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;

import com.mongodb.DBObject;
import com.mongodb.gridfs.GridFSDBFile;
import org.apache.log4j.Logger;

/**
 *
 * @author Gulam mustafa
 */
public class GridFSDBFileDaoImpl implements GridFSDBFileDao {

    static Logger log = Logger.getLogger(GridFSDBFileDaoImpl.class);
    @Autowired
    GridFsTemplate gridFsTemplate;

    @Override
    public String store(InputStream inputStream, String fileName, String contentType, DBObject metaData) {
                try {
        return this.gridFsTemplate.store(inputStream, fileName, contentType, metaData).getId().toString();
        } catch (Exception ex) {
            log.error("Unable to Store File"+ex);
            return null;
        }
    }

    @Override
    public GridFSDBFile fileRetriveById(String id) {
         try {
        return this.gridFsTemplate.findOne(new Query(Criteria.where(MongoCollectionKeyName._ID).is(id)));
         } catch (Exception ex) {
            log.error("Unable to Retrive File"+ex);
            return null;
        }
    }

    @Override
    public GridFSDBFile fileRetriveByFileName(String fileName) {
        try {
            return gridFsTemplate.findOne(new Query(Criteria.where(MongoCollectionKeyName.FILE_NAME).is(fileName)));
        } catch (Exception ex) {
            log.error("Unable to Delete File"+ex);
            return null;
        }
    }

    @Override
    public boolean deleteFileByGridFsId(String gridFsId) {
        try {
            Query query = new Query();
            query.addCriteria(Criteria.where(MongoCollectionKeyName._ID).is(gridFsId));
            this.gridFsTemplate.delete(query);
        } catch (Exception ex) {
            log.error("Unable to Delete File"+ex);
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

}
