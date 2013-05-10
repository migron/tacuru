package domain.model

import main.scala.domain.DomainEventHandler
import main.scala.domain.model.NewProductOffered

class NewProductOfferedHandler extends DomainEventHandler[NewProductOffered] {

  def handleEvent(domainEvent: NewProductOffered) {}
}
