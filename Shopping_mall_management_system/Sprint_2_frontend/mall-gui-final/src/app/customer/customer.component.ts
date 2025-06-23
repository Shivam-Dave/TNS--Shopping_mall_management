import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, NgForm } from '@angular/forms';
import { CustomerService } from './customer.service';
import { Customer } from './customer.model';

@Component({
  selector: 'app-customer',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './customer.component.html',
  styleUrls: ['./customer.component.scss']
})
export class CustomerComponent implements OnInit {

  public customers: Customer[] = [];
  public selectedCustomer: Customer | null = null;
  public isEditing = false;

  constructor(private customerService: CustomerService) { }

  ngOnInit(): void {
    this.getCustomers();
  }

  getCustomers(): void {
    this.customerService.getCustomers().subscribe({
      next: (data) => this.customers = data,
      error: (err: any) => console.error('Error fetching customers:', err)
    });
  }

  onFormSubmit(form: NgForm): void {
    if (form.invalid) return;

    if (this.isEditing && this.selectedCustomer) {
      const updatedCustomer: Customer = { ...this.selectedCustomer, ...form.value };
      this.customerService.updateCustomer(updatedCustomer).subscribe(() => {
        this.getCustomers();
        this.resetForm(form);
      });
    } else {
      this.customerService.addCustomer(form.value).subscribe(() => {
        this.getCustomers();
        this.resetForm(form);
      });
    }
  }

  onEdit(customer: Customer): void {
    this.isEditing = true;
    this.selectedCustomer = { ...customer };
  }

  onDelete(id: number): void {
    if (confirm('Are you sure you want to delete this customer?')) {
      this.customerService.deleteCustomer(id).subscribe({
        next: () => this.getCustomers(),
        error: (err: any) => console.error('Error deleting customer:', err)
      });
    }
  }

  resetForm(form?: NgForm): void {
    if (form) form.resetForm();
    this.isEditing = false;
    this.selectedCustomer = null;
  }
}
