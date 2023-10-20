package cinemaEntities;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ReturnedSeatDTO(@JsonProperty("returned_ticket") Seat returnedSeat) {
}
