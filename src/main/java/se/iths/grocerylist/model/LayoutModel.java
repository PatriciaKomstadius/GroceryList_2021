package se.iths.grocerylist.model;

public class LayoutModel {


    private String type;

    public LayoutModel() {
    }

    public LayoutModel(String type){
        this.type = type;
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
