package com.seezoon.admin.domain.sys.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class ChangePasswordDto  {

    @NotNull
    private Integer userId;
    @NotBlank
    private String oldPassword;
    @NotBlank
    @Length(min = 6)
    private String newPassword;
}
