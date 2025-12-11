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

object AccessDeniedPage extends BasePage {

  override def pageUrl: String = "/access-denied"

  val continueToAccountLinkText: By = By.linkText("Continue to your account")

  override def pageTitle: String =
    "Sorry, there is a problem with the service - Stamp Taxes Online - GOV.UK"
}

object UnauthorisedIndividualErrorPage extends BasePage {

  val pageUrl: String = "unauthorised/individual"

  val pageTitle: String =
    "Sorry, there is a problem with this service - Stamp Taxes Online - GOV.UK"
}

object PageNotFound extends BasePage {

  val pageUrl: String = "/page-not-found"

  val pageTitle: String =
    "Page not found - Stamp Taxes Online - GOV.UK"
}
