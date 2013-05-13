package main.scala.domain.model

import domain.DomainEvent
import java.util.Date

class NewProductOffered(val product : domain.model.Product) extends DomainEvent {

  def eventVersion(): Int = 1

  def occurredOn(): Date = new Date()
}
