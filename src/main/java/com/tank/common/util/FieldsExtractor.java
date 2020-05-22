package com.tank.common.util;


import io.vavr.collection.List;
import io.vavr.collection.Stream;
import lombok.NonNull;

import java.lang.reflect.Field;

/**
 * @author tank198435163.com
 */
public final class FieldsExtractor {

  public static <T> String extractField(@NonNull final Class<T> clazz) {
    List<String> data = Stream.of(clazz.getDeclaredFields()).map(Field::getName)
            .filter(name -> name.indexOf("_") != -1).toList();
    return data.mkString(",");
  }
}
