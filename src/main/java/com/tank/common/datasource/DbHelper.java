package com.tank.common.datasource;

import io.vavr.CheckedConsumer;
import io.vavr.CheckedFunction1;
import io.vavr.Function0;
import io.vavr.Function1;
import io.vavr.control.Option;
import io.vavr.control.Try;
import lombok.NonNull;
import lombok.val;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;

/**
 * @author tank198435163.com
 */
public class DbHelper {


  public DbHelper(@NonNull final RdbDataSource rdbDataSource) {
    this.rdbDataSource = rdbDataSource;
  }


  public <T> Collection<T> findWithoutCondition(@NonNull final String sql,
                                                @NonNull final CheckedFunction1<ResultSet, T> resultFun) throws Throwable {

    return this.findWithoutCondition(sql, Optional.empty(), resultFun);

  }

  private <T> Collection<T> findWithoutCondition(@NonNull String sql,
                                                 @NonNull final Optional<CheckedConsumer<PreparedStatement>> pstOpt,
                                                 @NonNull final CheckedFunction1<ResultSet, T> resultFun) throws Throwable {

    ResultSet cursor = null;
    Collection<T> collections = new HashSet<>();
    try (val conn = this.rdbDataSource.createConnection()) {
      val pst = conn.prepareStatement(sql);
      if (pstOpt.isPresent()) {
        pstOpt.get().accept(pst);
      }
      cursor = pst.executeQuery();
      while (cursor.next()) {
        final T record = resultFun.apply(cursor);
        collections.add(record);
      }
    } finally {
      if (Objects.nonNull(cursor)) {
        cursor.close();
      }
    }

    return collections;
  }

  private RdbDataSource rdbDataSource;

}
