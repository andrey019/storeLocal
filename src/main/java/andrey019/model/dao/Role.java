package andrey019.model.dao;

public enum Role {
    USER("USER"),
    MANAGER("MANAGER"),
    DBA("DBA"),
    ADMIN("ADMIN");

    private String role;

    private Role(final String role){
        this.role = role;
    }

    public String getRole(){
        return role;
    }
}