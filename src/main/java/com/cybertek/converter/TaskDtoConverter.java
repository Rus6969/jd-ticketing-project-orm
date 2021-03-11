package com.cybertek.converter;

import com.cybertek.dto.TaskDTO;
import com.cybertek.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationPropertiesBinding;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;



@Component
@ConfigurationPropertiesBinding
// bc we have 2 dropdowns(assignrd manager/project ) menus in view  menus returns as String need as object
public class TaskDtoConverter  implements Converter<String, TaskDTO> {
    @Autowired
private TaskService taskService;

    @Override
    public TaskDTO convert(String source) {
        Long id =Long.parseLong(source);
        return taskService.findById(id);
    }
}
