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

import org.openqa.selenium.{By, WebElement}
import org.openqa.selenium.support.ui.{ExpectedConditions, WebDriverWait}
import java.time.Duration

object AgentContactDetailsPage extends BasePage {

  override def pageUrl: String = "manage-agents/contact-details"
  val txtTelephoneNo: By       = By.id("phone")
  val txtEmailId: By           = By.id("email")

  override def pageTitle: String =
    "Agent’s contact details – Agent details - Stamp Taxes Online - GOV.UK"

  def enterContactDetails(telephoneNo: String, emailId: String): Unit = {
    input(txtTelephoneNo, telephoneNo)
    input(txtEmailId, emailId)
    clickSubmitButton()
  }

  private def getFieldValue(selector: By, fieldName: String): String = {
    val element = waitForVisibilityOfElement(selector)
    val value   = element.getAttribute("value")
    if (value == null) {
      ""
    } else {
      value
    }
  }

  def getExistingTelephoneNumber(): String = {
    waitForPage()
    waitForVisibilityOfElement(txtTelephoneNo)
    waitForVisibilityOfElement(txtEmailId)

    val value = getFieldValue(txtTelephoneNo, "telephone number")
    if (value.isEmpty) {
      val retryValue = getFieldValue(txtTelephoneNo, "telephone number")
      if (retryValue.isEmpty) {
        throw new RuntimeException(
          s"Existing telephone number not found in change mode. Field value: '$retryValue'"
        )
      }
      retryValue
    } else {
      value
    }
  }

  def getExistingEmail(): String = {
    waitForPage()
    waitForVisibilityOfElement(txtTelephoneNo)
    waitForVisibilityOfElement(txtEmailId)

    val value = getFieldValue(txtEmailId, "email")
    if (value.isEmpty) {
      val retryValue = getFieldValue(txtEmailId, "email")
      if (retryValue.isEmpty) {
        throw new RuntimeException(
          s"Existing email not found in change mode. Field value: '$retryValue'"
        )
      }
      retryValue
    } else {
      value
    }
  }

  def updateTelephoneNumber(newTelephoneNo: String): Unit = {
    waitForPage()
    val existingEmail = getExistingEmail()
    if (existingEmail.isEmpty) {
      throw new RuntimeException(
        s"Cannot update telephone number - existing email is empty. This suggests the form is not in change mode or not pre-filled."
      )
    }

    val phoneElement = waitForVisibilityOfElement(txtTelephoneNo)
    phoneElement.clear()
    phoneElement.sendKeys(newTelephoneNo)
    clickSubmitButton()
  }

  def updateEmail(newEmail: String): Unit = {
    waitForPage()

    val existingPhone = getExistingTelephoneNumber()
    if (existingPhone.isEmpty) {
      throw new RuntimeException(
        s"Cannot update email - existing telephone number is empty. This suggests the form is not in change mode or not pre-filled."
      )
    }

    val emailElement = waitForVisibilityOfElement(txtEmailId)
    emailElement.clear()
    emailElement.sendKeys(newEmail)
    clickSubmitButton()
  }

}
