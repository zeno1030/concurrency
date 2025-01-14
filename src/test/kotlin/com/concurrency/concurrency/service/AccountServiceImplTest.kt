package com.concurrency.concurrency.service

import com.concurrency.concurrency.service.role.AccountService
import org.junit.jupiter.api.DisplayName
import org.springframework.boot.test.context.SpringBootTest
import kotlin.test.Test

@SpringBootTest
class AccountServiceImplTest(val accountService: AccountService) {

    @Test
    @DisplayName("동시성 처리 테스트")
    fun depositTest(){
        //given
        //when
        repeat(2){
            accountService.deposit(1, 5000)
        }
        //then
    }
}