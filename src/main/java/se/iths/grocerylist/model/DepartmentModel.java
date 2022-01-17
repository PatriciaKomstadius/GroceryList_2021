package se.iths.grocerylist.model;

public class DepartmentModel {


    private String departmentName;

    public DepartmentModel() {
    }

    public DepartmentModel(String departmentName) {

        this.departmentName = departmentName;
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
                ", departmentName='" + departmentName + '\'' +
                '}';
    }
}
