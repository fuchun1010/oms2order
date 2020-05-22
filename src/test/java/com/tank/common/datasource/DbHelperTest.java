package com.tank.common.datasource;

import com.tank.common.datasource.impl.MysqlDataSource;
import com.tank.common.util.ResultsUtils;
import com.tank.common.vo.SimpleOrder;
import lombok.val;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Collection;
import java.util.stream.Collectors;

import static com.tank.common.util.FieldsExtractor.selectFieldsFrom;

public class DbHelperTest {

  @Test
  public void findWithoutCondition() throws Throwable {
    val sql = String.format("select %s from orders limit 200", selectFieldsFrom(SimpleOrder.class));
    ResultsUtils resultsUtils = new ResultsUtils();
    Collection<SimpleOrder> simpleOrders = this.dbHelper.findWithoutCondition(sql, row -> resultsUtils.result2Object(row, SimpleOrder.class));
    val results = simpleOrders.stream().collect(Collectors.toList());
    Assert.assertTrue(simpleOrders.size() > 0);
    simpleOrders.clear();
  }

  @Before
  public void init() {
    this.dbHelper = new DbHelper(new MysqlDataSource());
  }

  private DbHelper dbHelper;
}