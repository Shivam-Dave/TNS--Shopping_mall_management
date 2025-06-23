import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, NgForm } from '@angular/forms';
import { ActivatedRoute } from '@angular/router'; // Removed unused RouterLink
import { ItemService } from '../item.service';
import { Item, NewItem } from '../item.model'; // Corrected import path

@Component({
  selector: 'app-item-management',
  standalone: true,
  imports: [CommonModule, FormsModule], // Removed unused RouterLink
  templateUrl: './item-management.component.html',
  styleUrls: ['./item-management.component.scss']
})
export class ItemManagementComponent implements OnInit {
  shopOwnerId!: number;
  items: Item[] = [];
  newItem: NewItem = { name: '', description: '', price: 0, quantity: 0 };

  constructor(
    private route: ActivatedRoute,
    private itemService: ItemService
  ) { }

  ngOnInit(): void {
    const id = this.route.snapshot.paramMap.get('id');
    if (id) {
      this.shopOwnerId = +id;
      this.loadItems();
    }
  }

  loadItems(): void {
    this.itemService.getItemsByShopOwner(this.shopOwnerId).subscribe(data => {
      this.items = data;
    });
  }

  onFormSubmit(form: NgForm): void {
    if (form.invalid) return;
    this.itemService.addItem(this.shopOwnerId, this.newItem).subscribe(() => {
      this.loadItems();
      form.resetForm();
      this.newItem = { name: '', description: '', price: 0, quantity: 0 };
    });
  }

  onDelete(itemId: number): void {
    if (confirm('Are you sure you want to delete this item?')) {
      this.itemService.deleteItem(itemId).subscribe(() => this.loadItems());
    }
  }
}
