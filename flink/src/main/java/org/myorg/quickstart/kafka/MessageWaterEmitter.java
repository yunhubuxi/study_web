package org.myorg.quickstart.kafka;


import org.apache.flink.streaming.api.functions.AssignerWithPunctuatedWatermarks;
import org.apache.flink.streaming.api.watermark.Watermark;

/**
 * @Description    MessageWaterEmitter 根据Kafka消息确定Flink的水位
 * @Author         0262000099 Hengtai Nie
 * @CreateDate     2018/9/21 11:26
 */
public class MessageWaterEmitter implements AssignerWithPunctuatedWatermarks<String> {

    @Override
    public Watermark checkAndGetNextWatermark(String lastElement, long extractedTimestamp) {
        if (lastElement != null && lastElement.contains(",")) {
            String[] parts = lastElement.split(",");
            return new Watermark(Long.parseLong(parts[0]));
        }
        return null;
    }

    @Override
    public long extractTimestamp(String element, long previousElementTimestamp) {
        if (element != null && element.contains(",")) {
            String[] parts = element.split(",");
            return Long.parseLong(parts[0]);
        }
        return 0L;
    }
}
