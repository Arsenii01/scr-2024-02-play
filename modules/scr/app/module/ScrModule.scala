package module

import di.AppModule
import models.dao.repositories.{PhoneRecordRepository, PhoneRecordRepositoryImpl, ProductItemRepository, ProductItemRepositoryImpl, ProductRepository, ProductRepositoryImpl}
import models.services.{LogService, LogServiceImpl}

class ScrModule extends AppModule{
  override def configure(): Unit = {
    bindSingleton[LogService, LogServiceImpl]
    bindSingleton[PhoneRecordRepository, PhoneRecordRepositoryImpl]
    bindSingleton[ProductRepository, ProductRepositoryImpl]
    bindSingleton[ProductItemRepository, ProductItemRepositoryImpl]
  }
}
