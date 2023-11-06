package CinemaRoom.controller;

import CinemaRoom.cinemaEntities.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import CinemaRoom.service.CinemaService;


@RestController
public class CinemaRoomController {

    CinemaService service = new CinemaService();

    @GetMapping("/seats")
    public CinemaRoom getSeats() {
        return service.getSeats();
    }

    @PostMapping("/purchase")
    public ResponseEntity<?> purchaseTickets(@RequestBody Seat seat) {
        return service.purchaseTickets(seat);
    }

    @PostMapping("/return")
    public ResponseEntity<?> returnTickets(@RequestBody Token token) {
        return service.returnTickets(token);
    }

    @GetMapping("/stats")
    public ResponseEntity<?> returnStats(@RequestParam String password) {
        return service.returnStats(password);
    }
}
