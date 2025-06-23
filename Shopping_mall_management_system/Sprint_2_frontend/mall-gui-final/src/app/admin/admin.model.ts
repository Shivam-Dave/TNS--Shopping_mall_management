/**
 * Defines the structure for a MallAdmin object.
 * This should match the structure of the MallAdmin entity in your Spring Boot backend.
 */
export interface MallAdmin {
  mallAdminId: number;
  username: string;
  password?: string; // Password is included but often not displayed directly
  email: string;
  loginAttempts?: number; // Added loginAttempts
  isActive?: boolean;
  lastLoginTime?: string;
}
