package main.scala.domain

import domain.DomainEvent

abstract class DomainEventHandler[T <: DomainEvent] {

  def handleEvent(domainEvent :T)

}
