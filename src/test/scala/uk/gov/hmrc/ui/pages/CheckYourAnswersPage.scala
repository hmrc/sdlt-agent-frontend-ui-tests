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

package uk.gov.hmrc.ui.pages

import org.openqa.selenium.By

object CheckYourAnswersPage extends BasePage {

  override def pageUrl: String = "manage-agents/check-your-answers"

  val linkChangeAgentName: By                   = By.xpath("//a[@id='change-agent-name']")
  val linkChangeAgentAddress: By                = By.xpath("//a[@id='change-agent-address']")
  val linkChangeAgentContactTelephoneNumber: By = By.xpath(
    "//dt[contains(normalize-space(), 'Contact telephone number')]/following-sibling::dd//a[contains(normalize-space(), 'Change')]"
  )
  val linkChangeAgentContactEmail: By           = By.xpath(
    "//dt[contains(normalize-space(), 'Contact email')]/following-sibling::dd//a[contains(normalize-space(), 'Change')]"
  )

  override def pageTitle: String =
    "Check your answers – Manage agents - Stamp Taxes Online - GOV.UK"

  def clickChangeAgentName(): Unit = {
    val element = waitForElementToBeClickable(linkChangeAgentName)
    element.click()
  }

  def clickChangeAgentAddress(): Unit = {
    val element = waitForElementToBeClickable(linkChangeAgentAddress)
    element.click()
  }

  def clickChangeAgentContactTelephoneNumber(): Unit = {
    val element = waitForElementToBeClickable(linkChangeAgentContactTelephoneNumber)
    element.click()
  }

  def clickChangeAgentContactEmail(): Unit = {
    val element = waitForElementToBeClickable(linkChangeAgentContactEmail)
    element.click()
  }

}
