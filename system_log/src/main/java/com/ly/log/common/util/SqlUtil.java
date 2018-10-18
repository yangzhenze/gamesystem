package com.ly.log.common.util;

import java.util.Arrays;
import java.util.stream.Stream;

public class SqlUtil {


    public static String sqlWhere(String ... sqlParams){
        Stream<String> sqlParamsStream =  Arrays.stream(sqlParams);

        StringBuffer sbSql = new StringBuffer();
        sqlParamsStream.forEach((param) ->{
            if(sbSql.length() == 0){
                sbSql.append(" where ").append(param);
            }else{
                sbSql.append(" and ").append(param);
            }
                }
        );

        return sbSql.toString();
    }
}
