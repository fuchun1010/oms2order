package com.tank.common;

import com.tank.common.datasource.impl.MysqlDataSource;

import static io.vavr.API.*;

import com.tank.common.datasource.RdbDataSource;
import io.vavr.control.Try;
import lombok.val;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;

import static io.vavr.Patterns.$Failure;
import static io.vavr.Patterns.$Success;

public class RdbDataSourceTest {

  @Test
  public void createConnection() {
    Try<Connection> connectionTry = Try.of(() -> this.rdbDataSource.createConnection());
    val result = Match(connectionTry).of(
            Case($Success($()), conn -> {
              boolean isOpen = Try.of(() -> {
                conn.close();
                System.out.println("close db connection success");
                return true;
              }).getOrElse(false);
              return isOpen;
            }),
            Case($Failure($()), rs -> false)
    );
    Assert.assertTrue(result);
  }

  @Before
  public void init() {
    this.rdbDataSource = new MysqlDataSource();
  }

  private RdbDataSource rdbDataSource;
}