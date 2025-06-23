import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Item, NewItem } from './item.model';

@Injectable({
  providedIn: 'root'
})
export class ItemService {

  private readonly API_URL = 'http://localhost:8083/api';

  constructor(private http: HttpClient) { }

  // Fetches all items from all shops (for the customer storefront)
  getAllItems(): Observable<Item[]> {
    return this.http.get<Item[]>(`${this.API_URL}/items`);
  }

  // Fetches items for a specific shop owner
  getItemsByShopOwner(shopOwnerId: number): Observable<Item[]> {
    return this.http.get<Item[]>(`${this.API_URL}/shopowner/${shopOwnerId}/items`);
  }

  // Adds a new item for a specific shop owner
  addItem(shopOwnerId: number, item: NewItem): Observable<Item> {
    return this.http.post<Item>(`${this.API_URL}/shopowner/${shopOwnerId}/items`, item);
  }

  // Deletes an item
  deleteItem(itemId: number): Observable<void> {
    return this.http.delete<void>(`${this.API_URL}/items/${itemId}`);
  }
}
