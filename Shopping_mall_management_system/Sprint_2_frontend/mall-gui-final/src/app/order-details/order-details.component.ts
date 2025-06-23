import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { OrderService } from '../order.service';
import { CustomerService } from '../customer/customer.service';
import { Order } from '../orders-list/order.model'; // Assuming this is the correct path
import { Customer } from '../customer/customer.model'; // Assuming this is the correct path

@Component({
  selector: 'app-order-details',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './order-details.component.html',
  styleUrls: ['./order-details.component.scss']
})
export class OrderDetailsComponent implements OnInit {
  order: Order | null = null;
  customers: Customer[] = [];
  selectedCustomerId!: number;
  selectedStatus!: string;
  orderStatuses = ['PENDING', 'SHIPPED', 'DELIVERED', 'CANCELLED'];

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private orderService: OrderService,
    private customerService: CustomerService
  ) {}

  ngOnInit(): void {
    this.loadOrder();
    this.loadCustomers();
  }

  loadOrder(): void {
    // Using Number() to ensure the id is a number
    const orderId = Number(this.route.snapshot.paramMap.get('id'));
    if (isNaN(orderId)) {
        this.goBack(); // or handle error
        return;
    }
    this.orderService.getOrderById(orderId).subscribe(data => {
      this.order = data;
      // Ensure data exists before assigning
      if (data && data.customer) {
        this.selectedCustomerId = data.customer.id;
        this.selectedStatus = data.status;
      }
    });
  }

  loadCustomers(): void {
    this.customerService.getCustomers().subscribe(data => this.customers = data);
  }

  updateItemQuantity(orderItemId: number, event: any): void {
    const quantity = event.target.value;
    // You will need to implement updateOrderItemQuantity in your OrderService
    // this.orderService.updateOrderItemQuantity(orderItemId, quantity).subscribe(() => this.loadOrder());
  }

  removeItem(orderItemId: number): void {
    if(confirm('Are you sure you want to remove this item?')) {
      // You will need to implement removeOrderItem in your OrderService
      // this.orderService.removeOrderItem(orderItemId).subscribe(() => this.loadOrder());
    }
  }

  // FIX: Now calls a specific backend endpoint and reloads data on success.
  saveCustomerChange(): void {
    if (!this.order) return;
    this.orderService.updateOrderCustomer(this.order.id, this.selectedCustomerId).subscribe(() => {
        alert('Customer updated!');
        this.loadOrder(); // <-- RELOAD a`fter update
    });
  }

  // FIX: Now calls a specific backend endpoint and reloads data on success.
  saveStatusChange(): void {
    if (!this.order) return;
    this.orderService.updateOrderStatus(this.order.id, this.selectedStatus).subscribe(() => {
        alert('Status updated!');
        this.loadOrder(); // <-- RELOAD after update
    });
  }

  goBack(): void {
    this.router.navigate(['/orders-list']); //FIX: Corrected router path
  }
}