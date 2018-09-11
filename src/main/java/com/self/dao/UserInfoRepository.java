package com.self.dao;

import com.self.model.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface UserInfoRepository extends JpaRepository<UserInfo, Long> {

    List<UserInfo> findByTypeAndLogTimeBetween(String type, Date startTime, Date endTime);

    UserInfo getBySendIdAndType(String sendId, String type);

    List<UserInfo> findBySendId(String sendId);
}
