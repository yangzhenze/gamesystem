package com.ly.log.dao.impl;

import com.ly.log.common.Const;
import com.ly.log.common.util.DateUtil;
import com.ly.log.common.util.FileUtil;
import com.ly.log.common.util.Page;
import com.ly.log.common.util.StrUtil;
import com.ly.log.dao.ILogDao;
import com.ly.log.entity.Log;
import com.sun.corba.se.spi.orb.StringPair;
import javafx.beans.property.adapter.ReadOnlyJavaBeanBooleanProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Properties;

@Component
public class LogDaoImpl implements ILogDao {
    @Autowired
    JdbcTemplate jdbcTemplate;
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

    @Value("${projectFilePath}")
    private  String filePath;
    @Value("${logStoreConfig}")
    private  String logStoreConfig;
    @Value("${dataBase}")
    private  String dataBase;

    boolean whereFlag = false;
    String event = " where ";

    @Override
    public Page<Log> getLog(int page, int pageSize, String service,String startDate, String endDate,String roleId,String roleName,String userId,String account,Integer logType,Integer eventType,String sort) {
        StringBuffer sql = new StringBuffer("select l.* from ");
        StringBuffer tableSql = new StringBuffer("");
        StringBuffer params = new StringBuffer();
        Page<Log> pageData = null;

        try{
            if(StrUtil.isBlank(startDate) && StrUtil.isBlank(endDate)){
                List<Map<String, Object>> tables = null;

                if(!StrUtil.isBlank(service)){
                    tables = this.getTables("%_"+service+"_%");
                }else {
                    tables = this.getTables(null);
                }

                for(int i = 0; i < tables.size(); i++){
                    if(i == 0){
                        tableSql.append(" (select * from `"+tables.get(i).get("TABLE_NAME")+"`");
                        if(null != logType){
                            params.append(whereSql()).append( "log_type = "+logType);
                        }

                        if(null != eventType){
                            params.append(whereSql()).append( " event_type = "+eventType);
                        }
                        restWhere();
                    }else{
                        tableSql.append(" union all select * from `"+tables.get(i).get("TABLE_NAME")+"` ");
                    }

                    tableSql.append(params);

                }



            }else if(!StrUtil.isBlank(startDate) && !StrUtil.isBlank(endDate)){
                int day = DateUtil.getIntervalDays(DateUtil.stringToDate(endDate,"yyyy-MM-dd"),DateUtil.stringToDate(startDate,"yyyy-MM-dd"));

                boolean selectFlag = true;

                for(int i=0;i<=day;i++){
                    Date date = DateUtil.dateAdd(3,i,DateUtil.stringToDate(startDate,"yyyy-MM-dd"));
                    Object [] keys = null;

                    List<Map<String, Object>> tables = null;

                    if(!StrUtil.isBlank(service)){
                        tables = this.getTables("%_"+service+"_"+simpleDateFormat.format(date));
                    }else {
                        tables = this.getTables("%"+simpleDateFormat.format(date));
                    }

                    for(int j = 0;j < tables.size();j++){
                        if(selectFlag){
                            tableSql.append(" (select * from  `"+tables.get(j).get("TABLE_NAME")+"`");

                            if(null != logType){
                                params.append(whereSql()).append( "log_type = "+logType);
                            }

                            if(null != eventType){
                                params.append(whereSql()).append( " event_type = "+eventType);
                            }
                            restWhere();
                            selectFlag = false;
                        }else{
                            tableSql.append(" union all select * from `"+tables.get(j).get("TABLE_NAME")+"`");
                        }

                        tableSql.append(params);
                    }


                }
            }

            tableSql.append(" ) l");

            if(!StrUtil.isBlank(startDate) && !StrUtil.isBlank(endDate)){
                tableSql.append(whereSql()).append(" l.logtime > ").append("'"+startDate+"'").append(" and l.logtime < ").append("'"+endDate+"'");

            }

            if(!StrUtil.isBlank(userId)){
                tableSql.append(whereSql()).append(" l.userid = '"+userId+"'");
            }

            if(!StrUtil.isBlank(account)){
                tableSql.append(whereSql()).append(" l.account = '"+account+"'");
            }

            if(!StrUtil.isBlank(roleId)){
                tableSql.append(whereSql()).append(" l.roleid = '"+roleId+"'");
            }

            if(!StrUtil.isBlank(roleName)){
                tableSql.append(whereSql()).append(" l.rolename like '%"+roleName+"'");
            }

            restWhere();
            sql.append(tableSql);

            int totalRow = jdbcTemplate.queryForObject(sql.toString() .replace("l.*","count(*)"),Integer.class);
            int totalPage = totalRow%pageSize == 0 ? totalRow/pageSize : totalRow/pageSize+1;

            sql.append(" order by l.logtime ");

            if(null != sort){
                sql.append(" desc");
            }

            sql.append(" limit ").append((page - 1) * pageSize).append(",").append(pageSize);
            RowMapper<Log> rowMapper = BeanPropertyRowMapper.newInstance(Log.class);
            List<Log> pageList = jdbcTemplate.query(sql.toString(),rowMapper);

            pageData = new Page<>(pageList, page, pageSize, totalPage, totalRow);
        }catch (Exception e){
            return new Page<>(null,1, Const.PAGE_SIZE,0,0);
        }

        return pageData;


    }

    private String whereSql(){

        if(this.whereFlag){
            this.event = " and ";
        }else{
            this.whereFlag = true;
        }
        return this.event;
    }

    private void restWhere(){
        this.whereFlag = false;
        this.event = " where ";
    }

    private List<Map<String,Object>> getTables(String tableName) {
        StringBuffer sb = new StringBuffer("SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = ?");
        List<Map<String,Object>> tableMap = null;
        if(!StrUtil.isBlank(tableName)){
            sb.append(" and TABLE_NAME like ?");
            tableMap = jdbcTemplate.queryForList(sb.toString(),dataBase,tableName);
        }else{
            tableMap = jdbcTemplate.queryForList(sb.toString(),dataBase);
        }
        return tableMap;
    }

    private boolean tableExist(String tableName){
        String sql = "SELECT count(*) FROM information_schema.TABLES WHERE table_name =?";

        int result = jdbcTemplate.queryForObject(sql, new String[]{tableName},Integer.class);

        if(result > 0){
            return true;
        }
        return false;
    }
}
