package person.yejing.quartzexample.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApplicationJob {
    private String id;

    private String cronExpression;

    /**
     * 0: 暂停 1：开始
     */
    private String status;
}
