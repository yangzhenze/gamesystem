package com.ly.log.service;

import com.ly.log.common.util.Page;
import com.ly.log.entity.Log;

import java.util.List;

public interface ILogService {

    Page<Log> getLog(int page, int pageSize, String service, String startDate, String endDate,String roleId,String roleName,String userId,String account,Integer logType,Integer eventType,String sort);
}
