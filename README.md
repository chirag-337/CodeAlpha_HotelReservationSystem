# CodeAlpha Hotel Reservation System

A console-based Java hotel booking application using OOP and file I/O.

## Features
- Room categories: Standard, Deluxe and Suite
- Search/view room availability
- Book rooms
- Cancel reservations
- View booking details
- Calculate total booking cost
- Persist reservations in `reservations.txt`

## Run
```bash
javac -d out src/hotelreservation/*.java
java -cp out hotelreservation.HotelReservationSystem
```

Java 17+ recommended.

The generated `reservations.txt` is runtime data and is intentionally not included.
