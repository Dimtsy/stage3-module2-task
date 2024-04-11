package com.mjc.school.controller.menu.newscommands;

import com.mjc.school.controller.BaseController;
import com.mjc.school.controller.menu.MenuOptions;
import com.mjc.school.controller.menuinterface.MenuCommands;
import com.mjc.school.service.dto.NewsDtoRequest;
import com.mjc.school.service.dto.NewsDtoResponse;
import com.mjc.school.service.exception.Errors;
import com.mjc.school.service.exception.NotFoundException;
import com.mjc.school.service.exception.ValidatorException;
import com.mjc.school.service.validation.Validator;
import org.springframework.stereotype.Component;

import java.util.Scanner;

import static com.mjc.school.controller.menu.TextMenu.ENTER_NEWS_ID;
import static com.mjc.school.controller.menu.TextMenu.OPERATION;

@Component
public class RemoveNewsById implements MenuCommands {
    private final BaseController<NewsDtoRequest, NewsDtoResponse, Long> newsController;
    private final Scanner scanner;

    public RemoveNewsById(BaseController<NewsDtoRequest, NewsDtoResponse, Long> newsController) {
        this.newsController = newsController;
        this.scanner = new Scanner(System.in);
    }

    @Override
    public void execute() {
        System.out.println(OPERATION.getText() + MenuOptions.REMOVE_NEWS_BY_ID.getOptionName());
        Validator validator = new Validator();
        try {
            System.out.println(ENTER_NEWS_ID.getText());
            String newsId = scanner.nextLine();
            if (!validator.validateId(newsId)) {
                throw new ValidatorException(Errors.ERROR_NEWS_ID_FORMAT.getErrorData("", false));
            }

            System.out.println(newsController.deleteById(Long.parseLong(newsId)));
        } catch (ValidatorException | NotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
}
