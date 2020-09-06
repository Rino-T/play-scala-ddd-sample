package viewmodels.user.add

import play.api.data.Form
import play.api.data.Forms._


object UserAddForm {

  case class UserAddForm(name: String, roleId: String)

  val form: Form[UserAddForm] = Form(
    mapping(
      "name" -> text(minLength = 3, maxLength = 10),
      "roleId" -> nonEmptyText
      )(UserAddForm.apply)(UserAddForm.unapply)
    )
}
