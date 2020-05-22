package com.tank;

import io.vavr.CheckedConsumer;
import lombok.NonNull;
import lombok.val;
import org.apache.flink.api.common.typeinfo.TypeHint;
import org.apache.flink.streaming.api.CheckpointingMode;
import org.apache.flink.streaming.api.TimeCharacteristic;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

/**
 * @author tank198435163.com
 */
public class App {

  public static void main(String[] args) throws Throwable {
    val app = new App();
    app.handleLogical(env -> {
      env.fromElements(1, 2, 3)
              .uid("dataSource")
              .map(d -> d + 1)
              .uid("first transform")
              .returns(new TypeHint<Integer>() {
              })
              .filter(d -> d > 2)
              .uid("second transform")
              .returns(new TypeHint<Integer>() {
              })
              .print("result");
    }, "only first demo");

  }


  private void handleLogical(@NonNull final CheckedConsumer<StreamExecutionEnvironment> checkedConsumer,
                             @NonNull final String jobName) throws Throwable {
    val env = StreamExecutionEnvironment.getExecutionEnvironment();
    env.setStreamTimeCharacteristic(TimeCharacteristic.EventTime);
    val cfg = env.getCheckpointConfig();
    cfg.setCheckpointingMode(CheckpointingMode.EXACTLY_ONCE);
    checkedConsumer.accept(env);
    env.execute(jobName);
  }

}
