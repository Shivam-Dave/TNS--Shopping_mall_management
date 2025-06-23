import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ItemService } from '../item.service';
import { CartService } from '../cart.service';
import { OrderService } from '../order.service';
import { Item } from '../item.model'; // Corrected import path
import { CartItem } from '../cart.model'; // Corrected import path

@Component({
  selector: 'app-storefront',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './storefront.component.html',
  styleUrls: ['./storefront.component.scss']
})
export class StorefrontComponent implements OnInit {
  allItems: Item[] = [];
  cartItems: CartItem[] = [];
  cartTotal = 0;
  currentCustomerId = 1;

  constructor(
    private itemService: ItemService,
    private cartService: CartService,
    private orderService: OrderService
  ) {}

  ngOnInit(): void {
    this.itemService.getAllItems().subscribe(data => {
      this.allItems = data;
    });
    this.cartService.cartItems$.subscribe(items => {
      this.cartItems = items;
      this.cartTotal = this.cartService.getCartTotal();
    });
  }

  addToCart(item: Item): void {
    this.cartService.addToCart(item);
  }

  placeOrder(): void {
    if (this.cartItems.length === 0) {
      alert("Your cart is empty!");
      return;
    }
    this.orderService.placeOrder(this.currentCustomerId, this.cartItems).subscribe({
      next: () => {
        alert("Order placed successfully!");
        this.cartService.clearCart();
        this.itemService.getAllItems().subscribe(data => this.allItems = data);
      },
      error: (err) => {
        console.error("Order failed:", err);
        alert("There was an error placing your order. Please try again.");
      }
    });
  }
}
