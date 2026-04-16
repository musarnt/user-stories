// final: ExternalConsultant is a leaf in the hierarchy — no further extension allowed.
// Satisfies the sealed Person contract without reopening the hierarchy.
public final class ExternalConsultant extends Person {

    private final String agency;
    private final double hourlyRate;

    public ExternalConsultant(int id, String name, String agency, double hourlyRate) {
        super(id, name);
        if (agency == null || agency.isBlank()) throw new IllegalArgumentException("Agency cannot be blank.");
        if (hourlyRate < 0) throw new IllegalArgumentException("Hourly rate cannot be negative.");
        this.agency = agency;
        this.hourlyRate = hourlyRate;
    }

    public String getAgency()      { return agency; }
    public double getHourlyRate()  { return hourlyRate; }

    @Override
    public String toString() {
        return "ExternalConsultant[id=" + getId() + ", name=" + getName()
                + ", agency=" + agency + ", hourlyRate=" + hourlyRate + "]";
    }
}