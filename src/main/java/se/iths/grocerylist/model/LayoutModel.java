package se.iths.grocerylist.model;

import se.iths.grocerylist.entity.DepartmentEntity;

import java.util.Set;

public class LayoutModel {

    private String type;
    private Set<DepartmentEntity> departments;

    public LayoutModel() {
    }

    public LayoutModel(String type) {
        this.type = type;
    }

    public Set<DepartmentEntity> getDepartments() {
        return departments;
    }

    public void setDepartments(Set<DepartmentEntity> departments) {
        this.departments = departments;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("LayoutModel{");
        sb.append("type='").append(type).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
