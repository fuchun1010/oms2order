package com.tank.common.util;


import io.vavr.collection.List;
import io.vavr.collection.Stream;
import lombok.NonNull;

import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Set;

/**
 * @author tank198435163.com
 */
public final class FieldsExtractor {

  public static <T> String selectFieldsFrom(@NonNull final Class<T> clazz) {
    Set<String> specialFields = new HashSet<>();
    specialFields.add("id");
    specialFields.add("status");
    List<String> data = Stream.of(clazz.getDeclaredFields()).map(Field::getName)
            .filter(name -> name.indexOf("_") != -1 || specialFields.contains(name))
            .toList();
    return data.mkString(",");
  }
}
