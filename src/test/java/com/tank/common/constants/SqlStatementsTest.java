package com.tank.common.constants;

import com.tank.common.util.FieldsExtractor;
import com.tank.common.vo.SimpleOrder;
import lombok.val;
import org.junit.Before;
import org.junit.Test;

import static com.tank.common.util.FieldsExtractor.selectFieldsFrom;

public class SqlStatementsTest {

  @Test
  public void testPagination() {
    String sql = String.format("select %s from orders ", selectFieldsFrom(SimpleOrder.class));
    sql = this.sqlStatements.pagination(sql, 1, pageNo -> pageNo - 1);
    System.out.println(sql);
  }

  @Before
  public void initInstance() {
    this.sqlStatements = new SqlStatements();
  }

  private SqlStatements sqlStatements;

}