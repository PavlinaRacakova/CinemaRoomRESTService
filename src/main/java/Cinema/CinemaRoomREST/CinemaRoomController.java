package Cinema.CinemaRoomREST;

import model.CinemaRoom;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class CinemaRoomController {

    private CinemaRoom cinemaRoom = new CinemaRoom(9, 9);

    @GetMapping("/seats")
    public CinemaRoom getSeats() {
        return cinemaRoom;
    }

}
