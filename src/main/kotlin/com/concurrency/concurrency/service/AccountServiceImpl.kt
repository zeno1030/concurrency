package com.concurrency.concurrency.service

import com.concurrency.concurrency.domain.Account
import com.concurrency.concurrency.repository.role.AccountRepository
import com.concurrency.concurrency.service.role.AccountService
import org.springframework.stereotype.Service
import java.util.concurrent.atomic.AtomicBoolean

@Service
class AccountServiceImpl(val repository: AccountRepository) : AccountService {
    private val isProcessing = AtomicBoolean(false)

    override fun deposit(id: Long, amount: Long): Account {
        if (isProcessing.compareAndSet(false, true)) {
            throw IllegalStateException("아직 처리 중 입니다.")
        }

        try {
            val account = repository.findAccountBy(id)
            val newBalance = account.balance + amount
            return repository.deposit(id, newBalance)
        } finally {
            isProcessing.set(false)
        }
    }

    override fun depositWithOutLock(id: Long, amount: Long): Account {
        val account = repository.findAccountBy(id)
        val newBalance = account.balance + amount
        return repository.deposit(id, newBalance)
    }
}