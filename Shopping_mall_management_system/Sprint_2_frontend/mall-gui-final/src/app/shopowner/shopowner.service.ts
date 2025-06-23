import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { ShopOwner } from './shopowner.model';

@Injectable({
  providedIn: 'root'
})
export class ShopownerService {

  private readonly API_URL = 'http://localhost:8083/api/shopowner';

  constructor(private http: HttpClient) { }

  getShopOwners(): Observable<ShopOwner[]> {
    return this.http.get<ShopOwner[]>(this.API_URL);
  }

  addShopOwner(shopOwner: Omit<ShopOwner, 'id'>): Observable<ShopOwner> {
    return this.http.post<ShopOwner>(this.API_URL, shopOwner);
  }

  updateShopOwner(shopOwner: ShopOwner): Observable<void> {
    return this.http.put<void>(`${this.API_URL}/${shopOwner.id}`, shopOwner);
  }

  deleteShopOwner(id: number): Observable<void> {
    return this.http.delete<void>(`${this.API_URL}/${id}`);
  }
}
