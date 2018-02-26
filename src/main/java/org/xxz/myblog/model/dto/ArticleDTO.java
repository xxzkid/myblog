package org.xxz.myblog.model.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;
import org.xxz.myblog.common.bean.BaseDTO;

import javax.validation.constraints.Pattern;
import java.util.Date;

/**
 * @author tt
 */
@Setter
@Getter
@ToString
public class ArticleDTO extends BaseDTO {

	private static final long serialVersionUID = 1L;

	private Long id;

    @NotEmpty(message = "标题不能为空")
    @Length(min = 1, max = 200, message = "标题长度在1-200之间")
    private String title;

    @NotEmpty(message = "固定链接不能为空")
    @Length(min = 1, max = 50, message = "固定链接长度在1-50之间")
    @Pattern(regexp = "[a-zA-Z0-9\\-]*", message = "固定链接必须是字母数字中划线组成，例如：spring-boot-1")
    private String fixedLink;

    @NotEmpty(message = "内容不能为空")
    private String content;

    @Range(min = 0, max = 1, message = "状态必须是0或1")
    private Integer isShow;

    @Range(min = 0, max = Integer.MAX_VALUE, message = "排序值必须大于0")
    private Integer sortValue;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createTime;
    
    @NotEmpty(message = "标签不能为空")
    private String tags;
    
    private Long categoryId;
	
}
