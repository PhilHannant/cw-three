package com.mildlyskilled

import akka.actor.{ActorSystem, ActorRef}

//Messages that are sent between the actors
sealed trait RenMessage
case class Calculate(scene: Scene) extends RenMessage
case class Work(scene: Scene, line: Int, coord: ActorRef) extends RenMessage
case class Set(x: Int, y: Int,  c: Colour) extends RenMessage
case object Result extends RenMessage
