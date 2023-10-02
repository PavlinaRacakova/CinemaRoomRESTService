package service;

import exceptionHandling.ErrorMessage;
import model.*;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CinemaService {

    private final CinemaRoom cinemaRoom = new CinemaRoom(9, 9, 10, 8);
    private static final String PASSWORD = "super_secret";

    public CinemaRoom getSeats() {
        return cinemaRoom;
    }

    public ResponseEntity<?> purchaseTickets(Seat seat) {
        if (seat.getRow() > 9 || seat.getRow() <= 0 || seat.getColumn() > 9 || seat.getColumn() <= 0) {
            return ResponseEntity.badRequest().body(new ErrorMessage("The number of a row or a column is out of bounds!"));
        }

        if (!cinemaRoom.isFreeSeat(seat)) {
            return ResponseEntity.badRequest().body(new ErrorMessage("The ticket has been already purchased!"));
        }

        PurchasedSeatDTO purchasedSeatDTO = cinemaRoom.purchaseSeat(seat);
        return ResponseEntity.ok().body(purchasedSeatDTO);
    }

    public ResponseEntity<?> returnTickets(Token token) {
        if (!cinemaRoom.seatWithThisTokenHasBeenPurchased(token)) {
            return ResponseEntity.badRequest().body(new ErrorMessage("Wrong token!"));
        }

        ReturnedSeatDTO returnedSeatDTO = cinemaRoom.returnSeat(token);
        return ResponseEntity.ok().body(returnedSeatDTO);
    }

    public ResponseEntity<?> returnStats(String password) {
        if (!password.equals(PASSWORD)) {
            return ResponseEntity.badRequest().body(new ErrorMessage("The password is wrong!"));
        }

        Stats stats = new Stats(cinemaRoom.currentIncome(), cinemaRoom.numberOfAvailableSeats(), cinemaRoom.numberOfPurchasedTickets());
        return ResponseEntity.ok().body(stats);
    }
}
