package cinemaEntities;

import java.util.UUID;

public record PurchasedSeatDTO(UUID token, Seat ticket) {
}
