import { City } from "./city.model";

export class Hotel {
    id: number;
    name: string;
    address: string;
    phone: string;
    stars: number;
    pricePerNight: number;
    availableRooms: number;
    city: City;
    manager: any;  // Remplacez `any` par `User` si vous avez une interface User
    imageUrl: string;

    constructor(
        id: number,
        name: string,
        address: string,
        phone: string,
        stars: number,
        pricePerNight: number,
        availableRooms: number,
        city: any,
        manager: any,
        imageUrl: string
    ) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.stars = stars;
        this.pricePerNight = pricePerNight;
        this.availableRooms = availableRooms;
        this.city = city;
        this.manager = manager;
        this.imageUrl = imageUrl;
    }
}
