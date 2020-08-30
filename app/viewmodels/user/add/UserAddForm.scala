package viewmodels.user.add

object UserAddForm {
  import play.api.data.Forms._
  import play.api.data.Form

  case class UserAddFormData(name: String, age: Int)

  val form: Form[UserAddFormData] = Form(
    mapping(
      "name" -> text(minLength = 3, maxLength = 10),
      "age" -> number(min = 0)
    )(UserAddFormData.apply)(UserAddFormData.unapply)
  )
}