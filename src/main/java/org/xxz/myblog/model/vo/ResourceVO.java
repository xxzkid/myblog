package org.xxz.myblog.model.vo;

import lombok.*;
import org.xxz.myblog.common.bean.IResource;
import org.xxz.myblog.common.constant.SystemConst;

/**
 * @author tt
 */
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ResourceVO implements IResource {


    private static final long serialVersionUID = -2281334849041660486L;

    private String url;

    @Override
    public String getFullUrl() {
        return SystemConst.STATIC_PREFIX + this.getUrl();
    }
}
