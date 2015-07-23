import org.specs2.mutable._
import org.specs2.runner._
import org.junit.runner._

import play.api.test._
import play.api.test.Helpers._

/**
 * Add your spec here.
 * You can mock out a whole application including requests, plugins etc.
 * For more information, consult the wiki.
 */
@RunWith(classOf[JUnitRunner])
class ApplicationSpec extends Specification {

  "Application" should {

    "render the index page" in new WithApplication{
      val home = route(FakeRequest(GET, "/")).get

      status(home) must equalTo(OK)
      //contentType(home) must beSome.which(_ == "text/html")
      contentAsString(home) must contain ("Welcome!")
    }
    
    "render the start page" in new WithApplication{
      val start = route(FakeRequest(GET, "/start/5")).get
      
      status(start) must equalTo(OK)
      //contentType(start) must beSome.which(_ == "text/html")
      contentAsString(start) must contain ("_ _ _ _ _")
    }
  }
}
