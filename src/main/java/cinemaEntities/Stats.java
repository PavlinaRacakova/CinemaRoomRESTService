package cinemaEntities;

import com.fasterxml.jackson.annotation.JsonProperty;

public record Stats(@JsonProperty("current_income") int currentIncome,
                    @JsonProperty("number_of_available_seats") int availableSeats,
                    @JsonProperty("number_of_purchased_tickets") int purchasedTickets) {
}
