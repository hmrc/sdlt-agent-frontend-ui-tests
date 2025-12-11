/*
 * Copyright 2025 HM Revenue & Customs
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package uk.gov.hmrc.ui.specs

import org.scalatest.{BeforeAndAfterAll, BeforeAndAfterEach, GivenWhenThen}
import org.scalatest.featurespec.AnyFeatureSpec
import org.scalatest.verbs.ShouldVerb
import uk.gov.hmrc.selenium.webdriver.{Browser, ScreenshotOnFailure}
import uk.gov.hmrc.ui.pages.{AccessDeniedPage, AgentsDetailsPage, AuthWizard, UnauthorisedIndividualErrorPage}
import uk.gov.hmrc.ui.util.Users.LoginTypes.HASDIRECT
import uk.gov.hmrc.ui.util.Users.UserTypes.Organisation
import uk.gov.hmrc.ui.util.Users.UserTypes.Individual

class ErrorHandlingSpec
    extends AnyFeatureSpec
    with BaseSpec
    with GivenWhenThen
    with ShouldVerb
    with BeforeAndAfterAll
    with BeforeAndAfterEach
    with Browser
    with ScreenshotOnFailure {

  Feature("SDLT Management frontend error handling") {
    //    Scenario("Display Page not found for invalid Management details URL") {
    //      Given("User enters login using the Authority Wizard page")
    //      AuthWizard.login(HASDIRECT, Organisation, "STN001")
    //      Then("User should be navigated to the home page")
    //      .verifyPageTitle(HomePage.pageTitle)
    //
    //    }

    Scenario("Display Access denied page when user tries to access management service without enrolment") {
      Given("User enters login using the Authority Wizard page")
      AuthWizard.login(HASDIRECT, Organisation, "", "STN001")
      Then("User should be navigated to the home page")
      AccessDeniedPage.verifyPageTitle(AccessDeniedPage.pageTitle)
      AccessDeniedPage.click(AccessDeniedPage.continueToAccountLinkText)
      AgentsDetailsPage.verifyPageTitle("Sign in to HMRC - Sign in to HMRC online services - GOV.UK")
    }

    Scenario("Display error page when user select individual in affinity group at a glance page") {
      Given("User enters login using the Authority Wizard page")
      AuthWizard.loginAsIndividual(HASDIRECT, Individual)
      Then("User should be navigated to the error page")
      AccessDeniedPage.verifyPageTitle(UnauthorisedIndividualErrorPage.pageTitle)
    }
  }
}
