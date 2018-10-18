package com.ly.log.controller;


import com.ly.log.common.Const;
import com.ly.log.common.util.Ret;
import com.ly.log.service.ILogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;

@Api(description = "日志操作接口")
@RestController
@RequestMapping("/log")
public class LogController {
    protected static final Logger logger = LoggerFactory.getLogger(LogController.class);

    @Autowired
    ILogService logService;

    @RequestMapping(value = "/{page}", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "查看日志",notes = "查看日志")
    public String findAll(@PathVariable("page") Integer page, Integer pageSize,String service,String startDate,String endDate,String roleId,String roleName,String userId,String account,Integer logType,Integer eventType,String sort){
        if(null == pageSize){
            pageSize = Const.PAGE_SIZE;
        }


        return Ret.msgSuccess(logService.getLog(page,pageSize,service,startDate,endDate,roleId,roleName,userId,account,logType,eventType,sort));
    }

}
