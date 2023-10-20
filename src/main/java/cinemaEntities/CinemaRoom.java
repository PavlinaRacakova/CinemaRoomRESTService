package cinemaEntities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.*;

@Getter
@Setter

public class CinemaRoom {

    @JsonProperty("total_rows")
    private int totalRows;
    @JsonProperty("total_columns")
    private int totalColumns;
    @JsonProperty("available_seats")
    private List<Seat> availableSeats = new ArrayList<>();

    @JsonIgnore
    private Map<String, Seat> purchasedSeats = new HashMap<>();
    @JsonIgnore
    int higherPrice;
    @JsonIgnore
    int lowerPrice;

    public CinemaRoom(int totalRows, int totalColumns, int higherPrice, int lowerPrice) {
        this.totalRows = totalRows;
        this.totalColumns = totalColumns;
        this.higherPrice = higherPrice;
        this.lowerPrice = lowerPrice;
        for (int i = 0; i < totalRows; i++) {
            for (int j = 0; j < totalColumns; j++) {
                availableSeats.add(new Seat(i + 1, j + 1, (i <= 3) ? higherPrice : lowerPrice));
            }
        }
    }

    public boolean isFreeSeat(Seat seat) {
        return availableSeats.contains(seat);
    }

    public PurchasedSeatDTO purchaseSeat(Seat seat) {
        UUID token = UUID.randomUUID();
        availableSeats.remove(seat);
        seat.setPrice((seat.getRow() <= 3) ? higherPrice : lowerPrice);
        purchasedSeats.put(token.toString(), seat);
        return new PurchasedSeatDTO(token, seat);
    }

    public boolean seatWithThisTokenHasBeenPurchased (Token token) {
        return purchasedSeats.containsKey(token.token());
    }

    public ReturnedSeatDTO returnSeat(Token token) {
        Seat seatToReturn = purchasedSeats.get(token.token());
        purchasedSeats.remove(token.token());
        availableSeats.add(seatToReturn);
        return new ReturnedSeatDTO(seatToReturn);
    }

    public int numberOfAvailableSeats() {
        return availableSeats.size();
    }

    public int numberOfPurchasedTickets() {
        return purchasedSeats.size();
    }

    public int currentIncome() {
       return purchasedSeats.values()
                .stream()
                .mapToInt(Seat::getPrice)
                .sum();
    }

}
