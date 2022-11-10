package ro.zizicu.mservice.product.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.zizicu.mservice.product.services.distibuted.transaction.DefaultTransactionStepExecutor;
import ro.zizicu.nwbase.transaction.ExecuteTransactionMessage;


@RestController
@RequestMapping(value = "/transaction")
@Slf4j
@RequiredArgsConstructor
public class TransactionController {

    private final DefaultTransactionStepExecutor transactionStepExecutor;

    @PostMapping
    public void executeTransaction(@RequestBody ExecuteTransactionMessage executeTransactionMessage) {
        if( executeTransactionMessage.getTransactionResult().equalsIgnoreCase("COMMIT") )
            transactionStepExecutor.commit(executeTransactionMessage.getTransactionId());
        else if(executeTransactionMessage.getTransactionResult().equalsIgnoreCase("ROLLBACK"))
            transactionStepExecutor.rollback(executeTransactionMessage.getTransactionId());
    }



}
