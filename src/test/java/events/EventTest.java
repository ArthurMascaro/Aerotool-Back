package events;

import edu.br.ifsp.applications.persistence.inmemory.InMemoryEventDAO;
import edu.br.ifsp.domain.entities.event.Event;
import edu.br.ifsp.domain.entities.event.EventSituation;
import edu.br.ifsp.domain.entities.event.EventType;
import edu.br.ifsp.domain.entities.user.User;
import edu.br.ifsp.domain.usecases.events.CreateEventUseCase;
import edu.br.ifsp.domain.usecases.events.FindEventUseCase;
import edu.br.ifsp.domain.usecases.events.RemoveEventUseCase;
import edu.br.ifsp.domain.usecases.events.UpdateEventUseCase;
import edu.br.ifsp.domain.usecases.utils.EntityAlreadyExistsException;
import edu.br.ifsp.domain.usecases.utils.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.notification.RunListener;

import java.awt.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;
import java.util.UUID;


class EventTest {

    public static InMemoryEventDAO inMemoryEventDAO = new InMemoryEventDAO();

    public CreateEventUseCase createEventUseCase = new CreateEventUseCase(inMemoryEventDAO);

    public FindEventUseCase findEventUseCase = new FindEventUseCase(inMemoryEventDAO);

    public RemoveEventUseCase removeEventUseCase = new RemoveEventUseCase(inMemoryEventDAO);

    public UpdateEventUseCase updateEventUseCase = new UpdateEventUseCase(inMemoryEventDAO);
    @Test
    public void EventConstructor_WithUUIDArguments_ObjectEventWithUUID() {
        Assertions.assertEquals(UUID.class, new Event(UUID.randomUUID()).getId().getClass());
    }

    @Test
    public void EventConstructor_WithoutArguments_ObjectEventWithUUID() {
        Assertions.assertEquals(UUID.class, new Event().getId().getClass());
    }

    @Test
    public void insertEvent_AlreadyExists_IllegalStateException(){
        User respTest = new User(UUID.randomUUID());
        User subjTest = new User(UUID.randomUUID());
        Timestamp date = new Timestamp(System.currentTimeMillis());
        Event event = new Event(UUID.randomUUID(), respTest, subjTest, date,
                EventSituation.ACCEPTED, EventType.ACTION_USERS,
                "Standard description");
        createEventUseCase.insert(event);
        Assertions.assertThrows(EntityAlreadyExistsException.class, () -> createEventUseCase.insert(event));
    }

    @Test
    public void insertEvent_WithCorrectEvent_EventClass(){
        User respTest = new User(UUID.randomUUID());
        User subjTest = new User(UUID.randomUUID());
        Timestamp date = new Timestamp(System.currentTimeMillis());
        Event event = new Event(UUID.randomUUID(), respTest, subjTest, date,
                EventSituation.NOT_VISUALIZED, EventType.NOTIFICATION,
                "Standard description 2");
        Assertions.assertEquals(Event.class, createEventUseCase.insert(event).getClass());
    }

    @Test
    public void findOneEvent_withEventEqualsNull_IllegalArgumentException(){
        Assertions.assertThrows(IllegalArgumentException.class, () -> findEventUseCase.findOne(null));
    }

    @Test
    public void findOneEvent_withCorrectEvent_OptionalEvent(){
        User respTest = new User(UUID.randomUUID());
        User subjTest = new User(UUID.randomUUID());
        Timestamp date = new Timestamp(System.currentTimeMillis());
        Event event = new Event(UUID.randomUUID(), respTest, subjTest, date,
                EventSituation.SENT, EventType.SYSTEM_LOG,
                "Standard description 3");
        createEventUseCase.insert(event);
        Assertions.assertEquals(Event.class, findEventUseCase.findOne(event.getId()).get().getClass());
    }

    @Test
    public void findByUUIDEvent_withUUIDEqualsNull_IllegalArgumentException(){
        Assertions.assertThrows(IllegalArgumentException.class, () -> findEventUseCase.findByUUID(null));
    }

    @Test
    public void findByUUIDEvent_withCorrectUUIDEvent_OptionalEvent(){
        User respTest = new User(UUID.randomUUID());
        User subjTest = new User(UUID.randomUUID());
        Timestamp date = new Timestamp(System.currentTimeMillis());
        Event event = new Event(UUID.randomUUID(), respTest, subjTest, date,
                EventSituation.REJECTED, EventType.ACTION_USERS,
                "Standard description 4");
        createEventUseCase.insert(event);
        Assertions.assertEquals(Event.class, findEventUseCase.findByUUID(event.getId()).get().getClass());
    }

    @Test
    public void FindAllEvents(){
        Assertions.assertEquals(ArrayList.class, findEventUseCase.findAll().getClass());
    }

    @Test
    public void deleteEventByUUID_EventNotExists_EntityNotFoundException(){
        Assertions.assertThrows(EntityNotFoundException.class, () -> removeEventUseCase.remove(new Event(UUID.randomUUID())).getId().getClass());
    }

    @Test
    public void deleteEventByUUID_EventExists_EventClass(){
        User respTest = new User(UUID.randomUUID());
        User subjTest = new User(UUID.randomUUID());
        Timestamp date = new Timestamp(System.currentTimeMillis());
        Event event = new Event(UUID.randomUUID(), respTest, subjTest, date,
                EventSituation.VISUALIZED, EventType.ACTION_USERS,
                "Standard description 4");
        createEventUseCase.insert(event);
        Assertions.assertEquals(Event.class, removeEventUseCase.remove(event.getId()).getClass());
    }

    //Failed test.
    @Test
    public void deleteEventByEvent_EventNotExists_EntityNotFoundException(){
        User respTest = new User(UUID.randomUUID());
        User subjTest = new User(UUID.randomUUID());
        Timestamp date = new Timestamp(System.currentTimeMillis());
        Event event = new Event(UUID.randomUUID(), respTest, subjTest, date,
                EventSituation.SENT, EventType.ACTION_USERS,
                "Standard description 5");
        createEventUseCase.insert(event);
        Assertions.assertThrows(EntityNotFoundException.class, () -> removeEventUseCase.remove(event).getClass());
    }

    @Test
    public void deleteEventByEvent_EventExists_EventClass(){
        User respTest = new User(UUID.randomUUID());
        User subjTest = new User(UUID.randomUUID());
        Timestamp date = new Timestamp(System.currentTimeMillis());
        Event event = new Event(UUID.randomUUID(), respTest, subjTest, date,
                EventSituation.VISUALIZED, EventType.ACTION_USERS,
                "Standard description 4");
        createEventUseCase.insert(event);
        Assertions.assertEquals(Event.class, removeEventUseCase.remove(event).getClass());
    }

    //Not sure if it's really NullPointerException
    // that should be used here.
    @Test
    public void updateEvent_OnlyWithUUID_NullPointerException(){
        Event event = new Event(UUID.randomUUID());
        Assertions.assertThrows(new NullPointerException().getClass(), () -> updateEventUseCase.update(event));
    }

    @Test
    public void updateEvent_EventNotExists_EntityNotFoundException(){
        User respTest = new User(UUID.randomUUID());
        User subjTest = new User(UUID.randomUUID());
        Timestamp date = new Timestamp(System.currentTimeMillis());
        Event event = new Event(UUID.randomUUID(), respTest, subjTest, date,
                EventSituation.VISUALIZED, EventType.ACTION_USERS,
                "Standard description 4");
        Assertions.assertThrows(EntityNotFoundException.class, () -> updateEventUseCase.update(event));
    }

    //Failed test.
    @Test
    public void updateEvent_EventExists_NewEvent(){
        User respTest = new User(UUID.randomUUID());
        User subjTest = new User(UUID.randomUUID());
        Timestamp date = new Timestamp(System.currentTimeMillis());
        Event event = new Event(UUID.randomUUID(), respTest, subjTest, date,
                EventSituation.NOT_VISUALIZED, EventType.NOTIFICATION,
                "Standard description 2");

        event.setDescription("No description");
        Assertions.assertEquals(Event.class, updateEventUseCase.update(event));
    }
}