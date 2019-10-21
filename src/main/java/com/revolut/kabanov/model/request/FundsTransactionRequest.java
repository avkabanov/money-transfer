package com.revolut.kabanov.model.request;

import java.math.BigDecimal;

/**
 * @author Алексей
 * 
 */
public class FundsTransactionRequest {
    public long fromId;
    public long toId;
    public BigDecimal amount;

    public FundsTransactionRequest() {
    }

    public FundsTransactionRequest(long fromId, long toId, BigDecimal amount) {
        this.fromId = fromId;
        this.toId = toId;
        this.amount = amount;
    }
}
