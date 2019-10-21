package com.revolut.kabanov.model.response;

import java.math.BigDecimal;

/**
 * @author Алексей
 * 
 */
public class FundsTransactionResponse {

    public long fromId;
    public long toId;
    public BigDecimal amount;

    public FundsTransactionResponse() {
    }

    public FundsTransactionResponse(long fromId, long toId, BigDecimal amount) {
        this.fromId = fromId;
        this.toId = toId;
        this.amount = amount;
    }

    public long getFromId() {
        return fromId;
    }

    public void setFromId(long fromId) {
        this.fromId = fromId;
    }

    public long getToId() {
        return toId;
    }

    public void setToId(long toId) {
        this.toId = toId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
