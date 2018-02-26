package org.xxz.myblog.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * @author tt
 */
@Setter
@Getter
@ToString
public class TestVO {

    @NotNull(message = "id不能为空")
    // No validator could be found for constraint 'javax.validation.constraints.Size' validating type 'java.lang.Integer'
    //@Size(message = "id必须大于{min}小于{max}")
    @Min(value = 10, message = "id必须大于等于10")
    @Max(value = 20, message = "id必须小于等于20")
    private Integer id;
    @NotBlank(message = "name不能为空")
    private String name;
    
    @NotBlank(message = "password must not be blank")
    @Length(min = 6, max = 16, message = "password length between 6 and 16")
    private String password;
    @NotBlank(message = "confirmPassword must not be blank")
    @Length(min = 6, max = 16, message = "confirmPassword length between 6 and 16")
    private String confirmPassword;
    
    // confirm password equals password 自定义注解ConstraintValidator
    // 注意点：采用assertTrue我们的方法名称要符合javabean规范，boolean类型要以is开头
    @AssertTrue(message = "confirmPassword and password must be equlas")
    public boolean isEqualsPassword() {
        return confirmPassword != null && confirmPassword.equals(password);
    }

}
