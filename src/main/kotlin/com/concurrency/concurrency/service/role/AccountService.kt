package com.concurrency.concurrency.service.role

import com.concurrency.concurrency.domain.Account


interface AccountService {
    fun deposit(id: Long, amount: Long): Account
    fun depositWithOutLock(id: Long, amount: Long): Account
}