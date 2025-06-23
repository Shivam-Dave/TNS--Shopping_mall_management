export interface OrderItem {
    id: number; // <-- ADDED THIS FIELD
    itemName: string;
    quantity: number;
    priceAtOrder: number;
}

export interface Order {
    id: number;
    orderDate: string;
    status: string;
    totalAmount: number;
    customer: {
        id: number;
        name: string;
    };
    orderItems: OrderItem[];
}
