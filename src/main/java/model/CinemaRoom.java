package model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class CinemaRoom {

    @JsonProperty("total_rows")
    private int totalRows;

    @JsonProperty("total_columns")
    private int totalColumns;

    @JsonProperty("available_seats")
    private List<Seat> availableSeats = new ArrayList<>();

    public CinemaRoom(int totalRows, int totalColumns) {
        this.totalRows = totalRows;
        this.totalColumns = totalColumns;
        for (int i = 0; i < totalRows; i++) {
            for (int j = 0; j < totalColumns; j++) {
                availableSeats.add(new Seat(i + 1, j + 1));
            }
        }
    }

    public int getTotalRows() {
        return totalRows;
    }

    public void setTotalRows(int totalRows) {
        this.totalRows = totalRows;
    }

    public int getTotalColumns() {
        return totalColumns;
    }

    public void setTotalColumns(int total_columns) {
        this.totalColumns = total_columns;
    }

    public void setAvailableSeats(List<Seat> availableSeats) {
        this.availableSeats = availableSeats;
    }

    public List<Seat> getAvailableSeats() {
        return availableSeats;
    }

}
