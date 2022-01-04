package ro.zizicu.mservice.product.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaAdmin;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

@Configuration
public class KafkaConfiguration {

	private final Environment environment;
	
	public KafkaConfiguration(Environment environment) {
		this.environment = environment;
	}
	
	@Bean
	public KafkaAdmin admin() {
	    Map<String, Object> configs = new HashMap<>();
	    configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, environment.getProperty("kafka.url"));
	    return new KafkaAdmin(configs);
	}

	@Bean
	public NewTopic stockUpdateTopic() {
	    return TopicBuilder.name("stockUpdateTopic")
	            .partitions(2)
	            .compact()
	            .build();
	}


}
