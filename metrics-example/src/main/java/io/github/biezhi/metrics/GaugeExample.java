package io.github.biezhi.metrics;

import com.codahale.metrics.ConsoleReporter;
import com.codahale.metrics.Gauge;
import com.codahale.metrics.MetricRegistry;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.TimeUnit;

/**
 * 衡量一个待处理队列中任务的个数
 */
public class GaugeExample {

    public static Queue<String> q = new LinkedList<>();

    public static void main(String[] args) throws InterruptedException {
        MetricRegistry  registry = new MetricRegistry();
        ConsoleReporter reporter = ConsoleReporter.forRegistry(registry).build();
        reporter.start(1, TimeUnit.SECONDS);
        registry.register(MetricRegistry.name(GaugeExample.class, "queue", "size"),
                (Gauge<Integer>) () -> q.size());

        while (true) {
            Thread.sleep(1000);
            q.add("Job-xxx");
        }
    }
}