package com.aop.exam;

import com.aop.exam.annotation.Retry;
import com.aop.exam.annotation.Trace;
import org.springframework.stereotype.Repository;

@Repository
public class ExamRepository {

    private static int seq = 0;

    /**
     * 다섯번에 한번 실패하는 요청
     */
    @Trace
    @Retry(4)
    public String save(String itemId) {
        seq++;
        if (seq % 5 == 0) {
            throw new IllegalArgumentException("예외 발생");
        }
        return "ok";
    }
}
