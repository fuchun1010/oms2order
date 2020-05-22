package com.tank.common.util;

import com.tank.common.vo.SimpleOrder;
import org.junit.Test;

import static org.junit.Assert.*;

public class FieldsExtractorTest {

  @Test
  public void extractField() {
    String fieldsStr = FieldsExtractor.extractField(SimpleOrder.class);
    System.out.println(fieldsStr);
  }
}