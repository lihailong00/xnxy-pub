package com.lee.xnxy.util;

import com.lee.xnxy.model.dto.UserContextDTO;

public class UserContextDTOUtil {
    private static final ThreadLocal<UserContextDTO> userContextDTOThreadLocal = new ThreadLocal<>();

    public static void setUserContextDTO(UserContextDTO userContextDTO) {
        userContextDTOThreadLocal.set(userContextDTO);
    }

    public static UserContextDTO getUserContextDTO() {
        return userContextDTOThreadLocal.get();
    }

    public static void removeUserContextDTO() {
        userContextDTOThreadLocal.remove();
    }
}
