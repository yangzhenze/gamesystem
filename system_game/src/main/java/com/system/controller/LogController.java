package com.system.controller;


import com.system.service.Admin2Service;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Api(description = "日志操作接口")
@RestController
@RequestMapping("/log")
public class LogController {
    protected static final Logger logger = LoggerFactory.getLogger(LogController.class);

    @Autowired
    Admin2Service admin2Service;

    @RequestMapping(value = "/{page}", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "查看日志",notes = "查看日志")
    public String findAll(@PathVariable Integer page, Integer pageSize,String service,String startDate,String endDate){
        //return logService.getLog(page,pageSize,service,startDate,endDate);
        return null;
    }

}
