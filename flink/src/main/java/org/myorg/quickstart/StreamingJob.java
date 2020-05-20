/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.myorg.quickstart;

import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.api.java.tuple.Tuple3;
import org.apache.flink.streaming.api.TimeCharacteristic;
import org.apache.flink.streaming.api.datastream.DataStreamSink;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.AssignerWithPeriodicWatermarks;
import org.apache.flink.streaming.api.watermark.Watermark;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.apache.flink.streaming.api.windowing.triggers.ContinuousEventTimeTrigger;
import org.apache.flink.util.Collector;

import javax.annotation.Nullable;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Skeleton for a Flink Streaming Job.
 *
 * <p>For a tutorial how to write a Flink streaming application, check the
 * tutorials and examples on the <a href="http://flink.apache.org/docs/stable/">Flink Website</a>.
 *
 * <p>To package your application into a JAR file for execution, run
 * 'mvn clean package' on the command line.
 *
 * <p>If you change the name of the main class (with the public static void main(String[] args))
 * method, change the respective entry in the POM.xml file (simply search for 'mainClass').
 */
public class StreamingJob {

	public static void main(String[] args) throws Exception {
		final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
		env.setStreamTimeCharacteristic(TimeCharacteristic.EventTime);

		DataStreamSource<String> stream = env.socketTextStream("192.168.228.135", 9000);
		DataStreamSink<Tuple3<String, String, Integer>> print = stream.assignTimestampsAndWatermarks(new AssignerWithPeriodicWatermarks<String>() {
			private Long currentMaxTimestamp = 3000L;
			private Long maxOutOfOrderness = 3000L;

			@Override
			public long extractTimestamp(String element, long previousElementTimestamp) {
				Long timestamp = System.currentTimeMillis();
				currentMaxTimestamp = Math.max(currentMaxTimestamp, timestamp);
				return timestamp;
			}

			@Nullable
			@Override
			public Watermark getCurrentWatermark() {
				return new Watermark(currentMaxTimestamp - maxOutOfOrderness);
			}

		}).flatMap(new FlatMapFunction<String, Tuple2<String, Integer>>() {
			@Override
			public void flatMap(String value, Collector<Tuple2<String, Integer>> out) throws Exception {
				String[] tokens = value.toLowerCase().split(("\\W+"));
				for (String token : tokens) {
					if (token.length() > 0) {
						out.collect(Tuple2.of(token, 1));
					}
				}
			}
		})
			.keyBy(0)
			.timeWindow(Time.seconds(60))
			.trigger(ContinuousEventTimeTrigger.of(Time.seconds(10)))
			.sum(1)
			.map(new MapFunction<Tuple2<String, Integer>, Tuple3<String, String, Integer>>() {
				@Override
				public Tuple3<String, String, Integer> map(Tuple2<String, Integer> value) throws Exception {
					SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					String format = simpleDateFormat.format(new Date());
					return Tuple3.of(value.f0, format, value.f1);
				}
			}).print();
		// execute program
		env.execute("Flink Streaming Java API Skeleton");
	}
}
