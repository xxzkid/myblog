package org.xxz.myblog.model.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import org.xxz.myblog.common.bean.BaseDTO;

/**
 * @author tt
 */
@Setter
@Getter
@ToString
public class SysParamDTO extends BaseDTO {

    @NotBlank(message = "参数名称不能为空")
    private String paramName;
    @NotBlank(message = "c桉树值不能为空")
    private String paramValue;

}
