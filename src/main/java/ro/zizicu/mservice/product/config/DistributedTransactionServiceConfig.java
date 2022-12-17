package ro.zizicu.mservice.product.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.web.context.annotation.RequestScope;

import lombok.RequiredArgsConstructor;
import ro.zizicu.nwbase.rest.TransactionCoordinatorRestClient;
import ro.zizicu.nwbase.service.DistributedTransactionService;
import ro.zizicu.nwbase.service.impl.DistributedTransactionServiceImpl;
import ro.zizicu.nwbase.transaction.support.DefaultTransactionStepExecutor;

@RequiredArgsConstructor
@Configuration
public class DistributedTransactionServiceConfig {

    private final PlatformTransactionManager transactionManager;
    private final TransactionCoordinatorRestClient restClient;
	
	@Bean
	@RequestScope
	public DefaultTransactionStepExecutor defaultTransactionStepExecutor() {
		return new DefaultTransactionStepExecutor(transactionManager, restClient);
	}
	
	@Bean
	public DistributedTransactionService distributedTransactionService() {
		return new DistributedTransactionServiceImpl(defaultTransactionStepExecutor());  
	}
	
}
