package medicationtracking;

import java.util.ArrayList;
import java.util.List;

// Patient class extends Person, holds medications and prescriptions
public class Patient extends Person {
    private List<Medication> medications;
    private List<Prescription> prescriptions;

    public Patient(int id, String name, int age, String phoneNumber) {
        super(id, name, age, phoneNumber);
        this.medications = new ArrayList<>();
        this.prescriptions = new ArrayList<>();
    }

    public List<Medication> getMedications() { return medications; }
    public List<Prescription> getPrescriptions() { return prescriptions; }

    public void addMedication(Medication medication) {
        medications.add(medication);
    }

    public void addPrescription(Prescription prescription) {
        prescriptions.add(prescription);
    }
}