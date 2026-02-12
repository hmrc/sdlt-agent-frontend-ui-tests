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

object AgentsDetailsPage extends BasePage {

  override def pageUrl: String = "/manage-agents/agent-overview?paginationIndex=1"
  val btnAddAgent: By          = By.cssSelector(".govuk-button")
  val linkChange: By           =
    By.xpath("//dd[contains(@class, 'govuk-summary-list__actions')]//a[contains(normalize-space(), 'Change')]")

  override def pageTitle: String =
    "Agent overview – Manage agents - Stamp Taxes Online - GOV.UK"

  def lnkRemoveAgent(agentName: String): By =
    By.xpath(
      s"//dt[contains(normalize-space(), '$agentName')]" +
        "/following-sibling::dd//a[contains(normalize-space(), 'Remove')]"
    )

  def lnkChangeAgent(agentName: String): By =
    By.xpath(
      s"//dt[contains(normalize-space(), '$agentName')]" +
        "/following-sibling::dd//a[contains(normalize-space(), 'Change')]"
    )

  def clickAddAgent(): Unit = {
    val element = waitForElementToBeClickable(btnAddAgent)
    element.click()
  }

  def clickRemoveAgent(agentName: String): Unit = {
    waitForElementToBeClickable(lnkRemoveAgent(agentName))
    click(lnkRemoveAgent(agentName))
  }

  def clickChangeAgent(agentName: String): Unit = {
    waitForElementToBeClickable(lnkChangeAgent(agentName))
    click(lnkChangeAgent(agentName))
  }

  def clickAnyChangeAgent(): Unit = {
    val element = waitForElementToBeClickable(linkChange)
    element.click()
  }

  def getFirstAgentName(): String = {
    val firstAgentDt = By.xpath("//dl[contains(@class, 'govuk-summary-list')]//dt[1]")
    val element      = waitForVisibilityOfElement(firstAgentDt)
    element.getText.trim
  }
}
