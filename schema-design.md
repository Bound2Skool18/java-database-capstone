# Database Schema Design for Smart Clinic

## MySQL Database Design

### Table: patients
| Column Name | Data Type    | Constraints          |
|-------------|-------------|---------------------|
| id          | INT         | PRIMARY KEY, AUTO_INCREMENT |
| name        | VARCHAR(80) | NOT NULL            |
| dob         | DATE        | NOT NULL            |
| email       | VARCHAR(100)| UNIQUE, NOT NULL    |
| phone       | VARCHAR(20) |                     |

### Table: doctors
| Column Name | Data Type    | Constraints          |
|-------------|-------------|---------------------|
| id          | INT         | PRIMARY KEY, AUTO_INCREMENT |
| name        | VARCHAR(80) | NOT NULL            |
| specialty   | VARCHAR(100)| NOT NULL            |
| email       | VARCHAR(100)| UNIQUE, NOT NULL    |

### Table: appointments
| Column Name  | Data Type | Constraints                               |
|--------------|-----------|-------------------------------------------|
| id           | INT       | PRIMARY KEY, AUTO_INCREMENT               |
| patient_id   | INT       | FOREIGN KEY (patients.id), NOT NULL       |
| doctor_id    | INT       | FOREIGN KEY (doctors.id), NOT NULL        |
| appt_date    | DATETIME  | NOT NULL                                  |
| status       | VARCHAR(20)| NOT NULL                                 |

### Table: admin
| Column Name | Data Type    | Constraints          |
|-------------|-------------|---------------------|
| id          | INT         | PRIMARY KEY, AUTO_INCREMENT |
| username    | VARCHAR(40) | UNIQUE, NOT NULL    |
| password    | VARCHAR(255)| NOT NULL            |

---

## MongoDB Collection Design

### Collection: prescriptions

Example document:
```json
{
  "patient_id": 123,
  "doctor_id": 45,
  "prescribed_at": "2025-10-01T13:30:00Z",
  "medications": [
    {"name": "Amoxicillin", "dose": "500mg", "instructions": "Take twice daily"},
    {"name": "Paracetamol", "dose": "650mg", "instructions": "Take as needed for pain"}
  ],
  "notes": "Patient allergic to penicillin substitutes."
}
```
<!--
- patient_id and doctor_id reference MySQL tables (relational link).
- Medications is an array—one prescription can have many meds.
- Notes is a flexible field for freeform comments.
-->

---

## Why this design?

- **MySQL** is used for data that is structured and relational, like patients and appointments (easy to query, reliable relationships).
- **MongoDB** is used for prescriptions because they can have a flexible structure (lists of medications, optional notes).
