//#full-example
package com.example

import akka.actor.{Actor, ActorRef, ActorSystem, Props}

//#main-class
object AkkaQuickstart extends App {
  val actorSystem = ActorSystem()

  class MyActor extends Actor {
    private val anotherActorRef: ActorRef = actorSystem.actorOf(Props[AnotherActor])

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
  private val anotherActorRef: ActorRef = actorSystem.actorOf(Props[AnotherActor], name = "anotherActor")

  myActorRef ! "Hello world"
  anotherActorRef ! "Hello world"

  println("Done")
}
