package passenger_management;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import user_management.User;
import user_management.UserOptions;

public class FlightBookingSystem {
	static ArrayList<Passenger> al = new ArrayList<>();
	static List<Integer> availableSeats = new ArrayList<>();
	static List<String> availableFlights = new ArrayList<>();

	public static void flightBooking() throws IOException {
		Scanner scanner = new Scanner(System.in);
		for (int seatNumber = 1; seatNumber <= 20; seatNumber++) {
			availableSeats.add(seatNumber);
		}
		availableFlights.add("Air India 4582");
		availableFlights.add("Jet Airway  1254");
		availableFlights.add("SpiceJet 123");
		availableFlights.add("IndiGo 1234");
		availableFlights.add("Express 3524");
		boolean canIKeepRunningTheProgram = true;
		while (canIKeepRunningTheProgram == true) {
			System.out.println("****Welcome To Flight Booking System****");

			System.out.println("What Do you want to do?");
			System.out.println("1.Book A Ticket");
			System.out.println("2.Cancel A Ticket");
			System.out.println("3.View Your Ticket");
			System.out.println("4.See vacant Seats");
			System.out.println("5.Quit");

			int userChoice = scanner.nextInt();

			if (userChoice == UserOptions.QUIT) {
				File file = new File(
						"C:\\Users\\yash\\eclipse-workspace\\FlightBookingSystem\\src\\passenger_management\\Tickets.csv");
				FileWriter fw = new FileWriter(file);
				BufferedWriter bw = new BufferedWriter(fw);

				for (Passenger p : al) {
					bw.write(p.passengerName + "," + p.age + "," +p.source+","+p.destination+","+ p.flightName + "," + p.seatNumber + ","
							+ p.contactNumber);
				}
				bw.close();
				fw.close();
				
				file = null;

				canIKeepRunningTheProgram = false;
				System.out.println("Program Closed!!!!!");
			} else if (userChoice == BookingOptions.Book_Ticket) {
				bookTicket();
			} else if (userChoice == BookingOptions.Cancel_Ticket) {
				System.out.println("Enter Passenger Name to Cancel Ticket: ");
				scanner.nextLine();
				String passengerName = scanner.nextLine();
				cancelTicket(passengerName);
			} else if (userChoice == BookingOptions.View_Ticket) {
				System.out.println("Enter Passenger Name to View Ticket: ");
				scanner.nextLine();
				String passengerName = scanner.nextLine();
				viewTicket(passengerName);
			} else if (userChoice == BookingOptions.See_Vacant_Seats) {
				seeVacantSeats();
			}
		}
	}

	public static void bookTicket() {
		Scanner scanner = new Scanner(System.in);

		Passenger passenger = new Passenger();

		System.out.println("Enter Passenger Name : ");
		passenger.passengerName = scanner.nextLine();

		System.out.println("Enter Passenger Age : ");
		passenger.age = scanner.nextLine();

		System.out.println("Enter Source: ");
		passenger.source = scanner.nextLine();

		System.out.println("Enter Destination: ");
		passenger.destination = scanner.nextLine();

		for (int i = 0; i < availableFlights.size(); i++) {
			System.out.println((i + 1) + ". " + availableFlights.get(i));
		}
		System.out.println("Select a Flight (Enter the number): ");
		int flightChoice = scanner.nextInt();

		if (flightChoice >= 1 && flightChoice <= availableFlights.size()) {
			passenger.flightName = availableFlights.get(flightChoice - 1);
		} else {
			System.out.println("Invalid flight choice.");
			return;
		}
		passenger.flightName = availableFlights.get(flightChoice);

		System.out.println("Available Seats: " + availableSeats);
		System.out.println("Enter Seat Number : ");
		passenger.seatNumber = scanner.nextInt();

		System.out.println("Enter Contact Number : ");
		passenger.contactNumber = scanner.nextLine();

		al.add(passenger);

		System.out.println("Ticket Booked Successfully!!!");

	}

	public static void cancelTicket(String passengerName) {
		Iterator<Passenger> itr = al.iterator();
		while (itr.hasNext()) {
			Passenger p = itr.next();
			if (p.passengerName.equalsIgnoreCase(passengerName)) {
				itr.remove();
				System.out.println("Ticket Of " + p.passengerName + " has been Canceled!!!");
				break;
			}
		}
		System.out.println("Ticket Not Found!!!");
	}

	public static void viewTicket(String passengerName) {
		for (Passenger passenger : al) {
			if (passenger.passengerName.equalsIgnoreCase(passengerName)) {
				System.out.println("Passenger Name: " + passenger.passengerName);
				System.out.println("Age: " + passenger.age);
				System.out.println("Flight Name: " + passenger.flightName);
				System.out.println("Seat Number: " + passenger.seatNumber);
				System.out.println("Contact Number: " + passenger.contactNumber);
				return;
			}
		}
		System.out.println("Ticket Not Found!!!");
	}

	public static void seeVacantSeats() {
		System.out.println("Vacant Seats: ");
		for (int seat : availableSeats) {
			System.out.print(seat + " ");
		}
		System.out.println();
	}
}
