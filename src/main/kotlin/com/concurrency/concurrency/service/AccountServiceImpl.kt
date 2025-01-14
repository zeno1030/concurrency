package com.concurrency.concurrency.service

import com.concurrency.concurrency.domain.Account
import com.concurrency.concurrency.repository.role.AccountRepository
import com.concurrency.concurrency.service.role.AccountService
import org.springframework.stereotype.Service

@Service
class AccountServiceImpl(val repository: AccountRepository) : AccountService {
    @Volatile private var isProcessing: Boolean = false

    @Synchronized
    override fun deposit(id: Long, amount: Long): Account {
        if (isProcessing) {
            throw IllegalStateException("아직 처리 중 입니다.")
        }
        isProcessing = true
        try {
            val account = repository.findAccountBy(id)
            val newBalance = account.balance + amount
            return repository.deposit(id, newBalance);
        } finally {
            isProcessing = false
        }
    }
}