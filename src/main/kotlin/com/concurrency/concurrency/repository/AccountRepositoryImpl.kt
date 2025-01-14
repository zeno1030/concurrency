package com.concurrency.concurrency.repository

import com.concurrency.concurrency.domain.Account
import com.concurrency.concurrency.repository.role.AccountRepository
import org.springframework.stereotype.Repository
import java.lang.Math.random

@Repository
class AccountRepositoryImpl : AccountRepository {

    private val db: HashMap<Long, Account> = HashMap()

    override fun deposit(id: Long, amount: Long): Account {
        Thread.sleep(random().toLong() * 300L + 100)
        val account = Account(balance = amount, updateMillie = System.currentTimeMillis())
        db[id] = account
        return account
    }

    override fun findAccountBy(accountId: Long): Account {
        Thread.sleep(random().toLong() * 300L + 100)
        return db[accountId] ?: Account(balance = 0, updateMillie = System.currentTimeMillis())
    }
}