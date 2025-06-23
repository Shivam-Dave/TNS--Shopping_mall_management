import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, NgForm } from '@angular/forms';
import { EmployeeService } from './employee.service'; // FIX: The import path is now simpler
import { Employee } from './employee.model';

@Component({
  selector: 'app-employee',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './employee.component.html',
  styleUrls: ['./employee.component.scss']
})
export class EmployeeComponent implements OnInit {

  public employees: Employee[] = [];
  public selectedEmployee: Employee | null = null;
  public isEditing = false;

  constructor(private employeeService: EmployeeService) { }

  ngOnInit(): void {
    this.getEmployees();
  }

  getEmployees(): void {
    this.employeeService.getEmployees().subscribe({
      next: (data: Employee[]) => {
        this.employees = data;
      },
      error: (err: any) => console.error('Error fetching employees:', err)
    });
  }

  onFormSubmit(form: NgForm): void {
    if (form.invalid) return;

    if (this.isEditing && this.selectedEmployee) {
      const updatedEmployee: Employee = { ...this.selectedEmployee, ...form.value };
      this.employeeService.updateEmployee(updatedEmployee).subscribe(() => this.getEmployees());
    } else {
      this.employeeService.addEmployee(form.value).subscribe(() => this.getEmployees());
    }
     this.resetForm(form);
  }

  onEdit(employee: Employee): void {
    this.isEditing = true;
    this.selectedEmployee = { ...employee };
  }

  onDelete(id: number): void {
    if (confirm('Are you sure you want to delete this employee?')) {
      this.employeeService.deleteEmployee(id).subscribe({
        next: () => this.getEmployees(),
        error: (err: any) => console.error('Error deleting employee:', err)
      });
    }
  }

  resetForm(form?: NgForm): void {
    if (form) form.resetForm();
    this.isEditing = false;
    this.selectedEmployee = null;
  }
}
