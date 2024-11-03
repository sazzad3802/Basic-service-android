package com.shk.basickotlinservice

import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

suspend fun mains(){

    val handler = CoroutineExceptionHandler {  _, exception ->
        println("Caught $exception")
    }

    val parentJob = CoroutineScope(Dispatchers.IO).launch{

        println("parent job started")

        try {
            var childJob = launch(Dispatchers.IO){
                println("child job started")

                // if want the process to block and hold until the operation gets resolved, mention runBlocking
                withContext(Dispatchers.IO){
                    delay(2000)
                    println("child job continues -> 2 secs gone")
                    delay(2000)
                    println("child job end after -> 4 secs")
                }
                /*runBlocking {
                    delay(2000)
                    println("child job continues -> 2 secs gone")
                    delay(2000)
                    println("child job end after 4 secs")
                }*/
            }
        } catch (e: Exception) {
            println(e.toString())
        }

        delay(3000)
        println("parent job end after 3 secs")

    }

    /*delay(3000)
    println("parent job cancelled after 3 secs unexpectedly")
    parentJob.cancel()*/

    parentJob.join()
    println("parent job completed")
}




/// sequential call -> one after one
suspend fun mainss(){
    usingRunBlock()
    usingCoroutineLaunch()
    usingCoroutineAsync()
}



/// asynchronous call -> parallel execution
suspend fun main(): Unit = coroutineScope {// better to use supervisorScope => therefore if one child coroutine fails, others keep going, saves memory leak
    launch { usingRunBlock() }
    launch { usingCoroutineLaunch() }
    launch { usingCoroutineAsync() }
}



suspend fun usingRunBlock(){
    runBlocking {
        println("before run block")
//        val res = executeTask()
        delay(2000)
        println("inside run block")
        delay(2000)
        println("after run block")
    }
    println("outside run block")
}


suspend fun usingCoroutineLaunch(){
    val job = CoroutineScope(Dispatchers.IO).launch {
        println("before Launch")
//        val res = executeTask()
        delay(2000)
        println("inside launch")
        delay(2000)
        println("after launch")
    }
    job.join()   /// this is the key for launch
    println("outside Launch")
}

suspend fun usingCoroutineAsync(){
    val job = CoroutineScope(Dispatchers.IO).async {
        println("before Async")
//        val res = executeTask()
        delay(2000)
        println("inside async")
        delay(2000)
        println("after Async")
    }
    job.await()   /// this is the key for async await
    println("outside async")
}

suspend fun executeTask(){
    delay(2000)
    println("inside")
    delay(2000)
}