package medicationtracking;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

// Console menu for interacting with the MedicationTrackingSystem
public class Menu {
    public static void main(String[] args) {
        MedicationTrackingSystem system = new MedicationTrackingSystem();
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;
        int idCounter = 1;
        Random random = new Random();

        while (!exit) {
            System.out.println("\n===== Pharmacy Med Tracking System =====");
            System.out.println("1: Add New Patient");
            System.out.println("2: Add New Doctor");
            System.out.println("3: Add New Medication");
            System.out.println("4: Print System Report");
            System.out.println("5: Check Expired Medications");
            System.out.println("6: Process New Prescription");
            System.out.println("7: Print All Prescriptions for Doctor");
            System.out.println("8: Restock Medications");
            System.out.println("9: Print All Prescriptions for Patient (last year)");
            System.out.println("10: Exit");
            System.out.print("Choose option: ");
            int option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    addNewPatient(scanner, system, idCounter++);
                    break;
                case 2:
                    addNewDoctor(scanner, system, idCounter++);
                    break;
                case 3:
                    addNewMedication(scanner, system, idCounter++, random);
                    break;
                case 4:
                    printSystemReport(system);
                    break;
                case 5:
                    checkExpiredMedications(system, scanner);
                    break;
                case 6:
                    processNewPrescription(scanner, system, idCounter++);
                    break;
                case 7:
                    printPrescriptionsForDoctor(scanner, system);
                    break;
                case 8:
                    restockMedications(system, random, scanner);
                    break;
                case 9:
                    printPrescriptionsForPatient(scanner, system);
                    break;
                case 10:
                    exit = true;
                    System.out.println("Exiting system. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid option.");
            }
        }
        scanner.close();
    }

    // Add new patient
    private static void addNewPatient(Scanner scanner, MedicationTrackingSystem system, int id) {
        System.out.print("Enter patient name: ");
        String name = scanner.nextLine();
        System.out.print("Enter age: ");
        int age = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter phone: ");
        String phone = scanner.nextLine();
        Patient patient = new Patient(id, name, age, phone);
        system.addPatient(patient);
        System.out.println("Patient added.");
        System.out.println("Press Enter to continue...");
        scanner.nextLine();
    }

    // Add new doctor
    private static void addNewDoctor(Scanner scanner, MedicationTrackingSystem system, int id) {
        System.out.print("Enter doctor name: ");
        String name = scanner.nextLine();
        System.out.print("Enter age: ");
        int age = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter phone: ");
        String phone = scanner.nextLine();
        System.out.print("Enter specialization: ");
        String specialization = scanner.nextLine();
        Doctor doctor = new Doctor(id, name, age, phone, specialization);
        system.addDoctor(doctor);
        System.out.println("Doctor added.");
        System.out.println("Press Enter to continue...");
        scanner.nextLine();
    }

    // Add new medication
    private static void addNewMedication(Scanner scanner, MedicationTrackingSystem system, int id, Random random) {
        System.out.print("Enter medication name: ");
        String name = scanner.nextLine();
        System.out.print("Enter dose: ");
        String dose = scanner.nextLine();
        System.out.print("Enter quantity in stock: ");
        int quantity = scanner.nextInt();
        scanner.nextLine();
        LocalDate expiry = LocalDate.now().minusDays(random.nextInt(365)).plusDays(random.nextInt(730));
        Medication medication = new Medication(id, name, dose, quantity, expiry);
        system.addMedication(medication);
        System.out.println("Medication added.");
        System.out.println("Press Enter to continue...");
        scanner.nextLine();
    }

    // Print system report
    private static void printSystemReport(MedicationTrackingSystem system) {
        System.out.println("\n--- System Report ---");
        System.out.println("Patients:");
        for (Patient p : system.getPatients()) {
            System.out.println(p.getName() + ", age: " + p.getAge() + ", phone: " + p.getPhoneNumber());
        }
        System.out.println("\nDoctors:");
        for (Doctor d : system.getDoctors()) {
            System.out.println(d.getName() + ", specialization: " + d.getSpecialization());
        }
        System.out.println("\nMedications:");
        for (Medication m : system.getMedications()) {
            System.out.println(m.getName() + ", dose: " + m.getDose() + ", in stock: " + m.getQuantityInStock() +
                    ", expiry: " + m.getExpiryDate());
        }
        System.out.println("\nPrescriptions:");
        for (Prescription pr : system.getPrescriptions()) {
            System.out.println("Prescription #" + pr.getId() + ": " + pr.getMedication().getName() +
                    " for " + pr.getPatient().getName() + " by Dr. " + pr.getDoctor().getName() +
                    " (issued: " + pr.getIssueDate() + ", expires: " + pr.getExpiryDate() + ")");
        }
        // Wait for Enter to continue
        System.out.println("Press Enter to continue...");
        new Scanner(System.in).nextLine();
    }

    // Check expired medications
    private static void checkExpiredMedications(MedicationTrackingSystem system, Scanner scanner) {
        List<Medication> expired = system.getExpiredMedications();
        if (expired.isEmpty()) {
            System.out.println("No expired medications.");
        } else {
            System.out.println("Expired medications:");
            for (Medication m : expired) {
                System.out.println(m.getName() + " (expired: " + m.getExpiryDate() + ")");
            }
        }
        System.out.println("Press Enter to continue...");
        scanner.nextLine();
    }

    // Process new prescription
    private static void processNewPrescription(Scanner scanner, MedicationTrackingSystem system, int id) {
        System.out.print("Enter doctor name: ");
        String docName = scanner.nextLine();
        Doctor doc = system.findDoctorByName(docName);
        if (doc == null) {
            System.out.println("Doctor not found.");
            return;
        }
        System.out.print("Enter patient name: ");
        String patName = scanner.nextLine();
        Patient pat = system.findPatientByName(patName);
        if (pat == null) {
            System.out.println("Patient not found.");
            return;
        }
        System.out.print("Enter medication name: ");
        String medName = scanner.nextLine();
        Medication medication = system.findMedicationByName(medName);
        if (medication == null) {
            System.out.println("Medication not found.");
            return;
        }
        Prescription prescription = new Prescription(id, doc, pat, medication, LocalDate.now());
        system.addPrescription(prescription);
        doc.addPatient(pat);
        System.out.println("Prescription processed.");
        System.out.println("Press Enter to continue...");
        scanner.nextLine();
    }

    // Print all prescriptions for a doctor
    private static void printPrescriptionsForDoctor(Scanner scanner, MedicationTrackingSystem system) {
        System.out.print("Enter doctor name: ");
        String doctorName = scanner.nextLine();
        Doctor doctorObj = system.findDoctorByName(doctorName);
        if (doctorObj == null) {
            System.out.println("Doctor not found.");
            return;
        }
        List<Prescription> docPrescriptions = system.getPrescriptionsByDoctor(doctorObj);
        if (docPrescriptions.isEmpty()) {
            System.out.println("No prescriptions for this doctor.");
        } else {
            for (Prescription pr : docPrescriptions) {
                System.out.println("Prescription #" + pr.getId() + ": " +
                        pr.getMedication().getName() + " for " +
                        pr.getPatient().getName() + " (issued: " + pr.getIssueDate() + ")");
            }
        }
        System.out.println("Press Enter to continue...");
        scanner.nextLine();
    }

    // Restock all medications
    private static void restockMedications(MedicationTrackingSystem system, Random random, Scanner scanner) {
        for (Medication m : system.getMedications()) {
            int addQty = random.nextInt(50) + 1;
            system.restockMedication(m, addQty);
            System.out.println("Restocked " + m.getName() + " by " + addQty + " units.");
        }
        System.out.println("Press Enter to continue...");
        scanner.nextLine();
    }

    // Print all prescriptions for a patient in the last year
    private static void printPrescriptionsForPatient(Scanner scanner, MedicationTrackingSystem system) {
        System.out.print("Enter patient name: ");
        String patientName = scanner.nextLine();
        Patient patientObj = system.findPatientByName(patientName);
        if (patientObj == null) {
            System.out.println("Patient not found.");
            return;
        }
        List<Prescription> patientPrescriptions = system.getPrescriptionsForPatientLastYear(patientObj);
        if (patientPrescriptions.isEmpty()) {
            System.out.println("No prescriptions for this patient in the last year.");
        } else {
            System.out.println("Medications prescribed in the last year:");
            for (Prescription pr : patientPrescriptions) {
                System.out.println(pr.getMedication().getName());
            }
        }
        System.out.println("Press Enter to continue...");
        scanner.nextLine();
    }
}
