import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, NgForm } from '@angular/forms';
import { RouterLink } from '@angular/router'; // FIX: Added RouterLink import
import { ShopownerService } from './shopowner.service';
import { ShopOwner } from './shopowner.model';

@Component({
  selector: 'app-shopowner',
  standalone: true,
  imports: [CommonModule, FormsModule, RouterLink], // FIX: Added RouterLink to imports array
  templateUrl: './shopowner.component.html',
  styleUrls: ['./shopowner.component.scss']
})
export class ShopownerComponent implements OnInit {
  public shopOwners: ShopOwner[] = [];
  public selectedOwner: ShopOwner | null = null;
  public isEditing = false;

  constructor(private shopOwnerService: ShopownerService) { }

  ngOnInit(): void {
    this.getShopOwners();
  }

  getShopOwners(): void {
    this.shopOwnerService.getShopOwners().subscribe({
      next: (data) => this.shopOwners = data,
      error: (err: any) => console.error('Error fetching shop owners:', err)
    });
  }

  onFormSubmit(form: NgForm): void {
    if (form.invalid) return;

    if (this.isEditing && this.selectedOwner) {
      const updatedOwner: ShopOwner = { ...this.selectedOwner, ...form.value };
      this.shopOwnerService.updateShopOwner(updatedOwner).subscribe(() => {
        this.getShopOwners();
        this.resetForm(form);
      });
    } else {
      this.shopOwnerService.addShopOwner(form.value).subscribe(() => {
        this.getShopOwners();
        this.resetForm(form);
      });
    }
  }

  onEdit(owner: ShopOwner): void {
    this.isEditing = true;
    this.selectedOwner = { ...owner };
  }

  onDelete(id: number): void {
    if (confirm('Are you sure you want to delete this shop owner?')) {
      this.shopOwnerService.deleteShopOwner(id).subscribe({
        next: () => this.getShopOwners(),
        error: (err: any) => console.error('Error deleting shop owner:', err)
      });
    }
  }

  resetForm(form?: NgForm): void {
    if (form) form.resetForm();
    this.isEditing = false;
    this.selectedOwner = null;
  }
}
