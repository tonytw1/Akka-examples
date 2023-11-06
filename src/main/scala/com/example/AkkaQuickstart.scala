//#full-example
package com.example

import akka.actor.{Actor, ActorRef, ActorSystem, Props}

//#main-class
object AkkaQuickstart extends App {
  val actorSystem = ActorSystem()
  private val anotherActorRef: ActorRef = actorSystem.actorOf(Props[AnotherActor], name = "anotherActor")

  class MyActor extends Actor {
    override def receive: Receive = {
      case m =>
        println("MyActor Received: " + m)
        anotherActorRef ! "Relay " + m
    }
  }

  class AnotherActor extends Actor {
    override def receive: Receive = {
      case m =>
        println("AnotherActor received: " + m)
    }
  }

  private val myActorRef: ActorRef = actorSystem.actorOf(Props[MyActor], name = "myActor")

  myActorRef ! "Hello world"
  anotherActorRef ! "Hello world"

  class MyComponent(anotherActorRef: ActorRef) {

    def doSomething(): Unit = {
      println("Did something")
      anotherActorRef ! "Hey I did something"
    }
  }

  private val myComponent = new MyComponent(anotherActorRef)

  myComponent.doSomething()

  println("Done")
}


