package controllers

import com.google.inject.Singleton
import com.rinotc.domain.model.user.{DatabaseError, UserId, UserName, UserNotFound}
import com.rinotc.usecases.user.add.{UserAddInputData, UserAddUseCase}
import com.rinotc.usecases.user.delete.{UserDeleteInputData, UserDeleteUseCase}
import com.rinotc.usecases.user.detail.{UserDetailInputData, UserDetailUseCase}
import com.rinotc.usecases.user.list.{UserListInputData, UserListUseCase}
import com.rinotc.usecases.user.update.{UserUpdateInputData, UserUpdateUseCase}
import javax.inject.Inject
import lib.view.converter.user.UserRoleConverter
import play.api.Logger
import play.api.data.Form
import play.api.mvc.{Action, AnyContent, MessagesAbstractController, MessagesControllerComponents}
import viewmodels.user.add.{UserAddForm, UserAddInputViewModel}
import viewmodels.user.update.{UserUpdateForm, UserUpdateInputViewModel}
import viewmodels.user.{UserDetailViewModel, UserListViewModel, UserViewModel}

import scala.concurrent.{ExecutionContext, Future}

@Singleton
class UserController @Inject()(
  cc: MessagesControllerComponents,
  userListUseCase: UserListUseCase,
  userAddUseCase: UserAddUseCase,
  userDetailUseCase: UserDetailUseCase,
  userDeleteUseCase: UserDeleteUseCase,
  userUpdateUseCase: UserUpdateUseCase
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


  def detail(id: String): Action[AnyContent] = Action.async { implicit request =>
    userDetailUseCase.handle(UserDetailInputData(UserId(id))).user.map {
      case Some(user) =>
        val viewModel = UserDetailViewModel(user.id.value, user.name.value, UserRoleConverter.convert(user.role))
        Ok(views.html.user.detail(viewModel))
      case None => NotFound("UserNotFound")
    }
  }

  def updateInput(id: String): Action[AnyContent] = Action.async { implicit request =>
    userDetailUseCase.handle(UserDetailInputData(UserId(id))).user.map {
      case Some(user) =>
        val initialForm = UserUpdateForm.UserUpdateForm(user.name.value)
        Ok(views.html.user.update.input(UserUpdateInputViewModel(user.id.value), UserUpdateForm.form.fill(initialForm)))
      case None => NotFound("UserNotFound")
    }
  }

  def updateInputSubmit(id: String): Action[AnyContent]  = Action.async { implicit request =>
    val errorFunction = { formWithErrors: Form[UserUpdateForm.UserUpdateForm] =>
      Future.successful(BadRequest(formWithErrors.toString))
    }

    val successFunction = { data: UserUpdateForm.UserUpdateForm =>
      val inputData = UserUpdateInputData(UserId(id), UserName(data.name))
      userUpdateUseCase.handle(inputData).output.map {
        case Right(_) => Redirect(routes.UserController.index())
        case Left(error) => BadRequest(error.toString)
      }
    }

    UserUpdateForm.form.bindFromRequest.fold(errorFunction, successFunction)
  }

  def deleteSubmit(id: String): Action[AnyContent] = Action.async { implicit request =>
    val inputData = UserDeleteInputData(UserId(id))
    userDeleteUseCase.handle(inputData).output.map {
      case Right(_) => Redirect(routes.UserController.index())
      case Left(error) => error match {
        case UserNotFound => NotFound("User Not Found")
        case DatabaseError => InternalServerError("Database Error")
      }
    }
  }
}
