# CodeAlpha Hotel Reservation System

A console-based Java hotel booking application that demonstrates object-oriented programming, collections, date handling, validation, reservation management, and file I/O.

## Assignment Task

**Task 4: Hotel Reservation System**

The application is designed to:
- Search and view hotel rooms
- Categorize rooms as Standard, Deluxe, or Suite
- Make and cancel reservations
- Display booking details
- Calculate the total booking cost
- Store reservation data using file I/O

## Features

### Room Management
The system contains sample rooms with different categories and prices. Each room maintains its room number, category, price per night, and availability status.

### Room Availability
The user can view all rooms and see whether each one is currently available or booked.

### Booking
To create a reservation, the user provides:
- Room number
- Guest name
- Check-in date
- Check-out date

The system validates the dates and prevents a booking when the selected room is unavailable.

### Cost Calculation
The booking cost is calculated using:

```text
Total Cost = Number of Nights × Room Price Per Night
```

The number of nights is calculated using Java's `ChronoUnit.DAYS`.

### Cancellation
A reservation can be cancelled using its booking ID. When a reservation is cancelled, the associated room becomes available again.

### Booking Details
Users can enter a booking ID to view the guest, room, category, dates, number of nights, and total cost.

### File I/O
Reservations are saved to `reservations.txt`. When the application starts, existing reservations are loaded from the file, allowing booking information to survive between program sessions.

## Project Structure

```text
CodeAlpha_HotelReservationSystem/
├── README.md
└── src/
    └── hotelreservation/
        ├── Room.java
        ├── Reservation.java
        └── HotelReservationSystem.java
```

### Room.java
Represents a hotel room and stores its room number, category, nightly price, and availability.

### Reservation.java
Represents a booking and stores the booking ID, guest name, room number, check-in date, and check-out date. It also calculates the number of nights.

### HotelReservationSystem.java
Contains the main application menu, room initialization, booking and cancellation logic, booking lookup, date validation, and file persistence.

## Concepts Demonstrated

- Object-oriented programming
- Classes and objects
- Encapsulation
- Constructors and methods
- `ArrayList` for reservations
- `LinkedHashMap` for room management
- Iterators for safe removal
- Java `LocalDate`
- `ChronoUnit` for date calculations
- Exception handling
- File reading with `BufferedReader`
- File writing with `BufferedWriter`
- Basic data persistence

## Reservation Flow

```text
View Rooms
    ↓
Select Available Room
    ↓
Enter Guest Details
    ↓
Enter Check-in / Check-out Dates
    ↓
Validate Dates
    ↓
Create Reservation
    ↓
Mark Room as Booked
    ↓
Calculate Total Cost
    ↓
Save Reservation to File
```

## Data Persistence

Reservations are stored in a simple text file using the following structure:

```text
bookingId|guestName|roomNumber|checkIn|checkOut
```

For example:

```text
1001|Rahul|201|2026-09-01|2026-09-04
```

The application reads this information when it starts and reconstructs the reservation objects.

## How to Run

### Requirements
- Java 17 or later
- Command Prompt, PowerShell, Terminal, IntelliJ IDEA, Eclipse, or VS Code

### Compile

```bash
javac -d out src/hotelreservation/*.java
```

### Run

```bash
java -cp out hotelreservation.HotelReservationSystem
```

A `reservations.txt` file will be created automatically when a reservation is saved.

## Example Workflow

```text
=== HOTEL RESERVATION SYSTEM ===
1. View rooms
2. Book room
3. Cancel reservation
4. View booking details
5. Save and exit

Choose: 2
Room number: 201
Guest name: Rahul
Check-in date (YYYY-MM-DD): 2026-09-01
Check-out date (YYYY-MM-DD): 2026-09-04

Booking confirmed. ID: 1001 | Total: ₹8400.00
```

## Validation and Error Handling

The application handles common invalid inputs such as:
- Invalid room numbers
- Unavailable rooms
- Invalid booking IDs
- Non-numeric input where numbers are required
- Incorrect date formats
- Check-out dates that are not after check-in

## Limitations

This is an educational hotel reservation simulation. It does not connect to a real hotel inventory system, payment gateway, or external booking service. The application uses a simple text file instead of a production database.

## Learning Outcome

This project demonstrates how OOP, collections, date APIs, validation, and file I/O can be combined to build a practical reservation-management application.
