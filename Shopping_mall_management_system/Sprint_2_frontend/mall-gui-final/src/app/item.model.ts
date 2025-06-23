// This model represents an item sold by a shop owner.
export interface Item {
    id: number;
    name: string;
    description: string;
    price: number;
    quantity: number;
  }
  
  // This represents the data needed to create a new item.
  export interface NewItem {
    name: string;
    description: string;
    price: number;
    quantity: number;
  }
  