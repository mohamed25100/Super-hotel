export class User {
    constructor(
        public id: number,
        public firstName: string,
        public lastName: string,
        public email: string,
        public password: string,
        public role: Role
    ) { }
}

export interface ITokenUser {
    id: number;
    firstName: string;
    lastName: string;
    email: string;
    role: string;
}

// Enum pour les rôles des utilisateurs
export enum Role {
    ADMIN = "ADMIN",
    USER = "USER",
    MANAGER = "MANAGER"
}
