package com.tank.common.constants;

import com.tank.common.datasource.DbHelper;
import com.tank.common.datasource.impl.MysqlDataSource;
import com.tank.common.util.ResultsUtils;
import com.tank.common.vo.SimpleItem;
import com.tank.common.vo.SimpleOrder;
import io.vavr.collection.List;
import org.junit.Before;
import org.junit.Test;

import java.util.Collection;

import static com.tank.common.util.FieldsExtractor.selectFieldsFrom;

public class SqlStatementsTest {

  @Test
  public void testPagination() throws Throwable {
    String orderSql = String.format("select %s from orders", selectFieldsFrom(SimpleOrder.class));
    orderSql = this.sqlStatements.pagination(orderSql, 1, pageNo -> pageNo - 1);
    Collection<Long> orderIds = this.dbHelper.findWithoutCondition(orderSql, rs -> rs.getLong("id"));
    String seq = List.of(orderIds).mkString(",");

    String orderItemSql = String.format("select %s from order_items where order_id in (%s)",
            selectFieldsFrom(SimpleItem.class), seq.replace("[", "")
                    .replace("]", ""));

    Collection<SimpleItem> simpleItems = this.dbHelper.findWithoutCondition(orderItemSql, rs -> this.resultsUtils.result2Object(rs, SimpleItem.class));
    simpleItems.stream().map(SimpleItem::getSell_type).forEach(System.out::println);
  }

  @Before
  public void initInstance() {
    this.sqlStatements = new SqlStatements();
    this.resultsUtils = new ResultsUtils();
    this.dbHelper = new DbHelper(new MysqlDataSource());
  }

  private SqlStatements sqlStatements;

  private ResultsUtils resultsUtils;

  private DbHelper dbHelper;

}