package DesignPatterns;

// Enum to represent vehicle types
enum VehicleType {
    CAR, BIKE, TRUCK
}

// Product interface
interface Vehicle {
    String drive();
}

// Concrete products
class Car implements Vehicle {
    @Override
    public String drive() {
        return "Driving a car";
    }
}

class Bike implements Vehicle {
    @Override
    public String drive() {
        return "Riding a bike";
    }
}

class Truck implements Vehicle {
    @Override
    public String drive() {
        return "Driving a truck";
    }
}

// Factory class with static method
class VehicleFactory {
    public static Vehicle createVehicle(VehicleType vehicleType) {
        switch (vehicleType) {
            case CAR:
                return new Car();
            case BIKE:
                return new Bike();
            case TRUCK:
                return new Truck();
            default:
                throw new IllegalArgumentException("Invalid vehicle type");
        }
    }
}

// Client code using the Factory pattern
public class Factory {
    public static void main(String[] args) {
        // Directly calling the static method createVehicle without creating an instance
        Vehicle car = VehicleFactory.createVehicle(VehicleType.CAR);
        System.out.println(car.drive());

        Vehicle bike = VehicleFactory.createVehicle(VehicleType.BIKE);
        System.out.println(bike.drive());

        Vehicle truck = VehicleFactory.createVehicle(VehicleType.TRUCK);
        System.out.println(truck.drive());
    }
}
