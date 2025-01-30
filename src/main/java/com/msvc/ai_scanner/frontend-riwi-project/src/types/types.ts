export enum Category {
    HOGAR = 'HOGAR',
    TRANSPORTE = 'TRANSPORTE',
    ALIMENTACION = 'ALIMENTACION',
    ENTRETENIMIENTO = 'ENTRETENIMIENTO',
    SALUD = 'SALUD'
}

export enum Type {
    EXPENSE = 'EXPENSE',
    INCOME = 'INCOME'
}

export interface BillDto {
    company: string;
    category: Category;
    billDate: string;
    type: Type;
    amount: number;
    userId: number;
}