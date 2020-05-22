package com.tank.common.util;

import com.tank.common.vo.SimpleOrder;
import org.junit.Test;

public class FieldsExtractorTest {

  @Test
  public void extractField() {
    String fieldsStr = FieldsExtractor.selectFieldsFrom(SimpleOrder.class);
    System.out.println(fieldsStr);
  }
}