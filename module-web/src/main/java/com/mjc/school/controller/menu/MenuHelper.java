package com.mjc.school.controller.menu;

import com.mjc.school.controller.BaseController;
import com.mjc.school.controller.menu.authorcommands.*;
import com.mjc.school.controller.menu.newscommands.*;
import com.mjc.school.service.dto.AuthorDtoRequest;
import com.mjc.school.service.dto.AuthorDtoResponse;
import com.mjc.school.service.dto.NewsDtoRequest;
import com.mjc.school.service.dto.NewsDtoResponse;
import com.mjc.school.service.exception.Errors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

import static com.mjc.school.controller.menu.TextMenu.*;

@Component
public class MenuHelper {
    private final BaseController<NewsDtoRequest, NewsDtoResponse, Long> newsController;
    private final BaseController<AuthorDtoRequest, AuthorDtoResponse, Long> authorController;
    private final ExecuteCommand commandsExecutor;
    private final Scanner scanner;

    @Autowired
    public MenuHelper(BaseController<NewsDtoRequest, NewsDtoResponse, Long> newsController,
                      BaseController<AuthorDtoRequest, AuthorDtoResponse, Long> authorController,
                      ExecuteCommand commandsExecutor) {
        this.newsController = newsController;
        this.authorController = authorController;
        this.commandsExecutor = commandsExecutor;
        scanner = new Scanner(System.in);
    }

    public void runner() {
        while (true) {
            System.out.println(ENTER_NUMBER_OF_OPERATION.getText());
            for (MenuOptions options: MenuOptions.values()) {
                System.out.println(options.getOptionCode() + " - " + options.getOptionName());
            }
            switch (scanner.nextLine()) {
                case "1" -> commandsExecutor.executeCommand(new GetAllNews(newsController));
                case "2" -> commandsExecutor.executeCommand(new GetNewsById(newsController));
                case "3" -> commandsExecutor.executeCommand(new CreateNews(newsController));
                case "4" -> commandsExecutor.executeCommand(new UpdateNews(newsController));
                case "5" -> commandsExecutor.executeCommand(new RemoveNewsById(newsController));
                case "6" -> commandsExecutor.executeCommand(new GetAllAuthors(authorController));
                case "7" -> commandsExecutor.executeCommand(new GetAuthorById(authorController));
                case "8" -> commandsExecutor.executeCommand(new CreateAuthor(authorController));
                case "9" -> commandsExecutor.executeCommand(new UpdateAuthor(authorController));
                case "10" -> commandsExecutor.executeCommand(new RemoveAuthorById(authorController, newsController));
                case "0" -> System.exit(0);
                default -> System.out.println(Errors.ERROR_COMMAND_NOT_FOUND.getErrorMessage());
            }
        }
    }
}