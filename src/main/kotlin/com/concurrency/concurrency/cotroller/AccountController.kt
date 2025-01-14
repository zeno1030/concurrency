package com.concurrency.concurrency.cotroller

import com.concurrency.concurrency.domain.Account
import com.concurrency.concurrency.service.role.AccountService
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class AccountController(val accountService: AccountService) {

    @PostMapping("/{id}/deposit")
    fun deposit(@PathVariable id: Long, @RequestBody amount: Long): Account {
        return accountService.deposit(id, amount)
    }
}