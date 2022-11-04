package ro.zizicu.mservice.product.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ro.zizicu.mservice.product.services.support.DistributedTransaction;

import java.util.Hashtable;
import java.util.Map;


@Configuration
public class DistributedTransactionConfig {

    @Bean
    public Map<Long, DistributedTransaction> distributedTransactionMap() {
        return new Hashtable<>();
    }

}
