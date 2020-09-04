package controllers

import com.google.inject.Singleton
import com.rinotc.usecases.user.list.{UserListInputData, UserListUseCase}
import javax.inject.Inject
import play.api.mvc.{AbstractController, Action, AnyContent, ControllerComponents}
import viewmodels.user.{UserListViewModel, UserViewModel}

import scala.concurrent.ExecutionContext

@Singleton
class UserController @Inject()(
  cc: ControllerComponents,
  listUseCase: UserListUseCase
)(implicit ec: ExecutionContext) extends AbstractController(cc) {

  def index: Action[AnyContent] = Action.async { implicit request =>
    listUseCase.handle(new UserListInputData).users.map { users =>
      val viewModel = UserListViewModel(users.map(user => UserViewModel(user.id.value, user.name.value)))
      Ok(views.html.user.index(viewModel))
    }
  }

  def addInput(): Action[AnyContent] = Action { implicit request =>
    Ok
  }

  def detail(id: String): Action[AnyContent] = Action { implicit request =>
    Ok
  }
}
