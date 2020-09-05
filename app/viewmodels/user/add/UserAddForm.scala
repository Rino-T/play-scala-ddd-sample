package viewmodels.user.add

object UserAddForm {
  import play.api.data.Forms._
  import play.api.data.Form

  case class UserAddFormData(name: String, role: String)

  val form: Form[UserAddFormData] = Form(
    mapping(
      "name" -> text(minLength = 3, maxLength = 10),
      "role" -> nonEmptyText
    )(UserAddFormData.apply)(UserAddFormData.unapply)
  )
}
