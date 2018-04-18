package com.khohlov.khohlov.service.current.user;

import com.khohlov.khohlov.domain.security.CurrentUser;

public interface CurrentUserService {
    boolean canAccessUser(CurrentUser currentUser, Long userId);
}
