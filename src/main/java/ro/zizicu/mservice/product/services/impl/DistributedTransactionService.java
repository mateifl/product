//package ro.zizicu.mservice.product.services.impl;
//
//
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.kafka.annotation.KafkaListener;
//import org.springframework.stereotype.Service;
//import ro.zizicu.mservice.product.services.support.DistributedTransaction;
//import ro.zizicu.nwbase.transaction.CommitMessage;
//
//import java.util.Map;
//
//@Service
//@RequiredArgsConstructor
//@Slf4j
//public class DistributedTransactionService {
//    private final Map<Long, DistributedTransaction> distributedTransactionMap;
//
//    @KafkaListener(topics = "stockUpdateTopic", groupId = "test")
//    public void commitListener(CommitMessage commitMessage) {
//        log.debug("listener active, transaction {}", commitMessage.getTransactionId());
//        distributedTransactionMap.get(commitMessage.getTransactionId()).commit();
//    }
//
//}
