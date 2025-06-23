import { Routes } from '@angular/router';
import { EmployeeComponent } from './employee/employee.component';
import { AdminComponent } from './admin/admin.component';
import { ShopownerComponent } from './shopowner/shopowner.component';
import { CustomerComponent } from './customer/customer.component';
import { ItemManagementComponent } from './item-management/item-management.component';
import { StorefrontComponent } from './storefront/storefront.component';
import { OrdersListComponent } from './orders-list/orders-list.component';
import { OrderDetailsComponent } from './order-details/order-details.component';

export const routes: Routes = [
    { path: '', redirectTo: '/employees', pathMatch: 'full' },
    { path: 'employees', component: EmployeeComponent },
    { path: 'admins', component: AdminComponent },
    { path: 'shopowners', component: ShopownerComponent },
    { path: 'customers', component: CustomerComponent },
    { path: 'store', component: StorefrontComponent },
    { path: 'orders', component: OrdersListComponent },
    { path: 'orders/:id', component: OrderDetailsComponent } // <-- FIX: Added comma here
];
