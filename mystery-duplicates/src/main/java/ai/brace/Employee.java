package ai.brace;

public class Employee
{
    public String firstName;
    public String middleInitial;
    public String lastName;
    public String socialSecurityNumber;

    public Employee(String _lastName, String _firstName, String _middleInitial, String _socialSecurityNumber)
    {
        this.firstName = _firstName;
        this.middleInitial = _middleInitial;
        this.lastName = _lastName;
        this.socialSecurityNumber = _socialSecurityNumber;
    }

    /**
     *  Overrides equals methods for Employee object comparison
     * @param obj
     * @return
     */
    @Override
    public boolean equals(Object obj)
    {
        if(this == obj)
            return true;

        if(obj == null || obj.getClass()!= this.getClass())
            return false;

        Employee e = (Employee)obj;

        return (e.firstName.equals(this.firstName) &&
                e.middleInitial.equals(this.middleInitial) &&
                e.lastName.equals(this.lastName) &&
                e.socialSecurityNumber.equals(this.socialSecurityNumber));
    }

    /**
     * Returns unique hashcode for Employee objects
     * @return
     */
    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + this.firstName.hashCode();
        result = 31 * result + this.middleInitial.hashCode();
        result = 31 * result + this.lastName.hashCode();
        result = 31 * result + this.socialSecurityNumber.hashCode();
        return result;
    }
}
