package model;

import java.util.UUID;

public record PurchasedSeatDTO(UUID token, Seat ticket) {
}
