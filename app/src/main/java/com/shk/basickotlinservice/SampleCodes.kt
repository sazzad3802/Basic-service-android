package com.shk.basickotlinservice


// fun main() {

//     fun calculate(x: Int, y: Int, doSomething: (Int, Int) -> Int): Int {
//     	return doSomething(x, y)
// 	}
//     val result1 = calculate(5, 4){ a, b -> a + b } // provide implementation for do something from calling, not in the method

//     val sum = {a:Int, b:Int -> a + b}

//     val result2 = sum(3, 4)

//     println(result1)
//     println(result2)

// }

// ..................................................

// class Person {
//     var name: String = "John"
//         get() {
//             println("Getting name")
//             return field
//         }
//         set(value) {
//             println("Setting name to $value")
//             field = value
//         }
// }

// fun main() {
//     val person = Person()
//     println(person.name) // Output: Getting name, John
//     person.name = "Jane" // Output: Setting name to Jane
// }

// .................................................

// class Person(val name: String) {
//     fun introduce() {
//         println("Hello, my name is $name")
//     }
// }

// fun Person.isSamePerson(gname: String): Boolean{
//     return this.name == gname
// }

// fun String.isPalindrome(): Boolean {
//     val reversed = this.reversed()
//     return this == reversed
// }

// fun main() {
//     val text = "radar"
//     println(text.isPalindrome()) // Output: true

//     val person = Person("John")
//     person.introduce() // Output: Hello, my name is John

//     println(if(person.isSamePerson("John")) "You are searching for me!" else "Wrong number")
// }

// .................................................................

// fun add(a: Int, b: Int): Int {
//     return a + b
// }

// fun subtract(a: Int, b: Int): Int {
//     return a - b
// }

// fun performOperation(myOp: (Int, Int) -> Int) {
//     val result = myOp(10, 5)
//     println(result)
// }

// fun main() {
//     performOperation(::add) // Output: 15
//     performOperation(::subtract) // Output: 5
// }

// .........................................................................

// class Box<T>(val item: T)

// fun main() {
//     val box1 = Box(42)
//     val box2 = Box("Hello")

//     val item1: Int = box1.item
//     val item2: String = box2.item

//     println(item1) // Output: 42
//     println(item2) // Output: Hello
// }

// ..................................................................................


// class Person {
//     var name: String = ""
//     var age: Int = 0

//     init{
//         println("init called")
//     }

//     constructor(name: String) {
//         this.name = name
//         println("first constructor called")
//     }

//     constructor(name: String, age: Int) {
//         this.name = name
//         this.age = age
//         println("second constructor called")
//     }
// }

// fun main() {
//     val person1 = Person("John")
//     val person2 = Person("Jane", 30)
// }

// .....................................................


// sealed class Shape

// class Circle(val radius: Double) : Shape()
// class Rectangle(val width: Double, val height: Double) : Shape()
// class Triangle(val base: Double, val height: Double) : Shape()

// fun getArea(shape: Shape): Double = when (shape) {
//     is Circle -> Math.PI * shape.radius * shape.radius
//     is Rectangle -> shape.width * shape.height
//     is Triangle -> 0.5 * shape.base * shape.height
// }

// fun main() {
//     val circle = Circle(5.0)
//     val rectangle = Rectangle(3.0, 4.0)
//     val triangle = Triangle(2.0, 5.0)

//     println(getArea(circle))
//     println(getArea(rectangle))
//     println(getArea(triangle))
// }

// ......................................................

// suspend fun fetchData(): String {
//     delay(1000L)
//     return withContext(Dispatchers.IO) {
//         // Perform network request
//         // Return result
//     }
// }

// fun main() = runBlocking {
//     val result = fetchData()
//     println(result)
// }

// ........................................................

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

// fun fetchData(): Flow<Int> = flow {
//     for (i in 1..5) {
//         delay(1000L)
//         emit(i)
//     }
// }

// fun main() = runBlocking {
//     fetchData()
//         .map { it * 2 }
//         .collect { value -> println(value)}
// }

// ..................................................

// class Counter {
//     private var count = 0

//     fun increment() {
// //         println("increment")
// //             count++
// //             println(count)
//         synchronized(this) {
//             println("increment")
//             count++
//             println(count)
//         }
//     }
// }

// fun main() {
//     val counter = Counter()

//     Thread {
//         for (i in 1..5){
//             println("1st thread")
//             counter.increment()
//         }
//     }.start()


//     Thread {
//         for (i in 1..5){
//             println("2nd thread")
//             counter.increment()
//         }
//     }.start()


//     Thread.sleep(1000) // Wait for threads to complete
// }

// .......................................................................

// val channel = Channel<Int>(Channel.CONFLATED)

// GlobalSope.launch{
//     for (i in 1..5){
//         channel.send(i)
//     }
//     channel.close()
// }

// GlobalScope.launch{
//      for (value in channel) {
//         println("Received: $value")
//     }
// }

// fun main() = runBlocking {
//     val channel = Channel<String>()

//     launch {
//         val items = listOf("Apple", "Banana", "Cherry")
//         for (item in items) {
//             channel.send(item)
//             println("Produced: $item")
//         }
//         channel.close()
//     }

//     // Consumer coroutine
//     launch {
//         for (item in channel) {
//             println("Consumed: $item")
//         }
//     }
// }

// ...............................................................
