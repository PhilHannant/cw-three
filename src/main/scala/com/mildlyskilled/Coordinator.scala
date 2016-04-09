package com.mildlyskilled

import akka.actor._
import akka.routing._


object Coordinator {
  def init(im: Image, of: String) = {
    image = im
    outfile = of
    waiting = im.width * im.height
  }

  // Number of pixels we're waiting for to be set.
  var waiting = 0
  var outfile: String = null
  var image: Image = null

  def set(x: Int, y: Int, c: Colour) = {
    image(x, y) = c
    waiting -= 1
  }

  def print = {
    image.print(outfile)
  }
}

class Coordinator(outputfile: String, image: Image) extends Actor with ActorLogging {

  val start: Long = System.currentTimeMillis()
  val workerRouter = context.actorOf(BalancingPool(image.height / 4).props(Props[TracerActor]), "workerRouter")
  var resultCounter = 0
  var s: Scene = null

  def receive = {
    case Calculate(scene: Scene) =>
      for (y <- 0 until image.height) workerRouter ! Work(scene, y, self)
      s = scene
    case Result => resultCounter += 1
      println("returns in " + resultCounter)
      if (resultCounter == image.height) {
        Coordinator.print
        println("rays cast " + s.t.rayCount)
        println("rays hit " + s.t.hitCount)
        println("light " + s.t.lightCount)
        println("dark " + s.t.darkCount)
        println("Job time = " + (System.currentTimeMillis() - start))
        context.system.terminate()
      }
    case Set(x: Int, y: Int, c: Colour) => {
      Coordinator.set(x, y, c)
    }
  }
}





