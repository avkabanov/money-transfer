package com.revolut.kabanov.service.transaction;

import java.math.BigDecimal;

import javax.annotation.Nonnull;

import com.revolut.kabanov.model.Client;
import com.revolut.kabanov.model.exception.InsufficietFundsException;

/**
 * @author Алексей
 * 
 */
public interface TransactionService {

    /**
     * @return transferred amount
     */
    BigDecimal transferFunds(@Nonnull Client from, @Nonnull Client to, @Nonnull BigDecimal amount) throws InsufficietFundsException;

    void withdraw(@Nonnull Client client, @Nonnull BigDecimal amount);

    void deposit(@Nonnull Client client, @Nonnull BigDecimal amount);
    
}
