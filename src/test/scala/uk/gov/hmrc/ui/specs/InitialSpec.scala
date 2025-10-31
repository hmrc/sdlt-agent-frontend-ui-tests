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
import uk.gov.hmrc.ui.pages.{AgentsDetailsPage, AgentsNamePage, AuthWizard, FindAgentAddressPage, InitialPage, RemoveAgentPage}
import uk.gov.hmrc.ui.util.Users.LoginTypes.HASDIRECT
import uk.gov.hmrc.ui.util.Users.UserTypes.Organisation

class InitialSpec
    extends AnyFeatureSpec
    with BaseSpec
    with GivenWhenThen
    with ShouldVerb
    with BeforeAndAfterAll
    with BeforeAndAfterEach
    with Browser
    with ScreenshotOnFailure {

  Feature("SDLT Agent frontend Journeys") {
    Scenario("Add an agent when the Agent Overview page has no agents") {
      Given("User enters login using the Authority Wizard page")
      AuthWizard.login(HASDIRECT, Organisation, "STN002")
      Then("User navigates to Agent overview page")
      AgentsDetailsPage.verifyPageTitle("Manage Agents - Agent Details - Stamp Taxes Online - GOV.UK")
      Then("User clicks Add Agent button")
      AgentsDetailsPage.clickAddAgent()
      AgentsNamePage.verifyPageTitle(AgentsNamePage.pageTitle)
      AgentsNamePage.enterAgentName("Test Agent")
//      AgentsNamePage.clickSubmitButton()
//      Then("User navigates to Find address page")
//      FindAgentAddressPage.verifyPageTitle(FindAgentAddressPage.pageTitle)
//      When("User clicks on the link")
//      FindAgentAddressPage.clickAddressManually()
//      And("User enters the address manually")
//      FindAgentAddressPage.verifyPageTitle(FindAgentAddressPage.pageTitleForManualSearch)
//      FindAgentAddressPage.enterAddressManually("123", "ABC", "TE13 1ES")
//      Then("User is on the Review screen")
//      FindAgentAddressPage.verifyPageTitle(FindAgentAddressPage.pageTitleForAddressConfirmPage)
//      And("User clicks continue")
//      FindAgentAddressPage.clickSubmitButton()
    }

    Scenario("Add an agent when the Agent Overview page has a list of agents") {
      Given("User enters login using the Authority Wizard page")
      AuthWizard.login(HASDIRECT, Organisation, "STN001")
      Then("User navigates to Agent overview page")
      AgentsDetailsPage.verifyPageTitle("Manage Agents - Agent Details - Stamp Taxes Online - GOV.UK")
      Then("User clicks Add Agent button")
      AgentsDetailsPage.clickAddAgent()
      AgentsNamePage.verifyPageTitle(AgentsNamePage.pageTitle)
    }

    Scenario("Remove Agent journey - select No then Yes") {
      Given("User enters login using the Authority Wizard page")
      AuthWizard.login(HASDIRECT, Organisation, "STN001")
      Then("User navigates to Agent overview page")
      AgentsDetailsPage.verifyPageTitle("Manage Agents - Agent Details - Stamp Taxes Online - GOV.UK")
      Then("User clicks on Remove Agent link on Agent Details page")
      AgentsDetailsPage.clickRemoveAgent("Harborview Estates")
      Then("User verifies the remove agent page header")
      AgentsDetailsPage.verifyPageHeader("Are you sure you want to remove Harborview Estates?")
      Then("User clicks on No Radio button")
      RemoveAgentPage.radioButton(RemoveAgentPage.no)
      Then("User clicks on Continue button")
      RemoveAgentPage.clickSubmitButton()
      Then("User navigates to Agent overview page")
      AgentsDetailsPage.verifyPageTitle("Manage Agents - Agent Details - Stamp Taxes Online - GOV.UK")
      Then("User clicks on Remove Agent link on Agent Details page")
      AgentsDetailsPage.clickRemoveAgent("Harborview Estates")
      Then("User verifies the remove agent page header")
      AgentsDetailsPage.verifyPageHeader("Are you sure you want to remove Harborview Estates?")
      Then("User clicks on Yes Radio button")
      RemoveAgentPage.radioButton(RemoveAgentPage.yes)
      Then("User clicks on Continue button")
      RemoveAgentPage.clickSubmitButton()
      Then("User navigates to Agent overview page")
      AgentsDetailsPage.verifyPageTitle("Manage Agents - Agent Details - Stamp Taxes Online - GOV.UK")
    }
  }
}
