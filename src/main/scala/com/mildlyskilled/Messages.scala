package com.mildlyskilled

import akka.actor.{ActorSystem, ActorRef}


sealed trait RenMessage
case class Calculate(scene: Scene, coord: ActorRef) extends RenMessage
case class Work(scene: Scene, line: Int, coord: ActorRef) extends RenMessage
case class Set(x: Int, y: Int,  c: Colour) extends RenMessage
case object Result extends RenMessage
