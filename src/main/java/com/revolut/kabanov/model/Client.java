package com.revolut.kabanov.model;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.concurrent.locks.ReentrantLock;

import javax.annotation.concurrent.NotThreadSafe;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * @author Алексей
 * 
 */
@NotThreadSafe
@Entity
@Table(name = "clients")
public class Client {
    
    @Transient
    private final ReentrantLock clientOperationLock = new ReentrantLock();

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "balance")
    private BigDecimal balance = new BigDecimal(0);

    @Column(name = "client_name")
    private String name;

    public Client() {
    }

    public Client(String name, BigDecimal balance) {
        this.balance = balance;
        this.name = name;
    }

    public Client(Long id, String name, BigDecimal balance) {
        this.id = id;
        this.balance = balance;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void clientOperationLock() {
        clientOperationLock.lock();
    }
    
    public void accountOperationUnlock() {
        clientOperationLock.unlock();
    }
    
    public void deposit(BigDecimal amount) {
        balance = balance.add(amount);
    }
    
    public void withdraw(BigDecimal amount) {
        balance = balance.subtract(amount);
    }
    
    public void setBalance(BigDecimal amount) {
        this.balance = amount;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return Objects.equals(clientOperationLock, client.clientOperationLock) &&
                Objects.equals(id, client.id) &&
                Objects.equals(balance, client.balance) &&
                Objects.equals(name, client.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(clientOperationLock, id, balance, name);
    }
}
