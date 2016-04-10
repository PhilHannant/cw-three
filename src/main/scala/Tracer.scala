import java.io.File

import akka.actor.{ActorRef, Props, ActorSystem}
import com.mildlyskilled._

object Tracer extends App {

  val (infile, outfile) = ("src/main/resources/input.dat", "output.png")
  val scene = Scene.fromFile(infile)
  val t = new Trace
  render(scene, outfile, t.Width, t.Height)



  def render(scene: Scene, outfile: String, width: Int, height: Int) = {
    val image = new Image(width, height)

    // Init the coordinator -- must be done before starting it.
    Coordinator.init(image, outfile)
    //Set up the actor system
    val system = ActorSystem("Tracer")
    val coord = system.actorOf(Props(new Coordinator(outfile, image)))
    //First message sent to coordinator to begin calculation
    coord ! Calculate(scene)



  }

}
