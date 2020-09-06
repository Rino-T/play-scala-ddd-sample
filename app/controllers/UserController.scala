package controllers

import com.google.inject.Singleton
import com.rinotc.usecases.user.add.{UserAddInputData, UserAddUseCase}
import com.rinotc.usecases.user.list.{UserListInputData, UserListUseCase}
import javax.inject.Inject
import lib.view.converter.user.UserRoleConverter
import play.api.Logger
import play.api.data.Form
import play.api.mvc.{Action, AnyContent, MessagesAbstractController, MessagesControllerComponents}
import viewmodels.user.add.{UserAddForm, UserAddInputViewModel}
import viewmodels.user.{UserListViewModel, UserViewModel}

import scala.concurrent.{ExecutionContext, Future}

@Singleton
class UserController @Inject()(
  cc: MessagesControllerComponents,
  userListUseCase: UserListUseCase,
  userAddUseCase: UserAddUseCase
)(implicit ec: ExecutionContext) extends MessagesAbstractController(cc) {

  val userControllerLogger: Logger = Logger("application.userController")

  def index: Action[AnyContent] = Action.async { implicit request =>
    userListUseCase.handle(new UserListInputData).users.map { users =>
      val viewModel = UserListViewModel(users.map(user => UserViewModel(user.id.value, user.name.value)))
      Ok(views.html.user.index(viewModel))
    }
  }

  def addInput(): Action[AnyContent] = Action { implicit request =>
    Ok(views.html.user.add.input(new UserAddInputViewModel, UserAddForm.form))
  }


  def addInputSubmit(): Action[AnyContent] = Action.async { implicit request =>
    val errorFunction = { formWithErrors: Form[UserAddForm.UserAddForm] =>
      Future.successful(BadRequest(formWithErrors.toString))
    }

    val successFunction = { data: UserAddForm.UserAddForm =>
      UserRoleConverter.convert(data.roleId) match {
        case Right(role) =>
          val inputData = UserAddInputData(data.name, role)
          userAddUseCase.handle(inputData).userId.map { _ =>
            Redirect(routes.UserController.index())
          }
        case Left(error) => Future.successful(BadRequest(error.toString))
      }
    }

    UserAddForm.form.bindFromRequest.fold(errorFunction, successFunction)
  }


  def detail(id: String): Action[AnyContent] = Action { implicit request =>
    Ok
  }
}
