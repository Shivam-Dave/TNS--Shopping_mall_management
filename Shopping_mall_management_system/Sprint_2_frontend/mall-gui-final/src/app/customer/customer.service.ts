import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Customer } from './customer.model';

@Injectable({
  providedIn: 'root'
})
export class CustomerService {

  private readonly API_URL = 'http://localhost:8083/api/customer';

  constructor(private http: HttpClient) { }

  getCustomers(): Observable<Customer[]> {
    return this.http.get<Customer[]>(this.API_URL);
  }

  addCustomer(customer: Omit<Customer, 'id'>): Observable<Customer> {
    return this.http.post<Customer>(this.API_URL, customer);
  }

  updateCustomer(customer: Customer): Observable<void> {
    return this.http.put<void>(`${this.API_URL}/${customer.id}`, customer);
  }

  deleteCustomer(id: number): Observable<void> {
    return this.http.delete<void>(`${this.API_URL}/${id}`);
  }
}
