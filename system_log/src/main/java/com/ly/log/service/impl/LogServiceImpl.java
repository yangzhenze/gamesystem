package com.ly.log.service.impl;

import com.ly.log.common.util.Page;
import com.ly.log.dao.ILogDao;
import com.ly.log.entity.Log;
import com.ly.log.service.ILogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LogServiceImpl implements ILogService {

    @Autowired
    ILogDao logDao;

    @Override
    public Page<Log> getLog(int page, int pageSize, String service, String startDate, String endDate,String roleId,String roleName,String userId,String account,Integer logType,Integer eventType,String sort) {
        return logDao.getLog(page,pageSize,service,startDate,endDate,roleId,roleName,userId,account,logType,eventType,sort);
    }
}
