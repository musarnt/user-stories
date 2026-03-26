public record CompanyRecord(
        String name,         // Company legal name
        String nit,          // Tax identification number
        int foundationYear   // Year the company was founded
) {}

/**
 * CompanyRecord - Task 2
 *
 * Modern Java 17+ Record for company data.
 *
 * ANALYSIS - Class vs Record:
 *
 * VERBOSITY:
 * The Employee class required explicit field declarations,
 * a full constructor, and would need manual getters to access data.
 * This Record achieves the same result in a single line —
 * Java auto-generates the constructor, getters, toString(),
 * equals(), and hashCode().
 *
 * IMMUTABILITY:
 * Unlike a regular class, a Record's fields cannot be modified
 * after creation. There are no setters — by design.
 * This makes Records safer: you can pass them around the system
 * knowing no other part of the code can accidentally change them.
 * Immutability is a core principle in modern software design.
 *
 * USE CASE:
 * Records are ideal for data that should not change — company info,
 * configuration, API responses, database query results.
 * They are NOT ideal for data that needs to be updated, like Employee.
 */