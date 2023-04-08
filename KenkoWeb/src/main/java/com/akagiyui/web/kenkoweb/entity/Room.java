package com.akagiyui.web.kenkoweb.entity;

import lombok.Data;

/**
 * @author AkagiYui
 */

@Data
public class Room {
    Integer id;
    String name; // 名称
    RoomType roomType; // 房间类型
    Boolean isAvailable; // 是否可用
}
