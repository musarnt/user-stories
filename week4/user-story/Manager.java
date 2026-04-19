// TASK 4: implements Promotable — must override calculateBonus()
public final class Manager extends Employee implements Promotable {

    private final double monthlyBudget;

    public Manager(int id, String name, double salary, double monthlyBudget) {
        super(id, name, salary);
        if (monthlyBudget < 0) throw new IllegalArgumentException("Monthly budget cannot be negative.");
        this.monthlyBudget = monthlyBudget;
    }

    public double getMonthlyBudget() { return monthlyBudget; }

    // Bonus rule: managers receive 15% of their monthly salary
    @Override
    public double calculateBonus() {
        return salary * 0.15;
    }

    @Override
    public String toString() {
        return "Manager[id=" + getId() + ", name=" + getName()
                + ", salary=" + salary + ", monthlyBudget=" + monthlyBudget + "]";
    }
}