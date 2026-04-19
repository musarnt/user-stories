/*  
    non-sealed: allows Developer and Manager to extend Employee.
    If this were final, no subclass could exist.
    If this were sealed, we would need another permits clause.
    non-sealed reopens the hierarchy intentionally only at this level.
*/
public non-sealed class Employee extends Person {

    // protected: accessible by Employee and its subclasses (Developer, Manager)
    protected double salary;

    public Employee(int id, String name, double salary) {
        super(id, name);
        if (salary < 0) throw new IllegalArgumentException("Salary cannot be negative.");
        this.salary = salary;
    }

    public double getSalary() { return salary; }

    @Override
    public String toString() {
        return "Employee[id=" + getId() + ", name=" + getName() + ", salary=" + salary + "]";
    }
}