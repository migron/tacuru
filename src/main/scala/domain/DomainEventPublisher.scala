package domain

import main.scala.domain.DomainEventHandler
import scala.collection.mutable.ListBuffer

object DomainEventPublisher {

  private val subscribers = new ListBuffer[DomainEventHandler[_]]

  def subscribe(domainEventHandler: DomainEventHandler[_]) = {
    subscribers += domainEventHandler
  }


}

//    private val publishing =
//      new ThreadLocal[Boolean] {
//        protected Boolean initialValue() {
//          return Boolean.FALSE;
//        }
//      };


//    public <T> void publish(final T aDomainEvent) {
//      if (publishing.get()) {
//        return;
//      }
//      try {
//        publishing.set(Boolean.TRUE);
//        List<DomainEventSubscriber<T>> registeredSubscribers =
//          subscribers.get();
//        if (registeredSubscribers != null) {
//          Class<?> eventType = aDomainEvent.getClass();
//          for (DomainEventSubscriber<T> subscriber :
//          registeredSubscribers) {
//            Class<?> subscribedTo =
//              subscriber.subscribedToEventType();
//            if (subscribedTo == eventType ||
//              subscribedTo == DomainEvent.class) {
//              subscriber.handleEvent(aDomainEvent);
//            }
//          }
//        }
//      } finally {
//        publishing.set(Boolean.FALSE);
//      }
//      }
//
//      public DomainEventPublisher reset() {
//      if (!publishing.get()) {
//        subscribers.set(null);
//      }
//      return this;
//      }
//
//      @SuppressWarnings("unchecked")
//      public <T> void subscribe(DomainEventSubscriber<T> aSubscriber) {
//        if (publishing.get()) {
//          return;
//        }
//        List<DomainEventSubscriber<T>> registeredSubscribers =
//          subscribers.get();
//        if (registeredSubscribers == null) {
//          registeredSubscribers =
//            new ArrayList<DomainEventSubscriber<T>>();
//          subscribers.set(registeredSubscribers);
//        }
//        registeredSubscribers.add(aSubscriber);
//        }
//        }}
