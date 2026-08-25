package hotelreservation;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Reservation {
    private final int bookingId;
    private final String guestName;
    private final int roomNumber;
    private final LocalDate checkIn;
    private final LocalDate checkOut;

    public Reservation(int bookingId, String guestName, int roomNumber,
                       LocalDate checkIn, LocalDate checkOut) {
        this.bookingId = bookingId;
        this.guestName = guestName;
        this.roomNumber = roomNumber;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
    }

    public int getBookingId() { return bookingId; }
    public String getGuestName() { return guestName; }
    public int getRoomNumber() { return roomNumber; }
    public LocalDate getCheckIn() { return checkIn; }
    public LocalDate getCheckOut() { return checkOut; }

    public long getNights() {
        return ChronoUnit.DAYS.between(checkIn, checkOut);
    }

    @Override
    public String toString() {
        return bookingId + "|" + guestName + "|" + roomNumber + "|"
                + checkIn + "|" + checkOut;
    }
}
