import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Employee } from './employee.model'; // FIX: Corrected the import path

@Injectable({
  providedIn: 'root'
})
export class EmployeeService {

  private readonly API_URL = 'http://localhost:8083/api/employee';

  constructor(private http: HttpClient) { }

  getEmployees(): Observable<Employee[]> {
    return this.http.get<Employee[]>(this.API_URL);
  }

  addEmployee(employee: Omit<Employee, 'employeeId'>): Observable<Employee> {
    return this.http.post<Employee>(this.API_URL, employee);
  }

  updateEmployee(employee: Employee): Observable<void> {
    return this.http.put<void>(`${this.API_URL}/${employee.employeeId}`, employee);
  }

  deleteEmployee(employeeId: number): Observable<void> {
    return this.http.delete<void>(`${this.API_URL}/${employeeId}`);
  }
}
