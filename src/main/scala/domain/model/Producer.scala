package domain.model

import main.scala.domain.model.NewProductOffered

class Producer {

  def offerNewProduct(product :Product) = {
    JDomainEventPublisher.instance().publish(new NewProductOffered(product))
  }

}
