package com.mildlyskilled


sealed trait RenMessage
case class Calculate(scene: Scene) extends RenMessage
case class Work(scene: Scene, line: Int) extends RenMessage
case class Result() extends RenMessage
