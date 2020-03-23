package com.gdin.gmall.service;

import com.gdin.gmall.bean.UmsMember;
import com.gdin.gmall.bean.UmsMemberReceiveAddress;

import java.util.List;

public interface UserService {
    List<UmsMember> getAllUser();

    List<UmsMemberReceiveAddress> getReceiveAddressByMemberId(String memberId);
}
