package counter

import org.scalatestplus.play.{OneServerPerSuite, PlaySpec}
import play.api.test.FakeRequest
import play.api.{Application, ApplicationLoader, Environment, Logger}
import play.api.test.Helpers._

class CounterTest extends PlaySpec with OneServerPerSuite {

  override lazy val app: Application =
    new ApplicationLoader {
      def load(context: ApplicationLoader.Context): Application = {
        new Components(context).application
      }
    }.load(ApplicationLoader.createContext(Environment.simple()))

  "test index page" in {
    val response = route(app, FakeRequest("GET", "/")).value
    assert(status(response) === OK)
    assert(contentAsString(response) === "Hello, world!")
  }

}
