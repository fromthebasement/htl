package com.fromthebasement.service.impl;

import com.fromthebasement.service.LogoutService;
import org.springframework.stereotype.Service;

/**
 * Created by jeffginn on 4/8/14.
 */
@Service("logoutService")
public class LogoutServiceImpl implements LogoutService {
    @Override
    public boolean logout() {
        return false;
    }
}
