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

import org.openqa.selenium.{By, JavascriptExecutor}

object AgentsDetailsPage extends BasePage {

  override def pageUrl: String = "/manage-agents/agent-overview?paginationIndex=1"

  val btnAddAgent: By = By.xpath("//button[@type = 'submit']")
  val linkChange: By  = By.linkText("Change")

  override def pageTitle: String =
    "Manage Agents - Agent Details - Stamp Taxes Online - GOV.UK"

  def lnkRemoveAgent(agentName: String): By =
    By.xpath(
      s"//dt[contains(normalize-space(), '$agentName')]" +
        "/following-sibling::dd//a[contains(normalize-space(), 'Remove')]"
    )

  def clickAddAgent(): Unit = {
    val element = waitForElementToBeClickable(btnAddAgent)
    //    element.click()

    val js = driver.asInstanceOf[JavascriptExecutor]
    js.executeScript("""
    const btn = document.querySelector('button.govuk-button');
    if (btn) { btn.click(); } """)
  }

  def clickRemoveAgent(agentName: String): Unit = {
    waitForElementToBeClickable(lnkRemoveAgent(agentName))
    click(lnkRemoveAgent(agentName))
  }
}
