/* TASK 2: Immutable data modeling with records (Java 17/21)

 Legacy style (Java 8/11): a POJO required manual boilerplate:

   public class PerformanceReport {
       private final int employeeId;
       private final double average;
       private final String feedback;

       public PerformanceReport(int employeeId, double average, String feedback) {
           this.employeeId = employeeId;
           this.average = average;
           this.feedback = feedback;
       }
       public int getEmployeeId()   { return employeeId; }
       public double getAverage()   { return average; }
       public String getFeedback()  { return feedback; }

       @Override public String toString() {
           return "PerformanceReport[employeeId=" + employeeId
                   + ", average=" + average + ", feedback=" + feedback + "]";
       }
   }

 Modern style (Java 17/21): a record declares all of the above in one line.
 The compiler auto-generates: constructor, accessors, equals(), hashCode(), toString().
 Records are implicitly final and all fields are immutable (private final).
 Ideal for lightweight, read-only data carriers like end-of-month reports.
*/
public record PerformanceReport(int employeeId, double average, String feedback) {}