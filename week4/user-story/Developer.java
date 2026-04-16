// TASK 3: Developer subclass with primaryLanguage attribute
// TASK 4: implements Promotable — must override calculateBonus()
public final class Developer extends Employee implements Promotable {

    private final String primaryLanguage;

    public Developer(int id, String name, double salary, String primaryLanguage) {
        super(id, name, salary);
        if (primaryLanguage == null || primaryLanguage.isBlank())
            throw new IllegalArgumentException("Primary language cannot be blank.");
        this.primaryLanguage = primaryLanguage;
    }

    public String getPrimaryLanguage() { return primaryLanguage; }

    // Bonus rule: developers receive 10% of their monthly salary
    @Override
    public double calculateBonus() {
        return salary * 0.10;
    }

    @Override
    public String toString() {
        return "Developer[id=" + getId() + ", name=" + getName()
                + ", salary=" + salary + ", language=" + primaryLanguage + "]";
    }
}