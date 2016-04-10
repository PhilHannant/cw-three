package com.mildlyskilled

import akka.actor.{ActorRef, ActorSystem, Actor, ActorLogging}

//Tracer actor which carries out the work in Scene
class TracerActor() extends Actor with ActorLogging {
  var numRound: Int = 0
  def receive = {
    case Work(scene, y, coord: ActorRef) => scene.traceImageLine(scene.t.Width, scene.t.Height, y: Int,  coord: ActorRef)
      numRound += 1
      println("worker active for " + numRound + " time")
      sender ! Result
  }

}
