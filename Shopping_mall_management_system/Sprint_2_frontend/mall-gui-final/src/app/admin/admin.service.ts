import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { MallAdmin } from './admin.model'; // FIX: Corrected the import path

@Injectable({
  providedIn: 'root'
})
export class AdminService {

  private readonly API_URL = 'http://localhost:8083/api/admin';

  constructor(private http: HttpClient) { }

  getAdmins(): Observable<MallAdmin[]> {
    return this.http.get<MallAdmin[]>(this.API_URL);
  }

  addAdmin(admin: Omit<MallAdmin, 'mallAdminId'>): Observable<MallAdmin> {
    return this.http.post<MallAdmin>(this.API_URL, admin);
  }

  updateAdmin(admin: MallAdmin): Observable<void> {
    return this.http.put<void>(`${this.API_URL}/${admin.mallAdminId}`, admin);
  }

  deleteAdmin(adminId: number): Observable<void> {
    return this.http.delete<void>(`${this.API_URL}/${adminId}`);
  }
}
