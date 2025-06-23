import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import { CartItem } from './cart.model';
import { Item } from './item.model';

@Injectable({
  providedIn: 'root'
})
export class CartService {
  private cartItems = new BehaviorSubject<CartItem[]>([]);
  public cartItems$ = this.cartItems.asObservable();

  constructor() { }

  addToCart(itemToAdd: Item): void {
    const currentItems = this.cartItems.getValue();
    const existingItem = currentItems.find(cartItem => cartItem.item.id === itemToAdd.id);

    if (existingItem) {
      existingItem.quantity++;
    } else {
      currentItems.push({ item: itemToAdd, quantity: 1 });
    }
    this.cartItems.next([...currentItems]); // Emit a new array to trigger change detection
  }

  getCartTotal(): number {
    return this.cartItems.getValue().reduce((total, cartItem) => {
        return total + (cartItem.item.price * cartItem.quantity);
    }, 0);
  }

  clearCart(): void {
    this.cartItems.next([]);
  }
}
