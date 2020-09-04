package gateways.db

import play.api.db.slick.HasDatabaseConfigProvider
import slick.jdbc.JdbcProfile

trait DaoSlick extends DBTableDefinitions with HasDatabaseConfigProvider[JdbcProfile]
