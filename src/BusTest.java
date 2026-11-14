/** Repeatable checks without third-party test libraries. */
public class BusTest {
    private static int passed;
    private static void check(boolean result, String label) {
        if (!result) throw new AssertionError(label);
        passed++;
        System.out.println("PASS " + label);
    }
    private static void rejects(Runnable action, String label) {
        try { action.run(); }
        catch (IllegalArgumentException expected) { check(true, label); return; }
        throw new AssertionError(label);
    }
    /** Checks calculations, state changes and invalid inputs. */
    public static void main(String[] args) {
        Bus bus = new Bus("PTEST 01", "Ravi", "Factory A", 40, 20, 25, true);
        check(bus.calculateOccupancyPercentage() == 50.0, "20 of 40 seats gives 50 percent");
        check(bus.boardPassengers(5) && bus.getPassengerCount() == 25, "board 5 passengers");
        check(!bus.boardPassengers(16) && bus.getPassengerCount() == 25, "reject overcapacity without state change");
        check(!bus.boardPassengers(0), "reject zero boarding");
        check(!bus.boardPassengers(-1), "reject negative boarding");
        rejects(() -> bus.setPassengerCount(41), "reject passenger total above capacity");
        rejects(() -> bus.setSeatingCapacity(24), "reject capacity below current load");
        rejects(() -> bus.setDistanceKm(-1), "reject negative distance");
        rejects(() -> bus.setDistanceKm(Double.NaN), "reject NaN distance");
        rejects(() -> bus.setDistanceKm(Double.POSITIVE_INFINITY), "reject infinite distance");
        rejects(() -> bus.setDriverName(" "), "reject blank driver");
        rejects(() -> bus.setRegistrationNumber(null), "reject null registration");
        check(bus.dispatch() && !bus.isAvailable(), "dispatch changes availability");
        check(!bus.dispatch(), "reject repeated dispatch");
        check(!bus.boardPassengers(1), "reject boarding after dispatch");
        Bus spare = new Bus();
        check(spare.getSeatingCapacity() == 40 && spare.getPassengerCount() == 0, "default constructor values");
        check(!spare.dispatch(), "reject empty bus dispatch");
        spare.setPassengerCount(1);
        check(!spare.dispatch(), "reject zero distance dispatch");
        rejects(() -> new Bus("X", "Y", "Z", 0, 0, 0, true), "reject invalid constructor capacity");
        System.out.println(passed + " checks passed.");
    }
}
