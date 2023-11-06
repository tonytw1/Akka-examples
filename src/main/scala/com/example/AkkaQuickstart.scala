//#full-example
package com.example


import akka.actor.{Actor, ActorRef, ActorSystem, Props}
class MyActor extends Actor {
  override def receive: Receive = {
    case m =>
      println("Received: " + m)
  }
}


//#main-class
object AkkaQuickstart extends App {
  val actorSystem = ActorSystem()

  private val myActorRef: ActorRef = actorSystem.actorOf(Props[MyActor], name = "myActor")
  myActorRef ! "Hello world"

  println("Done")
}

