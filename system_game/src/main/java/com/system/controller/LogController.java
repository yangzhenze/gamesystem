package com.system.controller;

import com.system.bean.Admin;
import com.system.common.Const;
import com.system.service.LogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;


/**
 * @author zzy
 */
@Api(description = "日志操作接口")
@RestController
@RequestMapping("/log")
public class LogController {

    @Autowired
    private LogService logService;

    @RequestMapping(value = "/login/{page}", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "查看登录日志",notes = "查看登录日志")
    public String getLogin(@PathVariable("page") int page, Integer pageSize,String service,String startDate,String endDate,String roleId,String roleName,String userId,String account,String sort){
        return logService.findAll(page,pageSize,service,startDate,endDate,roleId,roleName,userId,account,Const.LOG_TYPE_ACTION,Const.EVENT_TYPE_ROLE_LOGOUT,sort);
    }

    @RequestMapping(value = "/createrole/{page}", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "查看创角日志",notes = "查看创角日志")
    public String getCreateRole(@PathVariable("page") int page, Integer pageSize,String service,String startDate,String endDate,String roleId,String roleName,String userId,String account,String sort){
        return logService.findAll(page,pageSize,service,startDate,endDate,roleId,roleName,userId,account,Const.LOG_TYPE_ACTION,Const.EVENT_TYPE_CREATE_ROLE,sort);
    }

}
