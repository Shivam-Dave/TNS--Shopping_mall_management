import { Item } from "./item.model";

// Represents a single item within the shopping cart,
// combining the item's data with the quantity being ordered.
export interface CartItem {
    item: Item;
    quantity: number;
}
