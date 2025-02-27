import { City } from "./city.model";
import { User } from "./user.model";

export class Hotel {
    id: number;
    name: string;
    address: string;
    phone: string;
    stars: number;
    pricePerNight: number;
    availableRooms: number;
    city: City;
    manager: User;
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
