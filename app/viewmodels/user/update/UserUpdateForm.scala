package viewmodels.user.update

import play.api.data.Form
import play.api.data.Forms._

object UserUpdateForm {

  case class UserUpdateForm(name: String)

  val form: Form[UserUpdateForm] = Form(
    mapping(
      "name" -> text(minLength = 3, maxLength = 10)
    )(UserUpdateForm.apply)(UserUpdateForm.unapply)
  )
}
