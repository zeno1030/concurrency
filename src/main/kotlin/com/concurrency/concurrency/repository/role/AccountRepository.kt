package com.concurrency.concurrency.repository.role

import com.concurrency.concurrency.domain.Account

interface AccountRepository {
    fun deposit(id: Long, amount: Long): Account
    fun findAccountBy(accountId: Long): Account
}