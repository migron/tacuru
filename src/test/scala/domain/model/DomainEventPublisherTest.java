//   Copyright 2012,2013 Vaughn Vernon
//
//   Licensed under the Apache License, Version 2.0 (the "License");
//   you may not use this file except in compliance with the License.
//   You may obtain a copy of the License at
//
//       http://www.apache.org/licenses/LICENSE-2.0
//
//   Unless required by applicable law or agreed to in writing, software
//   distributed under the License is distributed on an "AS IS" BASIS,
//   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
//   See the License for the specific language governing permissions and
//   limitations under the License.

package domain.model;

import junit.framework.TestCase;

public class DomainEventPublisherTest extends TestCase {

    private boolean anotherEventHandled;
    private boolean eventHandled;

    public DomainEventPublisherTest() {
        super();
    }

    public void testDomainEventPublisherPublish() throws Exception {
        JDomainEventPublisher.instance().reset();

        JDomainEventPublisher.instance().subscribe(new JDomainEventSubscriber<TestableDomainEvent>(){
            @Override
            public void handleEvent(TestableDomainEvent aDomainEvent) {
                assertEquals(100L, aDomainEvent.id());
                assertEquals("test", aDomainEvent.name());
                eventHandled = true;
            }
            @Override
            public Class<TestableDomainEvent> subscribedToEventType() {
                return TestableDomainEvent.class;
            }
        });

        assertFalse(this.eventHandled);

        JDomainEventPublisher.instance().publish(new TestableDomainEvent(100L, "test"));

        assertTrue(this.eventHandled);
    }

    public void testJDomainEventPublisherBlocked() throws Exception {
        JDomainEventPublisher.instance().reset();

        JDomainEventPublisher.instance().subscribe(new JDomainEventSubscriber<TestableDomainEvent>(){
            @Override
            public void handleEvent(TestableDomainEvent aDomainEvent) {
                assertEquals(100L, aDomainEvent.id());
                assertEquals("test", aDomainEvent.name());
                eventHandled = true;
                // attempt nested publish, which should not work
                JDomainEventPublisher.instance().publish(new AnotherTestableDomainEvent(1000.0));
            }
            @Override
            public Class<TestableDomainEvent> subscribedToEventType() {
                return TestableDomainEvent.class;
            }
        });

        JDomainEventPublisher.instance().subscribe(new JDomainEventSubscriber<AnotherTestableDomainEvent>(){
            @Override
            public void handleEvent(AnotherTestableDomainEvent aDomainEvent) {
                // should never be reached due to blocked publisher
                assertEquals(1000.0, aDomainEvent.value());
                anotherEventHandled = true;
            }
            @Override
            public Class<AnotherTestableDomainEvent> subscribedToEventType() {
                return AnotherTestableDomainEvent.class;
            }
        });

        assertFalse(this.eventHandled);
        assertFalse(this.anotherEventHandled);

        JDomainEventPublisher.instance().publish(new TestableDomainEvent(100L, "test"));

        assertTrue(this.eventHandled);
        assertFalse(this.anotherEventHandled);
    }
}
