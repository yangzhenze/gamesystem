package com.ly.log.entity;

import java.io.Serializable;

import com.ly.log.common.anotation.Column;
import lombok.Getter;
import lombok.Setter;
/**
 * @author auto create
 * @Date 2018/31/16 15:31:40
 */
public class Log implements Serializable{
        private static final long serialVersionUID = 1L;
        /**
         *程序类型
         */
        @Getter
        @Setter
        @Column(name="program_type")
        private java.lang.String programType;

        /**
         *日志类型
         */
        @Getter
        @Setter
        @Column(name="log_type")
        private java.lang.String logType;

        /**
         *事件类型
         */
        @Getter
        @Setter
        @Column(name="event_type")
        private java.lang.String eventType;

        /**
         *日志名称
         */
        @Getter
        @Setter
        @Column(name="logname")
        private java.lang.String logname;

        /**
         *角色id
         */
        @Getter
        @Setter
        @Column(name="roleid")
        private java.lang.String roleid;

        /**
         *用户名
         */
        @Getter
        @Setter
        @Column(name="rolename")
        private java.lang.String rolename;

        /**
         *用户id
         */
        @Getter
        @Setter
        @Column(name="userid")
        private java.lang.String userid;

        /**
         *用户账号
         */
        @Getter
        @Setter
        @Column(name="account")
        private java.lang.String account;

        /**
         *用户服务
         */
        @Getter
        @Setter
        @Column(name="serverid")
        private java.lang.String serverid;

        /**
         *日志时间
         */
        @Getter
        @Setter
        @Column(name="logtime")
        private java.util.Date logtime;

        /**
         *
         */
        @Getter
        @Setter
        @Column(name="params1")
        private java.lang.String params1;

        /**
         *
         */
        @Getter
        @Setter
        @Column(name="params2")
        private java.lang.String params2;

        /**
         *
         */
        @Getter
        @Setter
        @Column(name="params3")
        private java.lang.String params3;

        /**
         *
         */
        @Getter
        @Setter
        @Column(name="params4")
        private java.lang.String params4;

        /**
         *
         */
        @Getter
        @Setter
        @Column(name="params5")
        private java.lang.String params5;

        /**
         *
         */
        @Getter
        @Setter
        @Column(name="params6")
        private java.lang.String params6;

        /**
         *
         */
        @Getter
        @Setter
        @Column(name="params7")
        private java.lang.String params7;

        /**
         *
         */
        @Getter
        @Setter
        @Column(name="params8")
        private java.lang.String params8;

        /**
         *
         */
        @Getter
        @Setter
        @Column(name="params9")
        private java.lang.String params9;

        /**
         *
         */
        @Getter
        @Setter
        @Column(name="params10")
        private java.lang.String params10;

        /**
         *
         */
        @Getter
        @Setter
        @Column(name="params11")
        private java.lang.String params11;

        /**
         *
         */
        @Getter
        @Setter
        @Column(name="params12")
        private java.lang.String params12;

        /**
         *
         */
        @Getter
        @Setter
        @Column(name="params13")
        private java.lang.String params13;

        /**
         *
         */
        @Getter
        @Setter
        @Column(name="params14")
        private java.lang.String params14;

        /**
         *
         */
        @Getter
        @Setter
        @Column(name="params15")
        private java.lang.String params15;

        /**
         *
         */
        @Getter
        @Setter
        @Column(name="params16")
        private java.lang.String params16;

        /**
         *
         */
        @Getter
        @Setter
        @Column(name="params17")
        private java.lang.String params17;

        /**
         *
         */
        @Getter
        @Setter
        @Column(name="params18")
        private java.lang.String params18;

        /**
         *
         */
        @Getter
        @Setter
        @Column(name="params19")
        private java.lang.String params19;

        }