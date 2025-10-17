Rental Agency Vehicle Manager 🚗🏍️🚛

<img width="676" height="548" alt="image" src="https://github.com/user-attachments/assets/8c166a57-ceba-4011-aba0-62e65869f862" />


This is a small Java console application designed to manage the inventory for a car rental agency. The main goal of this project was to demonstrate the use of Interfaces to enforce common behavior across different, unrelated classes (Car, Motorcycle, Truck).

The whole system is built around the idea that all vehicles need certain things (Make, Model, Year), but each type also has its own unique, required information (Doors, Wheels, Capacity, etc.).

Key Features and OOP Concepts 🔑
Interfaces as Contracts: We use four different interfaces (Vehicle, CarVehicle, MotorVehicle, TruckVehicle) to define what methods each class must implement. This ensures consistency across the entire inventory.

Polymorphism: The entire inventory is stored in a single list (ArrayList<Vehicle>). We can loop through this one generic list and then check the specific type (instanceof) to display the specialized details.

Encapsulation: All data (like make, model, doors) is kept private inside the classes, and is only accessed safely through public getter and setter methods.

Error Handling: Includes basic try-catch blocks to prevent the program from crashing if the user enters letters when the program expects a number (like for 'Year' or 'Doors').

How to Run This Program 🚀
Save the code as VehicleSystem.java.

Open your terminal or command prompt.

Compile the file:

Bash

javac VehicleSystem.java -->
Run the application:

Bash

java VehicleSystem -->
The program will display a menu, allowing you to add different types of vehicles and view the full inventory details.
