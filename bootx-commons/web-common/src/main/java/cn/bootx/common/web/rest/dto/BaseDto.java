package cn.bootx.common.web.rest.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

/**   
* 基础Dto
* @author xxm  
* @date 2020/6/1 16:12 
*/
public class BaseDto implements Serializable {
    private static final long serialVersionUID = 2985535678913619503L;
    /** 主键 */
    private Long id;

    /** 创建时间 */
    private LocalDateTime createTime;

    /** 最后修改时间 */
    private LocalDateTime lastModifiedTime;

    /** 版本号 */
    private Integer version;

    public Long getId() {
        return id;
    }

    public BaseDto setId(Long id) {
        this.id = id;
        return this;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public LocalDateTime getLastModifiedTime() {
        return lastModifiedTime;
    }

    public void setLastModifiedTime(LocalDateTime lastModifiedTime) {
        this.lastModifiedTime = lastModifiedTime;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }
}
