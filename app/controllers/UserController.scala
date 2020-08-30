package controllers

import com.google.inject.Singleton
import javax.inject.Inject
import play.api.mvc.{AbstractController, ControllerComponents}

@Singleton
class UserController @Inject()(
  cc: ControllerComponents
) extends AbstractController(cc) {

  def index = ???
}
