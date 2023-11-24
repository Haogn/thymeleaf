package rikkei.academy.dto.request;

import rikkei.academy.utl.Role;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UserRegisterDTO {
    @NotEmpty(message = "Ten khong duoc de trong")
    private String userName ;
    @Email(message = "Email sai dinh dang")
    @NotEmpty(message = "Email khong duoc de trong")
    private String email ;
    @Size(min = 8, max = 16, message = "Password it nhat 8 ky tu , nhieu nhat 16 ky tu")
    private String password ;
    private String phone ;
    private Role role = Role.USER ;

    public UserRegisterDTO(String userName, String email, String password, String phone, Role role) {
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.role = role;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
