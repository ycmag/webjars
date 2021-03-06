package utils


import akka.util.Timeout
import play.api.test._

import scala.concurrent.duration._
import scala.util.Try

class LicenseDetectorSpec extends PlaySpecification with GlobalApplication {

  override implicit def defaultAwaitTimeout: Timeout = 30.seconds

  lazy val licenseDetector = application.injector.instanceOf[LicenseDetector]

  "gitHubLicenseDetect" should {
    "detect the license" in {
      await(licenseDetector.gitHubLicenseDetect(Try("twbs/bootstrap"))) must beEqualTo("MIT")
    }
    "detect another license" in {
      await(licenseDetector.gitHubLicenseDetect(Try("angular/angular"))) must beEqualTo("MIT")
    }
    "detect another license" in {
      await(licenseDetector.gitHubLicenseDetect(Try("T00rk/bootstrap-material-datetimepicker"))) must beEqualTo("MIT")
    }
  }

}
