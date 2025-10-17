import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

// Interface 1: The main contract that ALL vehicles must follow
interface Vehicle {
    String getMake();
    String getModel();
    int getYear();
}

// Interface 2: Extra stuff just for Cars
interface CarVehicle {
    void setNumDoors(int doors);
    int getNumDoors();
    void setFuelType(String fuel);
    String getFuelType(); // FIXED: Changed return type from void to String
}

// Interface 3: Extra stuff just for Motorcycles
interface MotorVehicle {
    void setNumWheels(int wheels);
    int getNumWheels();
    void setMotorcycleType(String type);
    String getMotorcycleType();
}

// Interface 4: Extra stuff just for Trucks
interface TruckVehicle {
    void setCargoCapacity(double capacity);
    double getCargoCapacity();
    void setTransmissionType(String type);
    String getTransmissionType();
}

// Class 1: Car. Implements the main contract plus the car-specific one.
class Car implements Vehicle, CarVehicle {
    // Shared info
    private String make;
    private String model;
    private int year;
    
    // Car-specific info
    private int doors;
    private String fuelType;

    public Car(String make, String model, int year) {
        this.make = make;
        this.model = model;
        this.year = year;
    }

    // Implementing Vehicle methods
    public String getMake() { return make; }
    public String getModel() { return model; }
    public int getYear() { return year; }

    // Implementing CarVehicle methods
    public void setNumDoors(int doors) { this.doors = doors; }
    public int getNumDoors() { return doors; }
    public void setFuelType(String fuel) { this.fuelType = fuel; }
    public String getFuelType() { return fuelType; } // Correct implementation matching the interface
}

// Class 2: Motorcycle. Implements the main contract plus the motor-specific one.
class Motorcycle implements Vehicle, MotorVehicle {
    // Shared info
    private String make;
    private String model;
    private int year;
    
    // Motorcycle-specific info
    private int wheels;
    private String bikeType;

    public Motorcycle(String make, String model, int year) {
        this.make = make;
        this.model = model;
        this.year = year;
    }

    // Implementing Vehicle methods
    public String getMake() { return make; }
    public String getModel() { return model; }
    public int getYear() { return year; }

    // Implementing MotorVehicle methods
    public void setNumWheels(int wheels) { this.wheels = wheels; }
    public int getNumWheels() { return wheels; }
    public void setMotorcycleType(String type) { this.bikeType = type; }
    public String getMotorcycleType() { return bikeType; }
}

// Class 3: Truck. Implements the main contract plus the truck-specific one.
class Truck implements Vehicle, TruckVehicle {
    // Shared info
    private String make;
    private String model;
    private int year;
    
    // Truck-specific info
    private double cargoTons;
    private String transmissionType;

    public Truck(String make, String model, int year) {
        this.make = make;
        this.model = model;
        this.year = year;
    }

    // Implementing Vehicle methods
    public String getMake() { return make; }
    public String getModel() { return model; }
    public int getYear() { return year; }

    // Implementing TruckVehicle methods
    public void setCargoCapacity(double capacity) { this.cargoTons = capacity; }
    public double getCargoCapacity() { return cargoTons; }
    public void setTransmissionType(String type) { this.transmissionType = type; }
    public String getTransmissionType() { return transmissionType; }
}

// Main class to run the program
public class VehicleSystem {
    
    // List to hold all vehicles (we can do this because they all implement Vehicle)
    private static ArrayList<Vehicle> inventory = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        
        boolean running = true;
        while (running) {
            displayMenu();
            int choice = 0;
            
            try {
                choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline
            } catch (InputMismatchException e) {
                System.out.println("❌ Invalid input. Please enter a number.");
                scanner.nextLine();
                continue;
            }

            switch (choice) {
                case 1:
                    addVehicle(1); // Add Car
                    break;
                case 2:
                    addVehicle(2); // Add Motorcycle
                    break;
                case 3:
                    addVehicle(3); // Add Truck
                    break;
                case 4:
                    displayAllVehicles();
                    break;
                case 5:
                    running = false;
                    System.out.println("Exiting the rental system. Bye!");
                    break;
                default:
                    System.out.println("That number doesn't work. Try again.");
            }
        }
    }

    public static void displayMenu() {
        System.out.println("\n--- Vehicle Rental Agency Manager ---");
        System.out.println("1. Add a Car");
        System.out.println("2. Add a Motorcycle");
        System.out.println("3. Add a Truck");
        System.out.println("4. Display Inventory");
        System.out.println("5. Exit");
        System.out.print("Enter choice: ");
    }
    
    // This handles the input of the shared data (Make, Model, Year)
    public static Vehicle createBaseVehicle(int type) {
        String typeName = (type == 1) ? "Car" : (type == 2) ? "Motorcycle" : "Truck";
        System.out.println("\n--- Entering " + typeName + " Details ---");
        
        System.out.print("Enter Make (e.g., Honda): ");
        String make = scanner.nextLine();
        
        System.out.print("Enter Model (e.g., Civic): ");
        String model = scanner.nextLine();
        
        System.out.print("Enter Year (number): ");
        int year = 0;
        try {
            year = scanner.nextInt();
            scanner.nextLine();
        } catch (InputMismatchException e) {
            System.out.println("❌ Error: Year must be a whole number. Vehicle creation failed.");
            scanner.nextLine();
            return null;
        }
        
        // This is where we create the actual object based on the menu choice
        if (type == 1) return new Car(make, model, year);
        if (type == 2) return new Motorcycle(make, model, year);
        if (type == 3) return new Truck(make, model, year);
        return null; 
    }

    // This method handles the creation and setting of unique data
    public static void addVehicle(int type) {
        Vehicle newVehicle = createBaseVehicle(type);
        if (newVehicle == null) return; // Exit if base creation failed (e.g., bad year input)
        
        // Use an if/else if block to cast the generic Vehicle into its specific type
        if (newVehicle instanceof Car) {
            Car car = (Car) newVehicle;
            
            System.out.print("Doors (2 or 4): ");
            int doors = 0;
            try {
                doors = scanner.nextInt();
                scanner.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("❌ Invalid input for doors. Setting to 4.");
                scanner.nextLine();
                doors = 4;
            }
            car.setNumDoors(doors);
            
            System.out.print("Fuel Type (petrol/diesel/electric): ");
            car.setFuelType(scanner.nextLine());
            
        } else if (newVehicle instanceof Motorcycle) {
            Motorcycle bike = (Motorcycle) newVehicle;
            
            System.out.print("Wheels (usually 2): ");
            int wheels = 0;
            try {
                wheels = scanner.nextInt();
                scanner.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("❌ Invalid input for wheels. Setting to 2.");
                scanner.nextLine();
                wheels = 2;
            }
            bike.setNumWheels(wheels);
            
            System.out.print("Type (sport/cruiser/off-road): ");
            bike.setMotorcycleType(scanner.nextLine());
            
        } else if (newVehicle instanceof Truck) {
            Truck truck = (Truck) newVehicle;
            
            System.out.print("Cargo Capacity in Tons (e.g., 5.5): ");
            double tons = 0.0;
            try {
                tons = scanner.nextDouble();
                scanner.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("❌ Invalid input for tons. Setting to 1.0.");
                scanner.nextLine();
                tons = 1.0;
            }
            truck.setCargoCapacity(tons);
            
            System.out.print("Transmission (manual/automatic): ");
            truck.setTransmissionType(scanner.nextLine());
        }
        
        inventory.add(newVehicle);
        System.out.println("🎉 Vehicle successfully added to inventory! 🎉");
    }

    // This method displays all details for all items in the inventory
    public static void displayAllVehicles() {
        if (inventory.isEmpty()) {
            System.out.println("\nInventory is empty. Add some vehicles first!");
            return;
        }
        
        System.out.println("\n--- Current Rental Inventory (" + inventory.size() + " total) ---");
        
        // Loop through the generic Vehicle list
        for (int i = 0; i < inventory.size(); i++) {
            Vehicle v = inventory.get(i);
            System.out.println("\n" + (i + 1) + ". " + v.getMake() + " " + v.getModel() + " (" + v.getYear() + ")");
            
            // Now we use if/else if to check the specific type and display the extra info
            if (v instanceof Car) {
                Car car = (Car) v;
                System.out.println("   Type: CAR 🚗");
                System.out.println("   Doors: " + car.getNumDoors());
                System.out.println("   Fuel: " + car.getFuelType());
            } else if (v instanceof Motorcycle) {
                Motorcycle bike = (Motorcycle) v;
                System.out.println("   Type: MOTORCYCLE 🏍️");
                System.out.println("   Wheels: " + bike.getNumWheels());
                System.out.println("   Style: " + bike.getMotorcycleType());
            } else if (v instanceof Truck) {
                Truck truck = (Truck) v;
                System.out.println("   Type: TRUCK 🚛");
                System.out.println("   Cargo: " + truck.getCargoCapacity() + " tons");
                System.out.println("   Trans: " + truck.getTransmissionType());
            }
        }
    }
}
