package edu.br.ifsp.web.model.event.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import edu.br.ifsp.domain.entities.event.Event;
import edu.br.ifsp.domain.entities.event.EventSituation;

import java.util.UUID;

public record UpdateSituationRequest(String situation) {

    @JsonCreator
    public UpdateSituationRequest(@JsonProperty("situation") String situation) {
        this.situation = situation;
    }

    public Event toEvent(UUID id){
        return Event.createWithIdAndSituation(id, EventSituation.valueOf(situation));
    }

}
