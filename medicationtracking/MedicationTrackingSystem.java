package medicationtracking;

import java.util.*;
import java.time.LocalDate;

// Main system class for managing patients, doctors, medications, and prescriptions
public class MedicationTrackingSystem {
    private List<Patient> patients;
    private List<Doctor> doctors;
    private List<Medication> medications;
    private List<Prescription> prescriptions;

    public MedicationTrackingSystem() {
        patients = new ArrayList<>();
        doctors = new ArrayList<>();
        medications = new ArrayList<>();
        prescriptions = new ArrayList<>();
    }

    // Add a patient
    public void addPatient(Patient patient) {
        patients.add(patient);
    }

    // Add a doctor
    public void addDoctor(Doctor doctor) {
        doctors.add(doctor);
    }

    // Add a medication
    public void addMedication(Medication medication) {
        medications.add(medication);
    }

    // Add a prescription
    public void addPrescription(Prescription prescription) {
        prescriptions.add(prescription);
        prescription.getPatient().addPrescription(prescription);
        prescription.getPatient().addMedication(prescription.getMedication());
    }

    // Find patient by name
    public Patient findPatientByName(String name) {
        for (Patient p : patients) {
            if (p.getName().equalsIgnoreCase(name)) return p;
        }
        return null;
    }

    // Find doctor by name
    public Doctor findDoctorByName(String name) {
        for (Doctor d : doctors) {
            if (d.getName().equalsIgnoreCase(name)) return d;
        }
        return null;
    }

    // Find medication by name
    public Medication findMedicationByName(String name) {
        for (Medication m : medications) {
            if (m.getName().equalsIgnoreCase(name)) return m;
        }
        return null;
    }

    // Remove patient
    public void removePatient(Patient patient) {
        patients.remove(patient);
    }

    // Remove doctor
    public void removeDoctor(Doctor doctor) {
        doctors.remove(doctor);
    }

    // Remove medication
    public void removeMedication(Medication medication) {
        medications.remove(medication);
    }

    // Get expired medications
    public List<Medication> getExpiredMedications() {
        List<Medication> expired = new ArrayList<>();
        LocalDate today = LocalDate.now();
        for (Medication m : medications) {
            if (m.getExpiryDate().isBefore(today)) {
                expired.add(m);
            }
        }
        return expired;
    }

    // Get all prescriptions by doctor
    public List<Prescription> getPrescriptionsByDoctor(Doctor doctor) {
        List<Prescription> result = new ArrayList<>();
        for (Prescription p : prescriptions) {
            if (p.getDoctor().equals(doctor)) {
                result.add(p);
            }
        }
        return result;
    }

    // Get all prescriptions for a patient in the last year
    public List<Prescription> getPrescriptionsForPatientLastYear(Patient patient) {
        List<Prescription> result = new ArrayList<>();
        LocalDate oneYearAgo = LocalDate.now().minusYears(1);
        for (Prescription p : prescriptions) {
            if (p.getPatient().equals(patient) && p.getIssueDate().isAfter(oneYearAgo)) {
                result.add(p);
            }
        }
        return result;
    }

    public List<Medication> getMedications() {
        return medications;
    }

    public List<Patient> getPatients() {
        return patients;
    }

    public List<Doctor> getDoctors() {
        return doctors;
    }

    public List<Prescription> getPrescriptions() {
        return prescriptions;
    }

    // Restock medication
    public void restockMedication(Medication medication, int amount) {
        medication.setQuantityInStock(medication.getQuantityInStock() + amount);
    }
}
