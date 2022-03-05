package pl.umcs.bookstore.app.book.domain;

import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.util.Strings;
import org.springframework.validation.BindingResult;
import pl.umcs.bookstore.app.book.domain.dto.CreateBookDto;
import pl.umcs.bookstore.app.shared.ValidationConstants;

@RequiredArgsConstructor
class BookValidator {

    private final BookRepository repository;

    public void validate(CreateBookDto dto, BindingResult bindingResult) {
        validateTitle(dto.getTitle(), bindingResult);
        validateAuthor(dto.getAuthor(), bindingResult);
    }

    private void validateTitle(String title, BindingResult bindingResult) {
        final String fieldName = ValidationConstants.Field.TITLE;
        if (Strings.isBlank(title)) {
            bindingResult.rejectValue(fieldName, ValidationConstants.Error.TITLE_IS_REQUIRED);
        } else if (repository.findByTitle(title).isPresent()) {
            bindingResult.rejectValue(fieldName, ValidationConstants.Error.TITLE_TAKEN);
        }
    }

    private void validateAuthor(String author, BindingResult bindingResult) {
        final String fieldName = ValidationConstants.Field.AUTHOR;
        if (Strings.isBlank(author)) {
            bindingResult.rejectValue(fieldName, ValidationConstants.Error.AUTHOR_IS_REQUIRED);
        }
    }
}
