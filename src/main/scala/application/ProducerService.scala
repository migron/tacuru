package scala.application

import domain.model.{ProductFactory, AllProducts, ProducerId, AllProducers}

object ProducerService {
  private val allProducers = new AllProducers()
  private val allProducts = new AllProducts()

  def publishNewProduct(producerId: String, name: String, description: String, price: String) = {
    val producer = allProducers.findProducer(new ProducerId(producerId))
    require(producer != null)
    val product = ProductFactory.create(producer, name, description, price)
    allProducts.add(product)
    producer.offerNewProduct(product)
  }

}
