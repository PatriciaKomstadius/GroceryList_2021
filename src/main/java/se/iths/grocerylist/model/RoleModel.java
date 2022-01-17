package se.iths.grocerylist.model;

public class RoleModel {
    private Long id;
    private String roleName;

    public RoleModel() {
    }

    public RoleModel(Long id, String roleName) {
        this.id = id;
        this.roleName = roleName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    @Override
    public String toString() {
        return "RoleModel{" +
                ", roleName='" + roleName + '\'' +
                '}';
    }
}
