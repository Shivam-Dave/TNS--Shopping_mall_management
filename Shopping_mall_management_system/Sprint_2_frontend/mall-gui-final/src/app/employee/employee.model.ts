/**
 * Defines the structure for an Employee object.
 * This interface provides type-safety and should match the
 * structure of the Employee entity in your Spring Boot backend.
 */
export interface Employee {
    employeeId: number;
    name: string;
    position: string;
    salary: number;
  }
  