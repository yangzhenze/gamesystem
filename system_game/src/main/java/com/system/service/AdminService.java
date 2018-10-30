package com.system.service;

import com.system.bean.Admin;
import com.system.config.FeignLogConfiguration;
import com.system.service.hystrix.AdminServiceHystrix;
import feign.Headers;
import feign.Param;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.beans.Encoder;

/**
 * @author zzy
 * @Date 2018/8/16 上午11:58
 */
@FeignClient(value = "systemAdmin",fallback = AdminServiceHystrix.class,configuration = FeignLogConfiguration.class)
public abstract class AdminService {

    @RequestMapping(value = "/admin/login", method = RequestMethod.POST)
    public abstract Admin login(@RequestParam("account") String account, @RequestParam("password") String password);

    @PostMapping(value = "/admin", produces = "application/json;charset=UTF-8")
    public abstract String addAdmin(@RequestBody Admin admin);

    @GetMapping(value = "/admin/{id}", produces = "application/json;charset=UTF-8")
    public abstract String getAdminById(@PathVariable("id") Integer id);

    @PutMapping(value = "/admin", produces = "application/json;charset=UTF-8")
    public abstract String update(Admin admin);

    @GetMapping(value = "/admin/page/{page}", produces = "application/json;charset=UTF-8")
    public abstract String getAdminPage(@PathVariable("page") Integer page, @RequestParam("pageSize") Integer pageSize);

    @PostMapping(value = "/admin/checkAccount")
    public abstract String checkAccount(@RequestParam("account") String account, @RequestParam("id") Integer id);

    @DeleteMapping(value = "/admin/{id}")
    public abstract String delAdmin(@PathVariable("id") Integer[] id);
}
