package com.tank.common.util;

import io.vavr.CheckedFunction1;
import io.vavr.control.Try;
import lombok.NonNull;
import lombok.val;

import java.lang.reflect.Method;
import java.sql.*;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.sql.Types.*;

/**
 * @author tank198435163.com
 */
public class ResultsUtils {

  public ResultsUtils() {
    intTypes.add(BIGINT);
    intTypes.add(INTEGER);
    intTypes.add(SMALLINT);
    intTypes.add(TINYINT);


    strTypes.add(NVARCHAR);
    strTypes.add(VARCHAR);
    strTypes.add(CHAR);
  }

  /**
   * @param rs
   * @param clazz
   * @param <T>
   * @return
   */
  public <T> T result2Object(@NonNull final ResultSet rs,
                             @NonNull final Class<T> clazz) throws Throwable {

    T obj = clazz.newInstance();
    val metaData = rs.getMetaData();
    int columnCount = metaData.getColumnCount();

    Map<String, Method> methods = new HashMap<>(2 << 7);

    val targetMethods = Stream.of(obj.getClass().getDeclaredMethods())
            .filter(method -> method.getName().startsWith("set"))
            .collect(Collectors.toList());

    for (Method method : targetMethods) {
      methods.putIfAbsent(method.getName().toLowerCase().substring(3), method);
    }

    for (int index = 1; index <= columnCount; index++) {

      val i = index;
      val columnName = metaData.getColumnName(index);
      metaData.getColumnType(index);
      int type = metaData.getColumnType(index);
      Method method = methods.get(columnName);

      boolean successful = assignValue(type, dataType -> dataType == Types.BIGINT, method, obj, rs, row -> row.getLong(i));
      if (successful) {
        continue;
      }

      successful = assignValue(type, dataType -> intTypes.contains(dataType), method, obj, rs, row -> {
        Long value = rs.getLong(i);
        return value > Integer.MAX_VALUE ? value : value.intValue();
      });
      if (successful) {
        continue;
      }

      successful = assignValue(type, dataType -> strTypes.contains(dataType), method, obj, rs, row -> row.getString(i));
      if (successful) {
        continue;
      }

      successful = assignValue(type, dataType -> dataType == TIMESTAMP, method, obj, rs, row -> row.getTimestamp(i) == null ? "0000-00-00 00:00:00" : row.getTimestamp(i).toString());
      if (successful) {
        continue;
      }

    }


    return obj;
  }


  private <T> boolean assignValue(int type, Predicate<Integer> predicate,
                                  final Method method,
                                  @NonNull Object target,
                                  @NonNull ResultSet rs,
                                  @NonNull CheckedFunction1<ResultSet, T> fun) throws Throwable {

    boolean isSuitable = predicate.test(type) && method != null;

    if (isSuitable) {
      Object value = fun.apply(rs);
      method.invoke(target, value);
      return true;
    }
    return false;
  }


  private Set<Integer> intTypes = new HashSet();

  private Set<Integer> strTypes = new HashSet<>();


}
