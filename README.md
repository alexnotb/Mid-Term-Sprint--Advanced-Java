# Mid-Term-Sprint--Advanced-Java

## Project Description

This project is a simple console-based Pharmacy Medication Tracking System implemented in Java.  
It allows users to manage patients, doctors, medications, and prescriptions using an object-oriented approach.  
The system provides a menu-driven interface for adding, viewing, and managing all entities.

## Features Implemented (according to the assignment)

- **Object-Oriented Design:**  
  - Implemented classes: `Person` (abstract), `Patient`, `Doctor`, `Medication`, `Prescription`, and `MedicationTrackingSystem`.
  - Used inheritance for `Patient` and `Doctor` from `Person`.
  - Used composition for relationships between patients, doctors, medications, and prescriptions.

- **Console Menu:**  
  - The `Menu` class provides a user-friendly console interface.
  - Users can add new patients, doctors, and medications.
  - Users can process new prescriptions.
  - System can print reports for all entities.
  - Users can check for expired medications.
  - Users can restock medications.
  - Users can view all prescriptions for a specific doctor or patient (for the last year).
  - After each action, the system prompts the user to press Enter to continue.

- **Data Storage:**  
  - All data is stored in memory (lists inside `MedicationTrackingSystem`) for the duration of the program.
  - No persistent storage is used, as per assignment requirements.

## How to Run the Project

1. **Open a terminal** in the project root directory (where the `medicationtracking` folder is located).

2. **Compile all Java files:**
   ```
   javac medicationtracking\*.java
   ```

3. **Run the main menu:**
   ```
   java medicationtracking.Menu
   ```

4. **Follow the on-screen instructions** in the console to use the system.