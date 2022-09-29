package team.cbk.demo.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "user实体类")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "用户名")
    private String userName;
    @ApiModelProperty(value = "用户密码")
    private String userPassword;
    @ApiModelProperty(value = "用户邮箱")
    private String userEmail;
    @ApiModelProperty(value = "用户状态，0表示未激活，1表示激活")
    private Integer userStatus;
    @ApiModelProperty(value = "用户邮箱激活码")
    private String userCode;
    @ApiModelProperty(value = "用户修改密码用的验证码")
    private String userVerification;

}