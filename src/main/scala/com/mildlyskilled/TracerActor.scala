package com.mildlyskilled

import akka.actor.{Actor, ActorLogging}

/**
  * Created by philhannant on 02/04/2016.
  */
class TracerActor() extends Actor with ActorLogging {
  var numRound: Int = 0
  def receive = {
    case Work(scene, y) => scene.traceImageLine(scene.t.Width, scene.t.Height, y: Int)
      numRound += 1
      println("worker active for " + numRound + " time")
      sender ! Result
  }

}
