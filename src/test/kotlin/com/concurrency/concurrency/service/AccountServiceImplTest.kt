package com.concurrency.concurrency.service

import com.concurrency.concurrency.service.role.AccountService
import org.junit.jupiter.api.DisplayName
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit
import java.util.concurrent.atomic.AtomicInteger
import kotlin.test.Test

@SpringBootTest
class AccountServiceImplTest() {

    @Autowired
    lateinit var accountService: AccountService

    @Test
    @DisplayName("동시성 처리 테스트")
    fun depositTest(){
        // given
        val threadPool = Executors.newFixedThreadPool(10) //병렬 처리를 위한 쓰레드 생성
        val exceptions = AtomicInteger(0) // IllegalStateException 발생 횟수 추적

        // when
        repeat(20) { // 20번 동시 실행
            threadPool.submit {
                try {
                    accountService.deposit(1, 5000)
                } catch (e: IllegalStateException) {
                    exceptions.incrementAndGet()
                }
            }
        }

        threadPool.shutdown()
        threadPool.awaitTermination(1, TimeUnit.SECONDS)

        // then
        // 예외 발생 확인
        assert(exceptions.get() > 0) { "IllegalStateException이 발생해야 합니다." }
    }
}