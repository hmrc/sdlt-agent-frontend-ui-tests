/*
 * Copyright 2026 HM Revenue & Customs
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
import uk.gov.hmrc.ui.pages.{AccessDeniedPage, AgentContactDetailsPage, AgentsDetailsPage, AgentsNamePage, AuthWizard, CheckYourAnswersPage, DoYouWantToAddContactDetailsPage, FeedBackPage, FindAgentAddressPage, RemoveAgentPage}
import uk.gov.hmrc.ui.util.Users.LoginTypes.HASDIRECT
import uk.gov.hmrc.ui.util.Users.UserTypes.Organisation
import uk.gov.hmrc.ui.util.Users.UserTypes.Agent_Trust

class AddAgentSpec
    extends AnyFeatureSpec
    with BaseSpec
    with GivenWhenThen
    with ShouldVerb
    with BeforeAndAfterAll
    with BeforeAndAfterEach
    with Browser
    with ScreenshotOnFailure {

  Feature("SDLT Agent frontend Journeys") {
    Scenario("Add an agent when the Agent details page has no agents") {
      Given("User enters login using the Authority Wizard page")
      AuthWizard.login(HASDIRECT, Organisation, "IR-SDLT-ORG", "STN002")
      When("User navigates to Agent details page")
      AgentsDetailsPage.verifyPageTitle(AgentsDetailsPage.pageTitle)
      And("User clicks Add Agent button")
      AgentsDetailsPage.clickAddAgent()
      AgentsNamePage.verifyPageTitle(AgentsNamePage.pageTitle)
      AgentsNamePage.enterAgentName("Test Agent. Rugby")
      AgentsNamePage.clickSubmitButton()
      And("User navigates to Find address page")
      FindAgentAddressPage.verifyPageTitle(FindAgentAddressPage.pageTitle)
      And("User clicks on the link to enter address manually")
      FindAgentAddressPage.clickAddressManually()
      And("User enters the address manually")
      FindAgentAddressPage.verifyPageTitle(FindAgentAddressPage.pageTitleForManualSearch)
      FindAgentAddressPage.enterAddressManually("123", "ABC", "TE13 1ES")
      And("User is on the address review screen")
      FindAgentAddressPage.verifyPageTitle(FindAgentAddressPage.pageTitleForAddressConfirmPage)
      And("User clicks confirm address button")
      FindAgentAddressPage.clickSubmitButton()
      And("User is navigated to do you want to add contact details page")
      DoYouWantToAddContactDetailsPage.verifyPageTitle(DoYouWantToAddContactDetailsPage.pageTitle)
      And("User selects Yes to add contact details")
      DoYouWantToAddContactDetailsPage.radioButton(DoYouWantToAddContactDetailsPage.yes)
      DoYouWantToAddContactDetailsPage.clickSubmitButton()
      And("User is navigated to agent contact details page")
      AgentContactDetailsPage.verifyPageTitle(AgentContactDetailsPage.pageTitle)
      And("User enters contact details and continues")
      AgentContactDetailsPage.enterContactDetails("0123456789", "test@email.com")
      Then("User is navigated to check your answers page")
      CheckYourAnswersPage.verifyPageTitle(CheckYourAnswersPage.pageTitle)
      CheckYourAnswersPage.clickSubmitButton()
      And("User navigates to Agent details page")
      AgentsDetailsPage.verifyPageTitle(AgentsDetailsPage.pageTitle)
      And("User verifies success message is displayed")
      AgentsDetailsPage.verifySuccessBannerMessage("You have added Test Agent. Rugby")
    }

    Scenario("Add an agent when the Agent details page has a list of agents") {
      Given("User enters login using the Authority Wizard page")
      AuthWizard.login(HASDIRECT, Organisation, "IR-SDLT-ORG", "STN004")
      When("User navigates to Agent details page")
      AgentsDetailsPage.verifyPageTitle(AgentsDetailsPage.pageTitle)
      Then("User clicks Add Agent button")
      AgentsDetailsPage.clickAddAgent()
      AgentsNamePage.verifyPageTitle(AgentsNamePage.pageTitle)
    }

    Scenario("Remove Agent journey - select No then Yes") {
      Given("User enters login using the Authority Wizard page")
      AuthWizard.login(HASDIRECT, Organisation, "IR-SDLT-ORG", "STN001")
      Then("User navigates to Agent details page")
      AgentsDetailsPage.verifyPageTitle(AgentsDetailsPage.pageTitle)
      Then("User clicks on Remove Agent link on Agent details page")
      AgentsDetailsPage.clickRemoveAgent("Harborview Estates")
      And("User verifies Remove Agent page title")
      RemoveAgentPage.verifyPageTitle(RemoveAgentPage.pageTitle)
      Then("User verifies the remove agent page header")
      AgentsDetailsPage.verifyPageHeader("Are you sure you want to remove Harborview Estates?")
      Then("User clicks on No Radio button")
      RemoveAgentPage.radioButton(RemoveAgentPage.no)
      Then("User clicks on Continue button")
      RemoveAgentPage.clickSubmitButton()
      Then("User navigates to Agent details page")
      AgentsDetailsPage.verifyPageTitle(AgentsDetailsPage.pageTitle)
      Then("User clicks on Remove Agent link on Agent details page")
      AgentsDetailsPage.clickRemoveAgent("Harborview Estates")
      Then("User verifies the remove agent page header")
      AgentsDetailsPage.verifyPageHeader("Are you sure you want to remove Harborview Estates?")
      Then("User clicks on Yes Radio button")
      RemoveAgentPage.radioButton(RemoveAgentPage.yes)
      Then("User clicks on Continue button")
      RemoveAgentPage.clickSubmitButton()
      Then("User navigates to Agent details page")
      AgentsDetailsPage.verifyPageTitle(AgentsDetailsPage.pageTitle)
      AgentsDetailsPage.verifySuccessBannerMessage("You have removed Harborview Estates")
    }

    Scenario("Check Auth Auard Agent journey") {
      Given("User enters login using the Authority Wizard page using org as affinity group and org as enrolment key")
      AuthWizard.login(HASDIRECT, Organisation, "IR-SDLT-ORG", "STN001")
      Then("User navigates to Agent details page")
      AgentsDetailsPage.verifyPageTitle(AgentsDetailsPage.pageTitle)
      Then("User navigates back to Authority Wizard page")
      AgentsDetailsPage.clickBackLink()
      Then("User navigates to agent page using Agent as affinity group and Agent as enrolment key")
      AuthWizard.loginAsAgent(HASDIRECT, Agent_Trust, "IR-SDLT-AGENT", "STN001", "Activated")
      Then("User navigates to Agent details page with Agent affinity group and Agent enrolment key")
      AgentsDetailsPage.verifyPageTitle(AgentsDetailsPage.pageTitle)
      AgentsDetailsPage.clickBackLink()
      Then("User navigates to agent page using Agent as affinity group and Org as enrolment key")
      AuthWizard.loginAsAgent(HASDIRECT, Agent_Trust, "IR-SDLT-AGENT", "STN001", "NotYetActivated")
      Then(
        "User navigates to Agent details page with Agent affinity group and Agent enrolment key with NotYetActivated"
      )
      AgentsDetailsPage.verifyPageTitle(AgentsDetailsPage.pageTitle)
      AgentsDetailsPage.clickBackLink()
      Then("User navigates to agent page using Agent as affinity group and Org as enrolment key")
      AuthWizard.loginAsAgent(HASDIRECT, Agent_Trust, "IR-SDLT-ORG", "STN001", "Activated")
      Then("User navigates to Agent details page with Agent affinity group and Org enrolment key")
      AccessDeniedPage.verifyPageTitle(AccessDeniedPage.pageTitle)
    }
  }
}
