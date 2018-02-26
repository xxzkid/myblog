package org.xxz.myblog.model.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author tt
 */
@Setter
@Getter
@ToString
public class SysParamVO {

    private String uploadUrl;
    private String title;
    private String author;
    private String wxpay;

}
