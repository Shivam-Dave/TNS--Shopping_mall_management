import { Component, OnInit } from '@angular/core';
import { CommonModule, formatDate } from '@angular/common';
import { FormsModule, NgForm } from '@angular/forms';
import { RouterLink } from '@angular/router'; // FIX: Import RouterLink
import { OrderService } from '../order.service';
import { CustomerService } from '../customer/customer.service';
import { Order } from './order.model';
import { Customer } from '../customer/customer.model';

// FIX: Define a more detailed model for the editing object
interface OrderEdit {
  id: number;
  customerId: number;
  status: string;
  orderDate: string;
  totalAmount: number;
}

@Component({
  selector: 'app-orders-list',
  standalone: true,
  imports: [CommonModule, FormsModule, RouterLink], // FIX: Add RouterLink
  templateUrl: './orders-list.component.html',
  styleUrls: ['./orders-list.component.scss']
})
export class OrdersListComponent implements OnInit {
  public orders: Order[] = [];
  public customers: Customer[] = [];
  public isEditing = false;
  public orderToEdit: OrderEdit | null = null;
  public orderStatuses = ['PENDING', 'SHIPPED', 'DELIVERED', 'CANCELLED'];

  constructor(
    private orderService: OrderService,
    private customerService: CustomerService
  ) { }

  ngOnInit(): void {
    this.loadOrders();
    this.loadCustomers();
  }
  
  loadOrders(): void {
    this.orderService.getOrders().subscribe(data => this.orders = data);
  }

  loadCustomers(): void {
    this.customerService.getCustomers().subscribe(data => this.customers = data);
  }
  
  // FIX: Populate the full orderToEdit object, formatting the date correctly for the input field
  onEdit(order: Order): void {
    this.isEditing = true;
    this.orderToEdit = {
        id: order.id,
        customerId: order.customer.id,
        status: order.status,
        totalAmount: order.totalAmount,
        // Format the date to 'yyyy-MM-ddTHH:mm', which is what <input type="datetime-local"> expects
        orderDate: order.orderDate ? formatDate(order.orderDate, 'yyyy-MM-ddTHH:mm', 'en-US') : ''
    };
  }

  onDelete(orderId: number): void {
    if (confirm('Are you sure you want to delete this order? This will restock the items.')) {
        this.orderService.deleteOrder(orderId).subscribe(() => this.loadOrders());
    }
  }
  
  // FIX: Send the complete, updated payload to the backend
  onFormSubmit(form: NgForm): void {
      if (form.invalid || !this.orderToEdit) return;

      // Construct the full payload from the form values
      const payload = {
          customerId: form.value.customerId,
          status: form.value.status,
          orderDate: form.value.orderDate ? new Date(form.value.orderDate).toISOString() : null,
          totalAmount: form.value.totalAmount
      };

      this.orderService.updateOrder(this.orderToEdit.id, payload).subscribe(() => {
          this.loadOrders(); // Refresh the list
          this.resetForm(form); // Hide the form and reset state
      });
  }

  resetForm(form: NgForm): void {
    form.resetForm();
    this.isEditing = false;
    this.orderToEdit = null;
  }
}