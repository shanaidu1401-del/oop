/** A factory bus whose details and dispatch state are kept together. */
public class Bus {
    private String registrationNumber;
    private String driverName;
    private String routeName;
    private int seatingCapacity;
    private int passengerCount;
    private double distanceKm;
    private boolean available;

    /** Creates an empty bus record that can be filled using setters. */
    public Bus() {
        this("UNASSIGNED", "Unassigned", "Unassigned", 40, 0, 0.0, true);
    }

    /** Builds a bus using the same validation applied to later changes. */
    public Bus(String registrationNumber, String driverName, String routeName,
               int seatingCapacity, int passengerCount, double distanceKm,
               boolean available) {
        setRegistrationNumber(registrationNumber);
        setDriverName(driverName);
        setRouteName(routeName);
        setSeatingCapacity(seatingCapacity);
        setPassengerCount(passengerCount);
        setDistanceKm(distanceKm);
        setAvailable(available);
    }

    /** Returns the registration number. */
    public String getRegistrationNumber() { return registrationNumber; }
    /** Returns the assigned driver. */
    public String getDriverName() { return driverName; }
    /** Returns the route. */
    public String getRouteName() { return routeName; }
    /** Returns the seating limit. */
    public int getSeatingCapacity() { return seatingCapacity; }
    /** Returns the current passenger total. */
    public int getPassengerCount() { return passengerCount; }
    /** Returns the planned trip distance. */
    public double getDistanceKm() { return distanceKm; }
    /** Returns whether the bus is available for dispatch. */
    public boolean isAvailable() { return available; }

    /** Rejects a missing registration number. */
    public void setRegistrationNumber(String value) {
        if (value == null || value.trim().isEmpty())
            throw new IllegalArgumentException("Registration cannot be blank.");
        registrationNumber = value.trim();
    }
    /** Rejects a missing driver name. */
    public void setDriverName(String value) {
        if (value == null || value.trim().isEmpty())
            throw new IllegalArgumentException("Driver cannot be blank.");
        driverName = value.trim();
    }
    /** Rejects a missing route name. */
    public void setRouteName(String value) {
        if (value == null || value.trim().isEmpty())
            throw new IllegalArgumentException("Route cannot be blank.");
        routeName = value.trim();
    }
    /** Prevents a capacity change that would leave too many passengers. */
    public void setSeatingCapacity(int value) {
        if (value <= 0 || value < passengerCount)
            throw new IllegalArgumentException("Capacity must be positive and at least the passenger total.");
        seatingCapacity = value;
    }
    /** Keeps passenger numbers within the seating limit. */
    public void setPassengerCount(int value) {
        if (value < 0 || value > seatingCapacity)
            throw new IllegalArgumentException("Passengers must be between 0 and capacity.");
        passengerCount = value;
    }
    /** Rejects negative distances and non-finite numeric values. */
    public void setDistanceKm(double value) {
        if (!Double.isFinite(value) || value < 0)
            throw new IllegalArgumentException("Distance must be a finite non-negative number.");
        distanceKm = value;
    }
    /** Updates availability, for example after a maintenance check. */
    public void setAvailable(boolean value) { available = value; }

    /** Calculates the proportion of occupied seats as a percentage. */
    public double calculateOccupancyPercentage() {
        return passengerCount * 100.0 / seatingCapacity;
    }
    /** Boards a group only when the whole group can fit. */
    public boolean boardPassengers(int count) {
        if (!available || count <= 0 || count > seatingCapacity - passengerCount)
            return false;
        setPassengerCount(passengerCount + count);
        return true;
    }
    /** Dispatches a loaded bus once and marks it unavailable. */
    public boolean dispatch() {
        if (!available || passengerCount == 0 || distanceKm <= 0)
            return false;
        setAvailable(false);
        return true;
    }
}
