package hotelreservation;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.*;

public class HotelReservationSystem {
    private static final Scanner scanner = new Scanner(System.in);
    private static final Map<Integer, Room> rooms = new LinkedHashMap<>();
    private static final List<Reservation> reservations = new ArrayList<>();
    private static final String FILE_NAME = "reservations.txt";
    private static int nextBookingId = 1001;

    public static void main(String[] args) {
        initializeRooms();
        loadReservations();

        boolean running = true;
        while (running) {
            System.out.println("\n=== HOTEL RESERVATION SYSTEM ===");
            System.out.println("1. View rooms");
            System.out.println("2. Book room");
            System.out.println("3. Cancel reservation");
            System.out.println("4. View booking details");
            System.out.println("5. Save and exit");
            System.out.print("Choose: ");

            switch (scanner.nextLine().trim()) {
                case "1" -> showRooms();
                case "2" -> bookRoom();
                case "3" -> cancelReservation();
                case "4" -> viewBooking();
                case "5" -> {
                    saveReservations();
                    running = false;
                }
                default -> System.out.println("Invalid option.");
            }
        }
        System.out.println("System closed.");
    }

    private static void initializeRooms() {
        rooms.put(101, new Room(101, "Standard", 1800));
        rooms.put(102, new Room(102, "Standard", 1800));
        rooms.put(201, new Room(201, "Deluxe", 2800));
        rooms.put(202, new Room(202, "Deluxe", 2800));
        rooms.put(301, new Room(301, "Suite", 4500));
    }

    private static void showRooms() {
        System.out.println("\n--- ROOMS ---");
        for (Room room : rooms.values()) {
            System.out.printf("Room %d | %-8s | ₹%.2f/night | %s%n",
                    room.getRoomNumber(), room.getCategory(),
                    room.getPricePerNight(),
                    room.isAvailable() ? "Available" : "Booked");
        }
    }

    private static void bookRoom() {
        showRooms();

        try {
            System.out.print("Room number: ");
            int roomNumber = Integer.parseInt(scanner.nextLine());
            Room room = rooms.get(roomNumber);

            if (room == null || !room.isAvailable()) {
                System.out.println("Room is unavailable.");
                return;
            }

            System.out.print("Guest name: ");
            String guestName = scanner.nextLine().trim();

            LocalDate checkIn = readDate("Check-in date (YYYY-MM-DD): ");
            LocalDate checkOut = readDate("Check-out date (YYYY-MM-DD): ");

            if (!checkOut.isAfter(checkIn)) {
                System.out.println("Check-out must be after check-in.");
                return;
            }

            Reservation reservation = new Reservation(
                    nextBookingId++, guestName, roomNumber, checkIn, checkOut
            );

            reservations.add(reservation);
            room.setAvailable(false);

            double total = reservation.getNights() * room.getPricePerNight();
            System.out.printf("Booking confirmed. ID: %d | Total: ₹%.2f%n",
                    reservation.getBookingId(), total);
            saveReservations();
        } catch (NumberFormatException e) {
            System.out.println("Room number must be numeric.");
        }
    }

    private static void cancelReservation() {
        System.out.print("Booking ID: ");
        try {
            int id = Integer.parseInt(scanner.nextLine());

            Iterator<Reservation> iterator = reservations.iterator();
            while (iterator.hasNext()) {
                Reservation reservation = iterator.next();
                if (reservation.getBookingId() == id) {
                    Room room = rooms.get(reservation.getRoomNumber());
                    if (room != null) room.setAvailable(true);
                    iterator.remove();
                    saveReservations();
                    System.out.println("Reservation cancelled.");
                    return;
                }
            }
            System.out.println("Booking not found.");
        } catch (NumberFormatException e) {
            System.out.println("Invalid booking ID.");
        }
    }

    private static void viewBooking() {
        System.out.print("Booking ID: ");
        try {
            int id = Integer.parseInt(scanner.nextLine());

            for (Reservation r : reservations) {
                if (r.getBookingId() == id) {
                    Room room = rooms.get(r.getRoomNumber());
                    double total = r.getNights() * room.getPricePerNight();

                    System.out.println("\n--- BOOKING DETAILS ---");
                    System.out.println("Booking ID: " + r.getBookingId());
                    System.out.println("Guest: " + r.getGuestName());
                    System.out.println("Room: " + r.getRoomNumber());
                    System.out.println("Category: " + room.getCategory());
                    System.out.println("Check-in: " + r.getCheckIn());
                    System.out.println("Check-out: " + r.getCheckOut());
                    System.out.println("Nights: " + r.getNights());
                    System.out.printf("Total: ₹%.2f%n", total);
                    return;
                }
            }
            System.out.println("Booking not found.");
        } catch (NumberFormatException e) {
            System.out.println("Invalid booking ID.");
        }
    }

    private static LocalDate readDate(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                return LocalDate.parse(scanner.nextLine().trim());
            } catch (DateTimeParseException e) {
                System.out.println("Use YYYY-MM-DD format.");
            }
        }
    }

    private static void saveReservations() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Reservation r : reservations) {
                writer.write(r.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Could not save reservations: " + e.getMessage());
        }
    }

    private static void loadReservations() {
        File file = new File(FILE_NAME);
        if (!file.exists()) return;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length != 5) continue;

                int id = Integer.parseInt(parts[0]);
                String guest = parts[1];
                int roomNumber = Integer.parseInt(parts[2]);
                LocalDate checkIn = LocalDate.parse(parts[3]);
                LocalDate checkOut = LocalDate.parse(parts[4]);

                reservations.add(new Reservation(id, guest, roomNumber, checkIn, checkOut));
                Room room = rooms.get(roomNumber);
                if (room != null) room.setAvailable(false);
                nextBookingId = Math.max(nextBookingId, id + 1);
            }
        } catch (IOException | NumberFormatException e) {
            System.out.println("Could not load saved reservations.");
        }
    }
}
