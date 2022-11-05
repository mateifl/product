package ro.zizicu.mservice.product.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ro.zizicu.mservice.product.services.support.TransactionStep;

import java.util.Hashtable;
import java.util.Map;


@Configuration
public class DistributedTransactionConfig {

    @Bean
    public Map<Long, TransactionStep> distributedTransactionMap() {
        return new Hashtable<>();
    }

}
