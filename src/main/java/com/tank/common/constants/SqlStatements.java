package com.tank.common.constants;

import io.vavr.Function1;
import lombok.NonNull;

/**
 * @author tank198435163.com
 */
public final class SqlStatements {

  public <T> String pagination(
          @NonNull final String sql,
          @NonNull final Integer pageNo,
          @NonNull final Function1<Integer, Integer> fun) {

    return pagination(sql, pageNo, 10, fun);

  }

  public <T> String pagination(
          @NonNull final String sql,
          @NonNull final Integer pageNo,
          @NonNull final Integer batchSize,
          @NonNull final Function1<Integer, Integer> fun) {

    return String.format("%s limit %d,%d", sql, fun.apply(pageNo) * 10, batchSize);

  }

}
