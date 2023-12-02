package com.jzel.m450bowling.server.business.service

import org.springframework.stereotype.Service
import java.util.concurrent.Callable
import java.util.concurrent.Executors
import java.util.concurrent.Future


@Service
class SequentialExecutorService {
    private val executorService = Executors.newSingleThreadExecutor()
    fun <T> submitTask(task: Callable<T>?): Future<T> {
        return executorService.submit(task)
    }
}
