import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

class Room {
    private int roomId;
    private String category;
    private double price;
    private boolean isAvailable;

    public Room(int roomId, String category, double price, boolean isAvailable) {
        this.roomId = roomId;
        this.category = category;
        this.price = price;
        this.isAvailable = isAvailable;
    }

    // Getters and Setters
    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }
}

class Reservation {
    private int reservationId;
    private int roomId;
    private String userId;
    private Date startDate;
    private Date endDate;
    private double totalAmount;

    public Reservation(int reservationId, int roomId, String userId, Date startDate, Date endDate, double totalAmount) {
        this.reservationId = reservationId;
        this.roomId = roomId;
        this.userId = userId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.totalAmount = totalAmount;
    }

    // Getters and Setters
    public int getReservationId() {
        return reservationId;
    }

    public void setReservationId(int reservationId) {
        this.reservationId = reservationId;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }
}

class Payment {
    private int paymentId;
    private int reservationId;
    private double amount;
    private String paymentStatus;

    public Payment(int paymentId, int reservationId, double amount, String paymentStatus) {
        this.paymentId = paymentId;
        this.reservationId = reservationId;
        this.amount = amount;
        this.paymentStatus = paymentStatus;
    }

    // Getters and Setters
    public int getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(int paymentId) {
        this.paymentId = paymentId;
    }

    public int getReservationId() {
        return reservationId;
    }

    public void setReservationId(int reservationId) {
        this.reservationId = reservationId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }
}

class RoomService {
    private List<Room> rooms;

    public RoomService() {
        this.rooms = new ArrayList<>();
        // Add some rooms for testing
        rooms.add(new Room(1, "Deluxe", 200.0, true));
        rooms.add(new Room(2, "Standard", 100.0, true));
        rooms.add(new Room(3, "Suite", 300.0, false));
    }

    public List<Room> searchAvailableRooms(String category, Date startDate, Date endDate) {
        List<Room> availableRooms = new ArrayList<>();
        for (Room room : rooms) {
            if (room.getCategory().equalsIgnoreCase(category) && room.isAvailable()) {
                availableRooms.add(room);
            }
        }
        return availableRooms;
    }

    public Room getRoomDetails(int roomId) {
        for (Room room : rooms) {
            if (room.getRoomId() == roomId) {
                return room;
            }
        }
        return null; // or throw an exception if not found
    }
}

class ReservationService {
    private List<Reservation> reservations;
    private int reservationCounter = 1;

    public ReservationService() {
        this.reservations = new ArrayList<>();
    }

    public Reservation makeReservation(int roomId, String userId, Date startDate, Date endDate, double totalAmount) {
        Reservation newReservation = new Reservation(reservationCounter++, roomId, userId, startDate, endDate, totalAmount);
        reservations.add(newReservation);
        return newReservation;
    }

    public Reservation getReservationDetails(int reservationId) {
        for (Reservation reservation : reservations) {
            if (reservation.getReservationId() == reservationId) {
                return reservation;
            }
        }
        return null; // or throw an exception if not found
    }
}

class PaymentService {
    private List<Payment> payments;
    private int paymentCounter = 1;

    public PaymentService() {
        this.payments = new ArrayList<>();
    }

    public Payment processPayment(int reservationId, double amount) {
        Payment newPayment = new Payment(paymentCounter++, reservationId, amount, "Success");
        payments.add(newPayment);
        return newPayment;
    }

    public Payment getPaymentDetails(int paymentId) {
        for (Payment payment : payments) {
            if (payment.getPaymentId() == paymentId) {
                return payment;
            }
        }
        return null; // or throw an exception if not found
    }
}

public class Main {
    public static void main(String[] args) {
        RoomService roomService = new RoomService();
        ReservationService reservationService = new ReservationService();
        PaymentService paymentService = new PaymentService();

        Scanner scanner = new Scanner(System.in);
         System.out.println();

        System.out.println("Welcome to the Hotel Reservation System!");

        while (true) {
            System.out.println("1. Search for rooms");
            System.out.println("2. Make a reservation");
            System.out.println("3. View reservation details");
            System.out.println("4. Process payment");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter room category (Deluxe/Standard/Suite): ");
                    String category = scanner.next();
                    // For simplicity, we're assuming the date range is valid and in correct format
                    List<Room> availableRooms = roomService.searchAvailableRooms(category, new Date(), new Date());
                    for (Room room : availableRooms) {
                        System.out.println("Room ID: " + room.getRoomId() + ", Category: " + room.getCategory() + ", Price: " + room.getPrice());
                    }
                    break;

                case 2:
                    System.out.print("Enter Room ID to book: ");
                    int roomId = scanner.nextInt();
                    System.out.print("Enter User ID: ");
                    String userId = scanner.next();
                    // Assuming startDate and endDate are valid dates, for simplicity
                    Reservation reservation = reservationService.makeReservation(roomId, userId, new Date(), new Date(), 200.0);
                    System.out.println("Reservation successful! Reservation ID: " + reservation.getReservationId());
                    break;

                case 3:
                    System.out.print("Enter Reservation ID: ");
                    int reservationId = scanner.nextInt();
                    Reservation resDetails = reservationService.getReservationDetails(reservationId);
                    if (resDetails != null) {
                        System.out.println("Reservation ID: " + resDetails.getReservationId() + ", Room ID: " + resDetails.getRoomId() + ", User ID: " + resDetails.getUserId());
                    } else {
                        System.out.println("Reservation not found.");
                    }
                    break;

                case 4:
                    System.out.print("Enter Reservation ID for payment: ");
                    int resId = scanner.nextInt();
                    System.out.print("Enter payment amount: ");
                    double amount = scanner.nextDouble();
                    Payment payment = paymentService.processPayment(resId, amount);
                    System.out.println("Payment successful! Payment ID: " + payment.getPaymentId());
                    break;

                case 5:
                    System.out.println("Exiting the system. Thank you!");
                    scanner.close();
                    System.exit(0);

                default:
                    System.out.println("Invalid option, please try again.");
            }
              System.out.println();
            System.out.println("------------------------------------------------");
        }
    }
}

