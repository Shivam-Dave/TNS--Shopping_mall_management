import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { CartItem } from './cart.model';
import { Order } from './orders-list/order.model';

// This interface defines the data we send for an update
interface OrderUpdatePayload {
    customerId: number;
    status: string;
}

@Injectable({ providedIn: 'root' })
export class OrderService {
    private readonly API_URL = 'http://localhost:8083/api/orders';

    constructor(private http: HttpClient) { }

    getOrders(): Observable<Order[]> {
        return this.http.get<Order[]>(this.API_URL);
    }

    placeOrder(customerId: number, cartItems: CartItem[]): Observable<any> {
        const orderRequest = {
            customerId: customerId,
            items: cartItems.map(ci => ({ itemId: ci.item.id, quantity: ci.quantity }))
        };
        return this.http.post(this.API_URL, orderRequest);
    }

    updateOrder(orderId: number, payload: OrderUpdatePayload): Observable<any> {
        return this.http.put(`${this.API_URL}/${orderId}`, payload);
    }

    deleteOrder(orderId: number): Observable<any> {
        return this.http.delete(`${this.API_URL}/${orderId}`);
    }

    getOrderById(id: number): Observable<Order> {
      return this.http.get<Order>(`${this.API_URL}/${id}`);
  }
  
  updateOrderStatus(id: number, status: string): Observable<any> {
      return this.http.put(`${this.API_URL}/${id}/status`, { status });
  }

  updateOrderCustomer(id: number, customerId: number): Observable<any> {
      return this.http.put(`${this.API_URL}/${id}/customer`, { customerId });
  }

  removeOrderItem(orderItemId: number): Observable<any> {
      return this.http.delete(`${this.API_URL}/items/${orderItemId}`);
  }

  updateOrderItemQuantity(orderItemId: number, quantity: number): Observable<any> {
      return this.http.put(`${this.API_URL}/items/${orderItemId}`, { quantity });
  }
}
