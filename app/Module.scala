import java.time.Clock

import com.google.inject.AbstractModule
import com.rinotc.domain.application.user._
import com.rinotc.domain.model.user.UserRepository
import com.rinotc.usecases.user.add.UserAddUseCase
import com.rinotc.usecases.user.delete.UserDeleteUseCase
import com.rinotc.usecases.user.detail.UserDetailUseCase
import com.rinotc.usecases.user.list.UserListUseCase
import com.rinotc.usecases.user.update.UserUpdateUseCase
import gateways.user.UserRepositoryImpl

/**
 * This class is a Guice module that tells Guice how to bind several
 * different types. This Guice module is created when the Play
 * application starts.
 *
 * Play will automatically use any class called `Module` that is in
 * the root package. You can create modules in other locations by
 * adding `play.modules.enabled` settings to the `application.conf`
 * configuration file.
 */
class Module extends AbstractModule {

  override def configure(): Unit = {
    // Use the system clock as the default implementation of Clock
    bind(classOf[Clock]).toInstance(Clock.systemDefaultZone)

    // UserCase User
    bind(classOf[UserListUseCase]).to(classOf[UserListInteractor])
    bind(classOf[UserAddUseCase]).to(classOf[UserAddInteractor])
    bind(classOf[UserDetailUseCase]).to(classOf[UserDetailInteractor])
    bind(classOf[UserDeleteUseCase]).to(classOf[UserDeleteInteractor])
    bind(classOf[UserUpdateUseCase]).to(classOf[UserUpdateInteractor])

    // Repository
    bind(classOf[UserRepository]).to(classOf[UserRepositoryImpl])
  }
}
