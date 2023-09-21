package Cinema.CinemaRoomREST;

import model.CinemaRoom;
import model.IncorrectSeatValue;
import model.Seat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
public class CinemaRoomController {

    private final CinemaRoom cinemaRoom = new CinemaRoom(9, 9, 10, 8);

    @GetMapping("/seats")
    public CinemaRoom getSeats() {
        return cinemaRoom;
    }

    @PostMapping("/purchase")
    public ResponseEntity<?> purchaseTickets(@RequestBody Seat seat) {

        if (seat.getRow() > 9 || seat.getRow() <= 0 || seat.getColumn() > 9 || seat.getColumn() <= 0) {
            return ResponseEntity.badRequest().body(new IncorrectSeatValue("The number of a row or a column is out of bounds!"));
        }

        if (!cinemaRoom.isFreeSeat(seat)) {
            return ResponseEntity.badRequest().body(new IncorrectSeatValue("The ticket has been already purchased!"));
        }

        cinemaRoom.purchaseSeat(seat);
        return ResponseEntity.ok()
                .body(cinemaRoom.returnSeatWithPrice(seat));
    }
}
