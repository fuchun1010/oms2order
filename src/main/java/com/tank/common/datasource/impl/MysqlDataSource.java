package com.tank.common.datasource.impl;

import com.tank.common.datasource.RdbDataSource;

/**
 * @author tank198435163.com
 */
public class MysqlDataSource extends RdbDataSource {

  @Override
  protected String assignDriverName() {
    return "com.mysql.cj.jdbc.Driver";
  }

  @Override
  protected String assignDbUserName() {
    return "root";
  }

  @Override
  protected String assignDbUserPassword() {
    return "123";
  }

  @Override
  protected String assignUrl() {
    return "jdbc:mysql://localhost:3307/orderCenter?serverTimezone=Asia/Shanghai&zeroDateTimeBehavior=convertToNull";
  }
}
