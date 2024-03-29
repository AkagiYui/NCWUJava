package com.akagiyui.springbootproject.entity;


import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 基础实体类
 * @author AkagiYui
 */
@Data
// 继承策略
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
// 启用自动填充
@EntityListeners(AuditingEntityListener.class)
// 实体继承映射
@MappedSuperclass
public abstract class BaseEntity implements Serializable {
    /**
     * 记录ID
     */
    // 主键生成策略
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    /**
     * 更新时间
     */
    @LastModifiedDate
    @Column(nullable = false)
    private Date updateTime;

    /**
     * 创建时间
     */
    @CreatedDate
    @Column(nullable = false, updatable = false)
    private Date createTime;
}
