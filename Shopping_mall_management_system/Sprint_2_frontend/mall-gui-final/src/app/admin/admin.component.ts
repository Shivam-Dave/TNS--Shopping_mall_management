import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, NgForm } from '@angular/forms';
import { AdminService } from './admin.service';
import { MallAdmin } from './admin.model';

@Component({
  selector: 'app-admin',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.scss']
})
export class AdminComponent implements OnInit {

  public admins: MallAdmin[] = [];
  public selectedAdmin: MallAdmin | null = null;
  public isEditing = false;

  constructor(private adminService: AdminService) { }

  ngOnInit(): void {
    this.getAdmins();
  }

  getAdmins(): void {
    this.adminService.getAdmins().subscribe({
      next: (data: MallAdmin[]) => {
        this.admins = data;
      },
      error: (err: any) => console.error('Error fetching admins:', err)
    });
  }

  onFormSubmit(form: NgForm): void {
    if (form.invalid) return;

    if (this.isEditing && this.selectedAdmin) {
      const updatedAdmin: MallAdmin = { ...this.selectedAdmin, ...form.value };
      this.adminService.updateAdmin(updatedAdmin).subscribe(() => this.getAdmins());
    } else {
      this.adminService.addAdmin(form.value).subscribe(() => this.getAdmins());
    }
    this.resetForm(form);
  }

  onEdit(admin: MallAdmin): void {
    this.isEditing = true;
    this.selectedAdmin = { ...admin };
  }

  onDelete(id: number): void {
    if (confirm('Are you sure you want to delete this admin?')) {
      this.adminService.deleteAdmin(id).subscribe({
        next: () => this.getAdmins(),
        error: (err: any) => console.error('Error deleting admin:', err)
      });
    }
  }

  resetForm(form?: NgForm): void {
    if (form) form.resetForm();
    this.isEditing = false;
    this.selectedAdmin = null;
  }
}
