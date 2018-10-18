package com.ly.log.dao;

import com.ly.log.common.util.Page;
import com.ly.log.entity.Log;

public interface ILogDao {

    Page<Log> getLog(int page,int pageSize,String service,String startDate,String endDate,String roleId,String roleName,String userId,String account,Integer logType,Integer eventType,String sort);
}
