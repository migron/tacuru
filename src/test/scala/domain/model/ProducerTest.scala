package test.scala.domain.model

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.FlatSpec
import org.scalatest.matchers.ShouldMatchers
import domain.model.{Producer, NewProductOfferedHandler, Product}
import domain.DomainEventPublisher

@RunWith(classOf[JUnitRunner])
class ProducerTest extends FlatSpec with ShouldMatchers {


  "Producer" should "publish a new product offered domain event" in {
    val handler = new NewProductOfferedHandler()
    DomainEventPublisher.subscribe(handler)

    val producer = new Producer()
    val product = new Product()

    producer.offerNewProduct(product)
  }

}
