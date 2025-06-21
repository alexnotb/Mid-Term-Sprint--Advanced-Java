package medicationtracking;

import java.util.ArrayList;
import java.util.List;

// Doctor class extends Person, holds specialization and patients
public class Doctor extends Person {
    private String specialization;
    private List<Patient> patients;

    public Doctor(int id, String name, int age, String phoneNumber, String specialization) {
        super(id, name, age, phoneNumber);
        this.specialization = specialization;
        this.patients = new ArrayList<>();
    }

    public String getSpecialization() { return specialization; }
    public List<Patient> getPatients() { return patients; }

    public void addPatient(Patient patient) {
        patients.add(patient);
    }
}