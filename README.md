# Vehicle Rental Management System

A robust, Object-Oriented console application built in Java to manage vehicle rentals (Cars, Bikes, and Buses), track inventory availability, compute customized category rates, and enforce defensive data validation.

---

## Table of Contents
- [Project Overview](#project-overview)
- [OOP Architecture & Design](#oop-architecture--design)
- [Class & Entity Specifications](#class--entity-specifications)
- [Pricing & Business Logic](#pricing--business-logic)
- [Prerequisites & Getting Started](#prerequisites--getting-started)
- [CLI Usage Guide](#cli-usage-guide)
- [Exception Handling & Validation](#exception-handling--validation)
- [Test Cases Matrix](#test-cases-matrix)
- [Project Rubric & Milestone Alignment](#project-rubric--milestone-alignment)

---

## Project Overview

The **Vehicle Rental Management System** is designed to digitize and automate the day-to-day operations of a multi-tier rental agency:
- **Fleet Inventory Control**: Register, track, and maintain real-time availability states across diverse vehicle classes.
- **Rental Life Cycle**: Dispatch and return assets with automated receipt generation and audit logging.
- **Defensive Execution**: Prevent edge cases like double booking, invalid return attempts, and non-numeric input crashes.

---

## OOP Architecture & Design

The project strictly follows core Object-Oriented Programming (OOP) paradigms:

1. **Abstraction**: 
   - An abstract base class `Vehicle` enforces a standardized interface across all vehicle types via `calculateRent(int days)`.
2. **Inheritance**: 
   - Specialized subclasses (`Car`, `Bike`, `Bus`) inherit common fields (`vehicleId`, `brand`, `model`, `baseRentalRate`, `isRented`) while adding specialized attributes (e.g., `numberOfSeats`, `bikeType`, `passengerCapacity`).
3. **Polymorphism**: 
   - The pricing engine dynamically invokes overridden `calculateRent()` implementations at runtime based on the underlying object type.
4. **Encapsulation**: 
   - Internal states (`isRented`, rates) are private and guarded with validation checks and accessors. Historical records (`RentalRecord`) remain immutable after creation.

```
                  ┌────────────────────────┐
                  │          Main          │ (Console Presentation)
                  └───────────┬────────────┘
                              │ delegates to
                              ▼
                  ┌────────────────────────┐
                  │      RentalSystem      │ (Controller / Fleet Manager)
                  └───┬────────────────┬───┘
                      │ manages        │ creates
                      ▼                ▼
         ┌───────────────────┐    ┌───────────────────┐
         │  Vehicle (Abstract)│    │   RentalRecord    │ (Immutable Audit Log)
         └─────────▲─────────┘    └───────────────────┘
                   │ inherits
       ┌───────────┼───────────┐
       │           │           │
 ┌─────┴─────┐ ┌───┴───┐ ┌─────┴─────┐
 │    Car    │ │ Bike  │ │    Bus    │
 └───────────┘ └───────┘ └───────────┘
```

---

## Class & Entity Specifications

| Class | Type | Key Fields | Key Methods | Role |
| :--- | :--- | :--- | :--- | :--- |
| **`Vehicle`** | `abstract class` | `vehicleId`, `brand`, `model`, `baseRentalRate`, `isRented` | `calculateRent(int days)` (abstract), `rentVehicle()`, `returnVehicle()` | Base model and state manager for fleet assets |
| **`Car`** | Subclass (`Vehicle`) | `numberOfSeats` | `calculateRent(int days)`, `toString()` | Implements per-seat surcharge and weekly booking discounts |
| **`Bike`** | Subclass (`Vehicle`) | `bikeType` | `calculateRent(int days)`, `toString()` | Implements daily flat rate with 5-day discount tier |
| **`Bus`** | Subclass (`Vehicle`) | `passengerCapacity` | `calculateRent(int days)`, `toString()` | Applies fixed commercial surcharge and long-haul discounts |
| **`RentalSystem`** | Service Class | `fleet: Map<String, Vehicle>`, `rentalHistory: List<RentalRecord>` | `addVehicle()`, `getAvailableVehicles()`, `rentVehicle()`, `returnVehicle()` | Central manager handling inventory lookups and transactions |
| **`RentalRecord`** | Domain Entity | `recordId`, `vehicleId`, `days`, `totalCost`, `timestamp` | Getters, `toString()` | Immutable receipt record for transaction audit trails |
| **`VehicleNotAvailableException`** | Custom Exception | `message` | Constructor | Thrown when an asset is missing or already rented |
| **`InvalidRentalException`** | Custom Exception | `message` | Constructor | Thrown when rental inputs (e.g., duration $\le 0$) fail validation |

---

## Pricing & Business Logic

Each vehicle type computes rental pricing using polymorphic calculations:

- **Car**:
  $$\text{Daily Rate} = \text{Base Rate} + (\text{Number of Seats} \times 5.0)$$
  $$\text{Total Cost} = \text{Daily Rate} \times \text{Days} \times (0.90 \text{ if Days} \ge 7 \text{ else } 1.0)$$
- **Bike**:
  $$\text{Total Cost} = (\text{Base Rate} \times \text{Days}) \times (0.85 \text{ if Days} \ge 5 \text{ else } 1.0)$$
- **Bus**:
  $$\text{Total Cost} = ((\text{Base Rate} \times \text{Days}) + 50.0) \times (0.95 \text{ if Days} \ge 10 \text{ else } 1.0)$$

---

## Prerequisites & Getting Started

### Prerequisites
- **Java Development Kit (JDK)**: Version 17 or higher
- **Terminal / Command Prompt**

### Compilation
Navigate to the root directory where `Main.java` is located:
```bash
javac Main.java
```

### Execution
Run the compiled application:
```bash
java Main
```

---

## CLI Usage Guide

When started, the application initializes default sample data and presents a numbered console menu:

```text
===== VEHICLE RENTAL MANAGEMENT SYSTEM =====
1. List Available Vehicles
2. Rent a Vehicle
3. Return a Vehicle
4. Add a New Vehicle (Admin)
5. View Rental Receipts & History
6. Exit
Enter choice (1-6): 
```

- **Option 1 (List Available Vehicles)**: Displays all fleet assets with `isRented == false`.
- **Option 2 (Rent a Vehicle)**: Prompts for `Vehicle ID` and `duration (days)`. Calculates the dynamic total price, marks the vehicle as rented, and generates an audit receipt.
- **Option 3 (Return a Vehicle)**: Prompts for `Vehicle ID` and resets its state to available.
- **Option 4 (Add a New Vehicle)**: Admin tool to register a new `Car`, `Bike`, or `Bus` into the fleet.
- **Option 5 (Rental Receipts & History)**: Lists all completed rental transactions with timestamps and total costs.
- **Option 6 (Exit)**: Safely closes resources and terminates the application.

---

## Exception Handling & Validation

The application utilizes defensive programming to intercept errors without terminating unexpectedly:

| Exception / Condition | Cause | System Handling |
| :--- | :--- | :--- |
| `VehicleNotAvailableException` | Target vehicle is already rented or ID does not exist | Rental is blocked; an informative message is printed to the user. |
| `InvalidRentalException` | User inputs rental duration $\le 0$ days | Transaction is rejected; user is prompted with valid duration rules. |
| `IllegalStateException` | User attempts to return a vehicle that is not rented | Rejection notice; prevents inconsistent state transitions. |
| `NumberFormatException` | Non-numeric input for menu options, seats, or rates | Caught in presentation layer; displays a validation notice without crashing. |
| `IllegalArgumentException` | Attempting to register an already existing vehicle ID | Rejects the vehicle addition and preserves the existing entry. |

---

## Test Cases Matrix

| Test Case | Step / Input | Expected Result | Pass/Fail |
| :--- | :--- | :--- | :---: |
| **TC-01: List Inventory** | Choose Option `1` | Displays default seeded vehicles (`C101`, `C102`, `B201`, `B202`, `U301`). | Pass |
| **TC-02: Normal Rental** | Rent `C101` for `3` days | Base $45 + (5 \times \$5) = \$70/\text{day} \times 3 = \$210.00$. Vehicle is marked rented. | Pass |
| **TC-03: Long-term Discount**| Rent `B201` for `5` days | Flat rate $25 \times 5 = \$125 \times 0.85 = \$106.25$ (15% off applied). | Pass |
| **TC-04: Double Booking** | Rent `C101` again while rented | Fails with `VehicleNotAvailableException: Vehicle C101 is already rented.` | Pass |
| **TC-05: Non-positive Days** | Rent `U301` for `-3` days | Fails with `InvalidRentalException: Rental duration must be at least 1 day.` | Pass |
| **TC-06: Return Asset** | Return `C101` | Marked available; reappears in available vehicle list. | Pass |
| **TC-07: Invalid Menu Input**| Enter string `abc` at main menu | Prints `Invalid option. Please try again.` without throwing uncaught exceptions. | Pass |

---

## Project Rubric & Milestone Alignment

- **Correct Core Features (30%)**: All required workflows (Add, View, Rent, Return, Exit) are fully implemented and verified.
- **Application of OOP Concepts (30%)**:
  - Abstract class architecture with clean inheritance hierarchies.
  - Runtime polymorphism via overridden pricing methods.
  - Strict data encapsulation and controlled accessors.
- **Input Validation & Error Handling (15%)**: Comprehensive coverage using custom exceptions and CLI input sanitization.
- **Code Quality & Documentation (25%)**: Formatted with standard naming conventions, comments, and project documentation.