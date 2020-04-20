package org.myorg.quickstart;

import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.common.functions.ReduceFunction;
import org.apache.flink.api.java.utils.ParameterTool;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.apache.flink.util.Collector;

import java.util.Arrays;

public class SocketWindowWordCount {
    public static void main(String[] args) throws Exception {
        //连接端口号
        final int port;
        try {
            // final ParameterTool params = ParameterTool.fromArgs(args);
            // port = params.getInt("port");
            port = 9000;
        } catch (Exception e) {
            System.out.println("No port specified. Please run 'SocketWindowWordCount --port <port>'");
            return;
        }
        //获取执行环节
        final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        // 读取txt文件
        // String filePath = "D:\\测试.txt";
        // DataStream<String> text = env.readTextFile(filePath);
        //获取连接socket输入数据
        DataStream<String> text = env.socketTextStream("192.168.228.135", 9000,"\n");
        // DataStream<String> text = env.fromCollection(Arrays.asList("fff", "bbbbb"));


        //解析数据、对数据进行分组、窗口函数和统计个数
        // DataStream<WordWithCount> windowCounts = text.flatMap(new FlatMapFunction<String, WordWithCount>() {
        //         //
        //         //     private static final long serialVersionUID = 6800597108091365154L;
        //         //
        //         //     @Override
        //         //     public void flatMap(String value, Collector<WordWithCount> out) throws Exception {
        //         //         for (String word : value.split("\\s")) {
        //         //             out.collect(new WordWithCount(word, 1));
        //         //         }
        //         //     }
        //         // })
        //         //     .keyBy("word")
        //         //     .timeWindow(Time.seconds(10), Time.seconds(1))
        //         //     .reduce((ReduceFunction<WordWithCount>) (value1, value2) -> new WordWithCount(value1.word, value1.count + value2.count));
        //         // windowCounts.print().setParallelism(1);

        /**
         * 最简单的String追剧字符串
         */
        // DataStream<String> stringAppend = text.flatMap(new FlatMapFunction<String, String>() {
        //     @Override
        //     public void flatMap(String s, Collector<String> collector) throws Exception {
        //         collector.collect(s + "fff");
        //     }
        // });
        // stringAppend.print();


        //解析数据、对数据进行分组、窗口函数和统计个数
        DataStream<WordWithCount> windowCounts = text.flatMap(new FlatMapFunction<String, WordWithCount>() {
            private static final long serialVersionUID = 6800597108091365154L;

            @Override
            public void flatMap(String value, Collector<WordWithCount> out) throws Exception {
                for (String word : value.split("\\s")) {
                    out.collect(new WordWithCount(word, 1));
                }
            }
        })
            .keyBy("word")
            .timeWindow(Time.seconds(10), Time.seconds(1))
            .sum("word");
                windowCounts.print().setParallelism(1);

        env.execute("Socket Window WordCount");

    }

}
