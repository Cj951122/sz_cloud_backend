package com.lunz.fin.config.conf.metrics;

import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Tag;
import io.micrometer.core.instrument.config.MeterFilter;
import org.springframework.boot.actuate.autoconfigure.metrics.MeterRegistryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Collections;

/**
 * @author Liuruixia
 */
@Configuration
public class MetricsConfiguration {

    @Bean
    public MeterRegistryCustomizer<MeterRegistry> metricsCommonTags() throws UnknownHostException {

        InetAddress inetAddress = InetAddress.getLocalHost();
        return registry -> registry.config()
                .commonTags(Collections.singletonList(Tag.of("server", inetAddress.getHostName())))
                .meterFilter(MeterFilter.deny(id -> {
                    String uri = id.getTag("uri");
                    return uri != null && (uri.startsWith("/swagger") || uri.startsWith("root") || uri.startsWith("/**") || uri.startsWith("/webjars"));
                }));
    }
}
