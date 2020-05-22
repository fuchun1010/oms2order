package com.tank.common.datasource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author tank198435163.com
 */
public abstract class RdbDataSource {

  public Connection createConnection() throws ClassNotFoundException, SQLException {
    Class.forName(this.assignDriverName());
    return DriverManager.getConnection(this.assignUrl(), this.assignDbUserName(), this.assignDbUserPassword());
  }

  protected abstract String assignDriverName();

  protected abstract String assignDbUserName();

  protected abstract String assignDbUserPassword();

  protected abstract String assignUrl();

}
