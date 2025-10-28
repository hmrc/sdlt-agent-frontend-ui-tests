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

  override def pageUrl: String = "/manage-agents/agent-overview?storn=STN001&paginationIndex=1"
  val btnAddAgent: By          = By.xpath("//a[@role = 'button' and contains(@class, 'govuk-button')]")

  override def pageTitle: String =
    "Agent Details - Manage Agents - Stamp Taxes Online - GOV.UK"

  def clickAddAgent(): Unit =
    click(btnAddAgent)

}
