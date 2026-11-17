package homework4.task1;

public abstract class Employee {
    // Encapsulated private fields
    private String id;
    private String name;


    // Default Constructure
    public Employee() {
        this.id = "0";
        this.name = "Unknow";
    }

    // Constructor called by subclasses using super(id, name)
    public Employee(String id, String name) {
        this.id = id;
        this.name = name;
    }

    // Abstract method: must be implemented by child classes
    public abstract double calculateSalary();

    // Getters
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
