package com.tank.dao;

import com.tank.common.datasource.DbHelper;
import com.tank.common.datasource.impl.MysqlDataSource;
import com.tank.common.util.ResultsUtils;
import com.tank.common.vo.SimpleOrder;
import lombok.NonNull;

import java.util.Collection;

/**
 * @author tank198435163.com
 */
public final class SimpleOrderDAO {

  public SimpleOrderDAO() {
    this.dbHelper = new DbHelper(new MysqlDataSource());
    this.resultsUtils = new ResultsUtils();
  }

  public Collection<SimpleOrder> querySimpleOrders(@NonNull final String sql) throws Throwable {
    return this.dbHelper.findWithoutCondition(sql, rs -> this.resultsUtils.result2Object(rs, SimpleOrder.class));
  }


  private DbHelper dbHelper;
  private ResultsUtils resultsUtils;
}
