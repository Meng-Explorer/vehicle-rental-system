# SLIDE 1: Title Slide
- Title: Vehicle Rental Management System
- Subtitle: Object-Oriented Architecture & Implementation
- Presenter: Software Development Team
- Date: Project Milestone Review

---

# SLIDE 2: Project Objectives & Scope
- Goal: Manage rental workflows for Cars, Bikes, and Buses.
- Tracking: Availability statuses, rental durations, and automated pricing.
- User Workflows: Fleet onboarding, listing, dynamic rate checks, and check-in/out.

---

# SLIDE 3: Core OOP Architecture
- Abstraction: Abstract `Vehicle` defines contract (`calculateRent`) and shared state.
- Inheritance: `Car`, `Bike`, and `Bus` inherit foundational fields from `Vehicle`.
- Polymorphism: Tailored pricing formulas via method overriding per vehicle category.
- Encapsulation: Private member variables with protected accessors and defensive guards.

---

# SLIDE 4: Exception Handling & Defensive Design
- Custom Exceptions:
    * `VehicleNotAvailableException`: Prevents double bookings and unregistered checkouts.
    * `InvalidRentalException`: Intercepts non-positive rental days and bad inputs.
- Safe Menu Execution: Traps `NumberFormatException` and malformed console commands.

---

# SLIDE 5: Demonstration & Timeline
- Week 1: Class definitions, inheritance hierarchy, constructors.
- Week 2: Business rules, polymorphic pricing, command-line interface.
- Week 3: Error management, receipts history, validation testing.
- Week 4: Documentation, polish, test cases, and delivery.