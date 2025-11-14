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

object FindAgentAddressPage extends BasePage {

  override def pageUrl: String = "manage-agents/enter-address"

  override def pageTitle: String =
    "Find your UK address - Find the agent’s address – Agent details - GOV.UK"

  val pageTitleForManualSearch: String =
    "Enter your address - Find the agent’s address – Agent details - GOV.UK"

  val pageTitleForAddressConfirmPage: String =
    "Review and confirm - Find the agent’s address – Agent details - GOV.UK"

  def enterPostCode(postCode: String): Unit = {
    input(Locators.txtPostCode, postCode)
    clickSubmitButton()
  }

  def clickAddressManually(): Unit = click(By.linkText(Locators.lnkAddrManually))

  def enterAddressManually(addressline1: String, town: String, addressPostcode: String): Unit = {
    input(Locators.txtAddress1, addressline1)
    input(Locators.txtTown, town)
    input(Locators.txtAddressPostCode, addressPostcode)
    clickSubmitButton()
  }
}
