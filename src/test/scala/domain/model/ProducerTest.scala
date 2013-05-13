package test.scala.domain.model

import main.scala.domain.model.NewProductOffered
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.FlatSpec
import org.scalatest.matchers.ShouldMatchers
import domain.model.{JDomainEventPublisher, JDomainEventSubscriber, Producer, Product}

@RunWith(classOf[JUnitRunner])
class ProducerTest extends FlatSpec with ShouldMatchers {

  "Producer" should "publish a new product offered domain event" in {
    val handler = new JDomainEventSubscriber[NewProductOffered] {
      var newProductOffered :NewProductOffered = null;
      def handleEvent(aNewProductOffered: NewProductOffered) = {
        newProductOffered = aNewProductOffered
      }
      def subscribedToEventType(): Class[NewProductOffered] = classOf[NewProductOffered]
    }

    JDomainEventPublisher.instance().subscribe(handler)

    val producer = new Producer()
    val product = new Product()

    producer.offerNewProduct(product)

    handler.newProductOffered.product should equal(product)
  }
}
