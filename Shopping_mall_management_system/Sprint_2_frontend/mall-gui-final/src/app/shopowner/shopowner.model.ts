/**
 * Defines the structure for a ShopOwner object.
 * This should match the structure of the ShopOwner entity in your Spring Boot backend.
 */
export interface ShopOwner {
    id: number;
    name: string;
    email: string;
    shopName: string;
    registrationDate?: string;
  }
  