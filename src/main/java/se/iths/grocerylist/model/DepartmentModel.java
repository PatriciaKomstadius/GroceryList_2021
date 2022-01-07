package se.iths.grocerylist.model;

public class DepartmentModel {

    private Long id;
    private String departmentName;

    public DepartmentModel() {
    }

    public DepartmentModel(Long id, String departmentName) {
        this.id = id;
        this.departmentName = departmentName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    @Override
    public String toString() {
        return "DepartmentModel{" +
                "id=" + id +
                ", departmentName='" + departmentName + '\'' +
                '}';
    }
}
